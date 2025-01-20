# Strings 

name = "Lakshya"      # String declared with double quotes
name2 = 'lakshya'     # String declared with single quotes
                      # Multiline string using triple quotes
name3 = '''lakshya                 
pachkhede
'''

# _____________________________________ ACCESSING STRING ELEMENTS ________________________________

print (name[0])     # Accessing character by index; outputs 'L'

# name[1] = 'a'       # Strings are immutable in Python; this line would cause an error

# ____________________________________ STRING OPERATIONS ______________________________________

print ("-" * 4)          # Repetition of a string; outputs '----'

print (name + name2)     # Concatenation of two strings; outputs 'Lakshyalakshya'

print (name2[1:4])       # Slicing the string; outputs 'aks' (characters from index 1 to 3)

print ("a" in name)      # Membership operator to search for 'a' in 'name'; outputs True


# ___________________________________ BUILT-IN FUNCTIONS ______________________________________

print (len(name))       # Returns the length of the string; outputs 7

print (min(name))       # Returns the smallest character based on Unicode value; outputs 'L'

print (max(name))       # Returns the largest character based on Unicode value; outputs 'y'


# _________________________________ STRING METHODS ___________________________________________

print (name.upper())                    # Converts string to uppercase; outputs 'LAKSHYA'

print (name.lower())                    # Converts string to lowercase; outputs 'lakshya'

print (name.swapcase())                 # Swaps case of all characters; outputs 'lAKSHYA'

print (name.isupper())                  # Checks if all characters are uppercase; outputs False

print (name.islower())                  # Checks if all characters are lowercase; outputs False

print (name.isalnum())                  # Checks if all characters are alphanumeric (letters or numbers); outputs True

print (name.isnumeric())                # Checks if all characters are numeric; outputs False

print (name3.title())                   # Capitalizes the first character of each word; outputs 'Lakshya Pachkhede'

print (name2.capitalize())              # Capitalizes the first character of the first word; outputs 'Lakshya'

print (name.endswith('a'))              # Checks if the string ends with 'a'; outputs True

print (name.startswith('l'))            # Checks if the string starts with 'l'; outputs False

date = '23/23/23'
print (date.split('/'))                 # Splits the string by '/' and returns a list; outputs ['23', '23', '23']

print (name.count(''))                  # Returns the number of empty substrings (length + 1); outputs 8

print (name.find('k'))                  # Returns the first occurrence index of 'k'; outputs 2

print (name.index('k'))                 # Similar to find(), but raises an error if not found; outputs 2
