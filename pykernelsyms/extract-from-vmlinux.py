import re
import shlex
import subprocess
import sys

Usage = """
Vmlinux kernel symbols and crcs extractor
Usage:
python extract-from-vmlinux.py <vmlinux_file>
"""

ErrExecErr = """
extract-from-vmlinux.py: executed program returned next error:
"""

ErrObjdumpErr = """
extract-from-vmlinux.py: objdump returned next error:
"""

symSections = ['__ksymtab', '__ksymtab_gpl', '__ksymtab_unused']
crcSections = ['__kcrctab', '__kcrctab_gpl', '__kcrctab_unused']
stringSection = '__ksymtab_strings'

hexDict = {'0' : 0,
           '1' : 1,
           '2' : 2,
           '3' : 3,
           '4' : 4,
           '5' : 5,
           '6' : 6,
           '7' : 7,
           '8' : 8,
           '9' : 9,
           'a' : 10,
           'b' : 11,
           'c' : 12,
           'd' : 13,
           'e' : 14,
           'f' : 15}

def getInputFileName():
    if not len(sys.argv):
        raise Exception(Usage)
    return sys.argv[1]

def getOutput(command):
    proc = subprocess.Popen(shlex.split(command), stdout = subprocess.PIPE, stderr = subprocess.PIPE)
    output = proc.stdout.read()
    err = proc.stderr.read()
    if proc.wait():
        raise Exception(ErrExecErr + err)
    return output

def isValidElf():
    fileCmd = 'file -b ' + getInputFileName()
    fileOutput = getOutput(fileCmd)
    if re.match("ELF 32-bit LSB", fileOutput) is not None:
        return "32-bit"
    if re.match("ELF 64-bit LSB", fileOutput) is not None:
        return "64-bit"
    else:
        raise Exception("Elf file format unrecognized")

elfType = isValidElf()

def is32Bit():
    return elfType == "32-bit"

def is64Bit():
    return elfType == "64-bit"

def rawReadSection(sectionName):
    objdumpCmd = 'objdump -j ' + sectionName +' -s ' + getInputFileName()
    return getOutput(objdumpCmd)

def invertHex(hex):
    if is32Bit():
        return hex[6:8] + hex[4:6] + hex[2:4] + hex[0:2]
    if is64Bit():
        return hex[14:16] + hex[12:14] + hex[10:12] + hex[8:10] + hex[6:8] + hex[4:6] + hex[2:4] + hex[0:2]
    else:
        return None

def validHex(hex):
    if is32Bit():
        return len(hex) == 8 and re.match('^[0-9abcdef]*$', hex)
    if is64Bit():
        return len(hex) == 16 and re.match('^[0-9abcdef]*$', hex)
    else:
        return None


def hexToInt(hex):
    result = 0
    for char in hex:
        result = result * 16 + hexDict[char]
    return result

def hexToChar(hex):
    isFirst = True
    tempInt = 0
    result = ''
    for char in hex:
        if isFirst:
            tempInt = hexDict[char]
            isFirst = False
        else:
            tempInt = tempInt * 16 + hexDict[char]
            result += chr(tempInt)
            isFirst = True
    return result

def extractHexValues(objString):
    result = []

    if is32Bit():
        stringValues = re.sub(' ', '', objString[10:45])
        if len(stringValues) >= 8:
            result.append(invertHex(stringValues[0:8]))
        if len(stringValues) >=16:
            result.append(invertHex(stringValues[8:16]))
        if len(stringValues) >= 24:
            result.append(invertHex(stringValues[16:24]))
        if len (stringValues) >= 32:
            result.append(invertHex(stringValues[24:32]))
        
    elif is64Bit():
        stringValues = re.sub(' ', '', objString [18:53])
        if len(stringValues) >= 16:
            result.append(invertHex(stringValues[0:16]))
        if len(stringValues) >= 32:
            result.append(invertHex(stringValues[16:32]))

    else:
        result = None

    return result

def extractString(objString):
    if is32Bit():
        return re.sub(' ', '', objString[10:45])
    elif is64Bit():
        return re.sub(' ', '', objString[18:53])
    else:
        return None

def getSymTab():
    symTab = []
    symCrcTab = []
    i = 0
    j = 0
    for symSection in symSections:
        crcSection = crcSections[i]
        symStrings = re.split('\n', rawReadSection(symSection))[4:-1]
        crcStrings = re.split('\n', rawReadSection(crcSection))[4:-1]

        for objString in symStrings:
            hexValues = extractHexValues(objString)
            if len(hexValues) >= 2:
                symTab.append((hexValues[0], hexValues[1]))
            if len(hexValues) >= 4:
                symTab.append((hexValues[2], hexValues[3]))

        for objString in crcStrings:
            hexValues = extractHexValues(objString)
            for x in xrange(0, len(hexValues)):
                if j < len(symTab) and len(hexValues):
                    symCrcTab.append((symTab[j][0], symTab[j][1], invertHex(hexValues[x]), symSection))
                    j += 1

    return symCrcTab

def getStrings():
    finalString = ''
    rawStrings = re.split('\n', rawReadSection(stringSection))[4:-1]
    initAddress = re.split('[ ]+', rawStrings[0])[1]
    for objString in rawStrings:
        finalString += extractString(objString)
    return initAddress, hexToChar(finalString)

def getSymbolsWithNames():
    symTab = getSymTab()
    strings = getStrings()
    stringOffset = hexToInt(strings[0])
    string = strings[1]
    finalTab = []

    for sym in symTab:
        """ 0: address of symbol
            1: address of name
            2: crc
            3: section in kernel
        """
        index = hexToInt(sym[1]) - stringOffset
        symbolName = ''
        while ord(string[index]) != 0:
            symbolName += string[index]
            index += 1
        finalTab.append((symbolName, sym[0], sym[2], sym[3]))

    return finalTab

for sym in getSymbolsWithNames():
    print sym