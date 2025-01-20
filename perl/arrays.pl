@array = (1,2,3);
@array2 = qw/This is a array/;
@array3 = (1..2);
@array4 = (a..z);

$array[3] = 4;
print "array: @array\n";
print "array: @array2\n";
print "array: @array3\n";
print "array: @array4\n";

#size
$size = @array;
print "array4 size:", scalar @array, "\n";
print "array size: $size\n";


#get max index
$max_index = $#array;

print "maxindex: $max_index\n";


#insertion and deletion in array

@names = ("Ram", "Shyam", "Amit", "kumar");

push(@names, "Lakshya"); # add at end

print "@names\n";

unshift(@names, "Yash"); # add at start

print "@names\n";

pop(@names); #delete from end
shift(@names); #delete from start

print "@names\n";

#slicing array elemenst
@names2 = @names[1,2];

print "@names2\n";

#splice
@nums = (1..20);
print "Before - @nums\n";

splice(@nums, 5, 5, 21..25); #replace from 5th to 10th index with provided 21..25
print "After - @nums\n";



# convert string to array
$string = "Hello-I-am-Lakshya";

@arrayString = split('-', $string);

print "@arrayString\n";

# convert array to string 

$newString = join('-', @arrayString);

print "$newString\n";


# sort array
@nums = (1, 389, 3293, 382094, 23211);
@nums = sort(@nums);

$len = @array; #get length

print "@nums\n";

print "len: $len\n";


#merging two arrays
@newARray = (@nums, @names)