# print function is used to output text

print ("Hello, World!")                                                   

print ("I failed math so many times at school,\n I can't even count.")  # \n is a special character used to insert new line


# input function is used to input text

name = input ("Enter your name: ")              # input function always take input as string 

print ("your name is: ", name)


x = input ("Enter a number: ")
# y = x + 3                                     # this will give an error because x is string

x = int (x)                                     # typecasting (changing type) string to int 

y = x + 3                                       # this works fine

print (y)

