/*
Write a program that uses a stack to determine whether the parentheses,
braces, and brackets in a given arithmetic expression are balanced. The
program should display whether the expression is balanced or not balanced.
*/

#include <stdio.h>
#define MAX 100

char stack[MAX];
int TOP = -1;

void push(int data)
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
    char exp[MAX];
    printf("Enter expression: ");
    scanf(" %99[^\n]", exp);
    int balanced = 1;
    for (int i = 0; exp[i] != '\0'; i++)
    {
        if (exp[i] == '(' || exp[i] == '[' || exp[i] == '{')
            push(exp[i]);

        if (exp[i] == ')' || exp[i] == ']' || exp[i] == '}')
        {
            if (TOP == -1)
            {
                balanced = 0;
                break;
            }

            if ((exp[i] == ')' && peek() != '(') || (exp[i] == ']' && peek() != '[') || (exp[i] == '}' && peek() != '{'))
            {

                balanced = 0;
                break;
            }
            pop();
        }
    }

    if (TOP != -1)
    {
        balanced = 0;
    }

    if (balanced)
    {
        printf("Balanced\n");
    }
    else
    {
        printf("Not balanced\n");
    }

    return 0;
}