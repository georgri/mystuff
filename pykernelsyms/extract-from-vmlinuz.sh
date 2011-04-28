#!/bin/sh

VMLINUX_FILE="/tmp/vmlinux"

if [ ! -f "$1" ]; then
	echo "Usage: extract-from-vmlinuz.sh <vmlinuz_file>"
	exit 1
fi

perl ./extract-vmlinux.pl "$1" "$VMLINUX_FILE" &&
		python ./extract-from-vmlinux.py "$VMLINUX_FILE"
