sub add {
    my ($a, $b) = @_;
    return $a + $b;
}

print add (2, 3) . "\n"; # or add(2,3); parentheses are optional


sub greet {
    my $name = shift || "Guest";  # Use "Guest" if no argument is provided
    print "Hello, $name!\n";
}
greet();         # Output: Hello, Guest!
greet("Alice");  # Output: Hello, Alice!

sub factorial {
    my ($n) = @_;
    return 1 if $n == 0;
    return $n * factorial($n - 1);
}
print factorial(5);  # Output: 120