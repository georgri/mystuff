#!/bin/sh

EXTRACT_VMLINUZ="./extract-from-vmlinuz.sh"
TEMP_DIR="temp"

if [ -a "$1"] && [ file "$1" | grep "Debian binary package" ]; then
	dpkg -x "$1" "$TEMP_DIR"
	"$EXTRACT_VMLINUX" "$TEMP_DIR"/boot/vmlinuz*
fi
