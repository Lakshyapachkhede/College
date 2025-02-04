# Regular expression

# +----------------------------------------------+-------------------------+
# | Pattern to Search                            | Regex                   |
# +----------------------------------------------+-------------------------+
# | A single digit                               | \d                      |
# | One or more digits                           | \d+                     |
# | Any non-digit character                      | \D                      |
# | A single word character (alphanumeric or _)  | \w                      |
# | One or more word characters                  | \w+                     |
# | Any non-word character                       | \W                      |
# | A single whitespace character                | \s                      |
# | One or more whitespace characters            | \s+                     |
# | Any non-whitespace character                 | \S                      |
# | A word boundary                              | \b                      |
# | Start of the string                          | ^                       |
# | End of the string                            | $                       |
# | Any single character (except newline)        | .                       |
# | Zero or more of the preceding character      | *                       |
# | One or more of the preceding character       | +                       |
# | Zero or one of the preceding character       | ?                       |
# | Exactly n occurrences of a character         | {n}                     |
# | At least n occurrences                       | {n,}                    |
# | Between n and m occurrences                  | {n,m}                   |
# | Any character in a set                       | [abc]                   |
# | Any character not in a set                   | [^abc]                  |
# | Alternative choices (OR)                     | a\|b                    |
# | Literal match for a special character        | \ (e.g., \. for .)      |
# +----------------------------------------------+-------------------------+


$string = "learning perl is powerful";



# MATCHING OPERATOR:
if ($string =~ /perl/) {            # returns true if found you can also use !~ NEGATED MATCHING OPERATOR
    print "Found\n";
} else {
    print "Not Found\n";
}



# MATCH OPERATOR                    # same as =~
$string =~ m/pattern/;




# SUBSTITUTION OPERATOR:
$string =~ s/perl/python/;         # substituting perl with python (changing perl to python)
print "$string\n";




# TRANSLITERATION OPERATOR
$string =~ tr/e/a/;                 # transforming each e it string to a
print "$string\n";



my @matches = "hello1 ho2w a3re you 4" =~ /\d/g;    # get all matches
print "@matches\n";

$text = "abc123";

if ($text =~ /^\d/)  {
    print "start with digit\n";
} else {
    print "Does not start with digit\n"
}

if ($text =~ /\d$/ ) {
    print "End with digits\n"
} 



# search words starting with vowels
$string = "Apple Orange Banana pinepapple";

while($string =~ /\b[AEIOUaeiou]\w*/g)
{
    print ("$&\n");
}



# case insensitive matching 

$message = "Hello World";

if (!($message =~ /world/)) {
    print "world not found in $message\n";
}


if ($message =~ /world/i) {
    print "world found in $message\n";
}


# common patterns

# date matching 

$date = "13/11/2007";
$date2 = "29.07.2005";
$date3 = "29-07-2005";

if ($date =~ /\d+[\.\/-]\d+[\.\/-]\d+/ )
{
    print "valid date\n"
}
if ($date2 =~ /\d+[\.\/-]\d+[\.\/-]\d+/ )
{
    print "valid date\n"
}
if ($date3 =~ /\d+[\.\/-]\d+[\.\/-]\d+/ )
{
    print "valid date\n"
}



# check a phone number 

$number = "7869616950";

if ($number =~ /\d{10}/) {
    print "Valid phone number\n";
}




