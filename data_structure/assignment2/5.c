/*
Write a program that uses a stack to reverse a given string and display the
reversed string.
*/


#include <stdio.h>
#define MAX 100

char stack[MAX];
int TOP = -1;

void push(char data)
{
    if (TOP == MAX - 1)
    {
        printf("OVERFLOW\n");
        return;
    }
    stack[++TOP] = data;
}

char pop()
{
    if (TOP == -1)
    {
        // printf("UNDERFLOW\n");
        return '@';
    }
    return stack[TOP--];
}

char peek()
{
    if (TOP == -1)
    {
        // printf("UNDERFLOW\n");
        return '@';
    }
    return stack[TOP];
}




int main()
{
    char str[MAX];
    printf("Enter string: ");
    scanf(" %99[^\n]", str);


    for (int i = 0; str[i] != '\0'; i++)
    {
        push(str[i]);
    }


    int k = 0;
    while (TOP != -1)
    {
        str[k++] = pop();
    }
    
    
    printf("Reversed String: %s", str);

    return 0;
}
