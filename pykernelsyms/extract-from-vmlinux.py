import re
import shlex
import subprocess
import sys

Usage = """
Vmlinux kernel symbols and crcs extractor
Usage:
python extract-from-vmlinux.py <vmlinux_file>
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

def rawReadSection(sectionName):
    objdumpCmd = 'objdump -j ' + sectionName +' -s ' + getInputFileName()
    objdumpProcess = subprocess.Popen(shlex.split(objdumpCmd), stdout = subprocess.PIPE, stderr=subprocess.PIPE)
    objdumpOutput = objdumpProcess.stdout.read()
    objdumpErr = objdumpProcess.stderr.read()
    if objdumpProcess.wait() != 0:
        raise Exception(ErrObjdumpErr + objdumpErr)
    return objdumpOutput

def invertHex(hex):
    return hex[6:8] + hex[4:6] + hex[2:4] + hex[0:2]

def validHex(hex):
    return len(hex) == 8 and re.match('^[0-9abcdef]*$', hex)

def hexToInt(hex):
    result = 0
    for char in hex:
        result = result * 16 + hexDict[char]
    return result

def hexToChar(hex):
    isFirst = True
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
            hexValues = re.split('[ ]+', objString)
            if len(hexValues) >=4:
                if validHex(hexValues[2]) and validHex(hexValues[3]):
                    symTab.append((invertHex(hexValues[2]), invertHex(hexValues[3])))
            if len(hexValues) >= 6:
                if validHex(hexValues[4]) and validHex(hexValues[5]):
                    symTab.append((invertHex(hexValues[4]), invertHex(hexValues[5])))

        for objString in crcStrings:
            hexValues = re.split('[ ]+', objString)
            for x in xrange(2,6):
                if j < len(symTab) and len(hexValues) >= x + 1 and validHex(hexValues[x]):
                    symCrcTab.append((symTab[j][0], symTab[j][1], invertHex(hexValues[x]), symSection))
                    j += 1

    return symCrcTab

def getStrings():
    finalString = ''
    rawStrings = re.split('\n', rawReadSection(stringSection))[4:-1]
    initAddress = re.split('[ ]+', rawStrings[0])[1]
    for objString in rawStrings:
        finalString += re.sub(' ', '', objString[10:45])
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