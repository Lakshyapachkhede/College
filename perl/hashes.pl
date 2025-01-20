my %hash = (
    "name" => "lakshya",
    "age" => 23,
    "city" => "vidisha",
);


my @keys = keys %hash; #returns array of keys
my @values = values %hash; #returns array of values

print "@keys : @values\n";


while ((my $key, $value) = each %hash){
    print "$key: $value\n";

}

# check if a key is present 
if (exists $hash{'name'}) {
    print "key is present\n";
}

delete $hash{'name'};

if (exists $hash{'name'}) {
    print "key is present\n";
} else {
    print "key is not present\n";
}