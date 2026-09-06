/*
Write a program to convert an infix arithmetic expression into its equivalent
postfix expression using stack operations while considering operator
precedence and associativity.
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

int precedence(char c)
{
    switch (c)
    {
    case '^':
        return 3;

    case '*':
    case '/':
        return 2;

    case '+':
    case '-':
        return 1;

    default:
        return 0;
    }
}

int main()
{
    char exp[MAX];
    printf("Enter expression: ");
    scanf(" %99[^\n]", exp);

    char result[MAX];
    int r_idx = 0;

    for (int i = 0; exp[i] != '\0'; i++)
    {
        if (exp[i] == '(')
            push('(');

        else if (exp[i] == ' ')
        {
            ;
        }
        else if (exp[i] == ')')
        {
            while (TOP != -1 && peek() != '(')
            {
                result[r_idx++] = pop();
            }
            if (TOP != -1)
                pop();
        }
        else if ((exp[i] >= '0' && exp[i] <= '9') || (exp[i] >= 'a' && exp[i] <= 'z') || (exp[i] >= 'A' && exp[i] <= 'Z'))
            result[r_idx++] = exp[i];

        else
        {
            while (TOP != -1 && peek() != '(' &&
                   (precedence(peek()) > precedence(exp[i]) ||
                    (precedence(peek()) == precedence(exp[i]) && exp[i] != '^')))
            {
                result[r_idx++] = pop();
            }

            push(exp[i]);
        }
    }

    while (TOP != -1)
    {
        result[r_idx++] = pop();
    }

    result[r_idx] = '\0';

    printf("Postfix Expression: %s", result);

    return 0;
}
