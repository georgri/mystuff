#!/bin/sh

EXTRACT_VMLINUZ="./extract-from-vmlinuz.sh"
TEMP_DIR="temp"

while [ -a "$1" ]; do
    if [ -n "`file "$1" | grep "Debian binary package"`" ]; then
        rm -rf "$TEMP_DIR"
        dpkg -x "$1" "$TEMP_DIR"
        "$EXTRACT_VMLINUZ" "$TEMP_DIR"/boot/vmlinuz*
    fi;
    shift
done
