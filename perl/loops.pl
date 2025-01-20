# traditional for loop

for ($i = 0; $i < 5; $i++)
{
    print "$i\n";
}


print '-' x 50, "\n";

# for each loop 

@array = (1..5);
foreach my $i (@array) { #you can also use for in place of foreach and results will be same
    print "$i\n";
}


print '-' x 50, "\n";
# while loop
my $i = 0;
while ($i < 5) {
    print "$i\n";
    $i++;
}

print '-' x 50, "\n";

# until loop executes till a condition is false opposite of while loop

$i = 0;

until ($i > 4) {
    print "$i\n";
    $i++;
}


# infinite loop

while (1) {
    print "This is an infinite loop\n";
    last;  # Use `last` to break out of the loop
}

print '-' x 50, "\n";

# break = last and continue = next 

for $i (1..10) {
    last if $i == 3;
    print "$i\n";
}

print '-' x 50, "\n";
for $i (1..10) {
    next if $i % 2 == 0;
    print "$i\n";
}

print '-' x 50, "\n";
my $count = 0;
while ($count < 5) {
    $count++;
    redo if $count == 3;  # Repeats iteration when $count is 3
    print "Count: $count\n";
}


print '-' x 50, "\n";

# nested loops
for my $i (1..10) {
    for my $j (1..10) {
        print "@{[$i * $j]}\t";
    }
    print "\n";
}


print '-' x 50, "\n";



my $count = 0;
do {

    print "$count\n";
    $count++;
}while($count < 3);


print '-' x 50, "\n";
my $count = 0;
do {

    print "$count\n";
    $count++;
}until($count > 3);