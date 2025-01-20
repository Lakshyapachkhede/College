print "Enter your age: ";
$age = <>;
chomp ($age);

if ($age >= 65) {
    print "\U drive carefully watch your sight\n";
} elsif ($age >= 18) {
    print "\U you can drive\n";
} else {
    print "\U you can not drive\n";
}

#unless opposite of if
my $valid = 0;
unless ($valid) {
    print "Invalid input\n";
}


my $age = 20;
my $status = ($age >= 18) ? "Adult" : "Minor";
print "Status: $status\n";


print "You passed!\n" if $marks > 40;


my $age = 25;
if ($age > 18 && $age < 30) {
    print "You are a young adult.\n";
}


my $status = "student";
if ($status eq "student" || $status eq "teacher") {
    print "You have access to the library.\n";
}


my $active = 0;
if (!$active) {
    print "The account is inactive.\n";
}




