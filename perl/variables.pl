#scalars

$x = 32; #integer
$y = -20; #negative
$pi = 3.14; #float
$bigFloat = 3.13E-22; #big float

$octal = 010;#decimal 8
$hex = 0x10;

$name = "Lakshya Pachkede"; #string

print "Pi is $pi\n";
print "x is $x\n";
print "name is $name\n";

#arrays

@nums = (10, 20, 30, "lakshya");
@nums2 = (1..4); # range
print "nums is @nums\n";
print "nums is @nums2\n";

print "nums[0] = $nums[0]\n";
print "nums[3] = $nums[3]\n";


#hashes
%student = ('Lakshya', 25, 'Yash', 63);
print "student = %student\n";
print "student lakshya roll no $student{'Yash'}\n";


my %hash = (
    "key1" => "value1",
    "key2" => "value2",
);

print "hash{'key1'} = $hash{'key1'}\n";

my %person = (
    'name' => 'Lakshya Pachkhede',
    'age' => 18,
    'branch' => 'CSE'
);

$person{'age'} = 17;
$person{'city'} = 'vidisha';

print "$person{'age'}, $person{'city'}\n";

delete $person{'city'}

