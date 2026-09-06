/*
Q1. Write a program to implement a stack using arrays and perform following
operations:
(i) Push an element onto the stack.
(ii) Pop an element from the stack.
(iii) Display the top element without removing it (Peek).
(iv) Display all elements present in the stack.
(v) Display Count of Number of Elements present in the stack.
Ensure that the program appropriately handles stack overflow and stack
underflow conditions.
*/

#include <stdio.h>
#define MAX 100

int stack[MAX];
int TOP = -1;

void push(int data)
{
    if(TOP == MAX - 1)
    {
        printf("OVERFLOW\n");
        return;
    }
    printf("%d Pushed into stack\n", data);
    stack[++TOP] = data;
}

void pop()
{
    if(TOP == -1)
    {
        printf("UNDERFLOW\n");
        return;
    }
    printf("%d Popped from stack\n", stack[TOP--]);

}

void peek()
{
    if (TOP == -1)
    {
        printf("UNDERFLOW\n");
        return;
    }
    printf("Top Element: %d\n", stack[TOP]);
}

void display()
{   
    printf("Stack: \n");
    for (int i = TOP; i >= 0; i--)
    {
        printf("|%4d|\n", stack[i]);
        printf("------\n");
    }
    
}

void count()
{
    printf("Number of Elements in Stack: %d\n", TOP + 1);
}


int main()
{
    int c;
    while (1)
    {   
        printf("\n\nMenu:\nPush(1)\nPop(2)\nPeek(3)\nDisplay(4)\nCount(5)\nExit(6)\n\nEnter Your Choice: ");
        scanf("%d", &c);
        switch (c)
        {
        case 1:
            int data;
            printf("Enter value to push: ");
            scanf("%d", &data);
            push(data);
            break;
        case 2:
            pop();
            break;
        case 3:
            peek();
            break;
        case 4:
            display();
            break;
        case 5:
            count();
            break;
        case 6:
            printf("Exiting...\n");
            return 0;
        
        default:
            printf("Invalid Input\n");
            break;
        }
    }
    

    return 0;
}