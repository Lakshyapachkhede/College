/*
Write a program to evaluate a postfix arithmetic expression containing
single-digit operands and standard arithmetic operators using a stack.
*/


#include <stdio.h>
#include <math.h>
#define MAX 100

int stack[MAX];
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

int pop()
{
    if (TOP == -1)
    {
        // printf("UNDERFLOW\n");
        return __INT_MAX__;
    }
    return stack[TOP--];
}

int peek()
{
    if (TOP == -1)
    {
        // printf("UNDERFLOW\n");
        return __INT_MAX__;
    }
    return stack[TOP];
}

int do_operation(int a, int b, char op)
{
    switch (op)
    {
    case '+':
        return a + b;
    case '-':
        return a - b;
    case '*':
        return a * b;
    case '/':
        return a / b;
    case '^':
        return pow(a, b);
    default:
        return -1;
    }
}

int main()
{
    char exp[MAX];
    printf("Enter expression: ");
    scanf(" %99[^\n]", exp);
    int a,b;

    

    for (int i = 0; exp[i] != '\0'; i++)
    {
        if(exp[i] >= '0' && exp[i] <= '9')
        {
            int j = i + 1;
            int n = exp[i] - '0';
            while (exp[j] >= '0' && exp[j] <= '9')
            {
                n *= 10;
                n += exp[j] - '0';
                j++;
            }
            
            push(n);
            i = j - 1;
            
        }
        else if(exp[i] == ' ') {;}
        else
        {
            a = pop();
            b = pop();
            push(do_operation(b, a, exp[i]));
        }
    }

    printf("Result :%d\n", peek());

    return 0;
}
