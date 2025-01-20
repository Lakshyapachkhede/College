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


import re

string = "Learing python is powerful"
result = re.search(r'python', string)

if (result):
    print ("match found", result.span())
else: 
    print ("match not found")



string = re.sub(r'python', "perl", string)
print (string)


matches = re.findall(r"\d", "hello1 ho2w a3re you 4")
print (", ".join(matches))


dates = """

22/12/2022
1-11-2004
"""

matches = re.findall(r'\d{1,2}[-/]\d{1,2}[-/]\d{4}', dates)
print (', '.join(matches))
                            