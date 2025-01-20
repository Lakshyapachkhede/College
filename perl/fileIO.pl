# file io

# open(FILEHANDLE | Variable, MODE, FILENAME) or die "Error message: $!";
# close(FILEHANDLE);


# _____________________________________READING A FILE____________________________________________
open (my $fh, '<', "temp.txt") or die "Cannot open file :$!";
while (my $line = <$fh>) 
{
    # print $line;
}

close ($fh);

# ___________________________________WRITING A FILE________________________________________
open(my $fh, '>', 'output.txt') or die "Cannot open file: $!";
print $fh "Hello, World!\n";
close($fh);


# ___________________________________ APPENDING A FILE________________________________________
open(my $fh, '>>', 'output.txt') or die "Cannot open file: $!";
print $fh "Hello, World!\n";
close($fh);



# _________________________________ READING FILE LINE BY LINE ___________________________________
open(my $fh, '<', 'output.txt') or die $!;
while (<$fh>) {
    chomp;
    print "Line: $_\n";
}
close($fh);


# ________________________________ READING FILE INTO ARRAY _______________________________________
open(my $fh, '<', 'output.txt') or die $!;
@array = <$fh>;
close($fh);

foreach my $line (@array)
{
    print $line;
}


# Perl provides file test operators to check file attributes before processing:

# -e : Check if a file exists.
# -r : Check if a file is readable.
# -w : Check if a file is writable.
# -s : Check the size of the file.

my $file = "output.txt";
if (-e $file) {
    print "$file exists\n";    
}

