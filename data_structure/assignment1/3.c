/*
Q3. Write a program to convert a string to uppercase using function.
*/
#define MAX 100

#include <stdio.h>
#include <string.h>


void upper(char str[])
{

    for (int i = 0; str[i] != '\0'; i++)
    {
        if(str[i] >= 'a' && str[i] <= 'z')
        {
            str[i] = 'A' + (str[i] - 'a');
        }
    }
    
}


int main()
{   
    
    char str[MAX];

    printf("Enter string: ");
    scanf(" %[^\n]", str);

 

    upper(str);
    
    printf("Uppercase String: %s", str);

    return 0;
}