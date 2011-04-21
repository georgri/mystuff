#!/usr/bin/perl
#
# extract_initramfs.pl: extracts the vmlinux out of a packed kernel image
#
# Copyright (C) Martin Schlemmer <azarah@nosferatu.za.org>
#
# Released under the terms of the GNU GPL
#

use strict;
use warnings;

use Cwd;
use Encode;
use IO::File;
use IO::Pipe;
use IO::Uncompress::Gunzip qw (gunzip $GunzipError);

if (! defined ($ARGV[0]) && ! defined ($ARGV[1])) {
			print "usage: $0 VMLINUZ [VMLINUX]\n";
				exit (1);
}

if (! -f $ARGV[0]) {
			print "$ARGV[0] does not exists\n";
				exit (1);
}

my ($input,$vm_output) = @ARGV;

$vm_output = cwd . '/vmlinux',
	if (! defined ($vm_output) || $vm_output eq '');

eval {
	my $fh = IO::File->new;

	$fh->open ($input) or die ($!);
	$fh->binmode;

	my $data;
	my $count = 0;

	do {
		$data = '';

		$fh->seek ($count++, 0) or die ($!);
		$fh->read ($data, 3) or die ($!);

	} until ($data eq "\x1F\x8B\x08" || $fh->eof);

	if ($data ne "\x1F\x8B\x08" || $fh->eof) {
		print STDERR "Could not find GZIP marker!\n";
		exit (1);
	}

	$fh->seek (--$count, 0) or die ($!);

	$data = '';

	while (! $fh->eof) {
		my $buffer;

		$fh->read ($buffer, 2048) or die ($!);
		$data .= $buffer;
	}
	$fh->close;

	my $image_data;

	gunzip \$data => \$image_data,
        or die ("Gunzip failed: $GunzipError\n");

	$fh->open ($vm_output, '>') or die ($!);
	$fh->binmode;
	$fh->write ($image_data) or die ($!);
	$fh->close;

	my $have_ramfs = 0;
	my $pipe = IO::Pipe->new or die ($!);

	$pipe->reader ('objdump', '-h', $vm_output) or die ($!);

	$pipe->close;
};
if ($@) {
	printf STDERR "$@\n";
	exit (1);
}

exit (0);

