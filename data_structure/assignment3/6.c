/*
Write a C program to implement two independent stacks using a single array for efficient
memory utilization. Assume that Stack 1 is used to store undo operations and Stack 2 is used
to store redo operations in an application. Stack 1 should grow from the left side of the array
and Stack 2 from the right side. Implement Push, Pop and display operations for both the
stacks and ensure that unused array space can be dynamically shared between the two stacks.
*/

#include <stdio.h>
#define MAX 100

int stack[MAX];

int TOP1 = -1;
int TOP2 = MAX;


void push1(int data)
{
    if(TOP1 == TOP2 - 1)
    {
        printf("OVERFLOW 1\n");
        return;
    }

    stack[++TOP1] = data;
}

void push2(int data)
{
    if(TOP1 == TOP2 - 1)
    {
        printf("OVERFLOW 2\n");
        return;
    }

    stack[--TOP2] = data;
}

void pop1()
{
    if(TOP1 == - 1)
    {
        printf("UNDERFLOW 1\n");
        return;
    }

    printf("%d deleted from stack 1", stack[TOP1--]);
}

void pop2()
{
    if(TOP2 == MAX)
    {
        printf("UNDERFLOW 2\n");
        return;
    }

    printf("%d deleted from stack 2", stack[TOP2++]);
}

void display1()
{
    printf("Stack 1:\n");
    for (int i = TOP1; i >= 0; i--)
    {
        printf("%d ", stack[i]);
    }
    
    printf("\n");
}

void display2()
{   
    printf("Stack 2:\n");
    for (int i = TOP2; i < MAX; i++)
    {
        printf("%d ", stack[i]);
    }
    
    printf("\n");
}

int main()
{
    int c;

    while (1)
    {
        printf("\nMenu\nPush1(1)\nPop1(2)\nDisplay1(3)\nPush2(4)\nPop2(5)\nDisplay2(6)\nExit(7)\nEnter Choice: ");
        scanf("%d", &c);

        switch (c)
        {
        case 1:
            int data;
            printf("Enter data: ");
            scanf("%d", &data);
            push1(data);
            break;

        case 2:
            pop1();
            break;

        case 3:
            display1();
            break;

        case 4:
            int data2;
            printf("Enter data: ");
            scanf("%d", &data2);
            push2(data2);
            break;

        case 5:
            pop2();
            break;

        case 6:
            display2();
            break;

        case 7:
            printf("Exiting...\n");
            return 0;

        default:
            printf("Invalid choice!\n");
        }
    }

    return 0;
}