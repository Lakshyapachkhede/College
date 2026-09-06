/*
Q1. Write a menu-driven C program to implement a linear queue using an array. Implement
following operations:
(i) Insert an element (enqueue).
(ii) Delete an element (dequeue).
(iii) Display all elements.
(iv) Display the front element.
(v) Display total number of elements in the queue.
Appropriately handle overflow and underflow conditions.
*/

#include <stdio.h>
#define MAX 100

int queue[MAX];
int front = -1;
int rear = -1;

void enqueue()
{
    int value;

    if(rear == MAX - 1)
    {
        printf("OVERFLOW\n");
        return;

    }

    printf("Enter element: ");
    scanf("%d", &value);

    if(front == -1)
        front = 0;

    queue[++rear] = value;
}

void dequeue()
{
    if(front == -1)
    {
        printf("UNDERFLOW");
        return;
    }

    printf("Deleted element: %d\n", queue[front++]);

    if (front > rear)
    {
        front = -1;
        rear = -1;
    }
    
}

void display()
{

    if(front == -1)
    {
        printf("Queue is empty.\n");
        return;
    }

    printf("Queue elements: ");

    for (int i = front; i <= rear; i++)
    {
        printf("%d ", queue[i]);
    }
     
    printf("\n");
    
}

void display_front()
{
    if(front == -1)
    {
        printf("Queue is empty\n");
    }
    else 
    {
        printf("Front element: %d\n", queue[front]);
    }
}

void count()
{
    if(front == -1)
    {
        printf("Total elements: 0\n");
    }
    else
    {
        printf("Total elements: %d\n", rear - front + 1);
    }
}

int main()
{
    int c;
    while (1)
    {
        printf("\nMenu\nEnqueue(1)\nDequeue(2)\nDisplay(3)\nDisplay Front(4)\nCount(5)\nExit(6)\nEnter Choice: ");
        scanf("%d", &c);

        switch (c)
        {
        case 1:
            enqueue();
            break;
        case 2:
            dequeue();
            break;
        case 3:
            display();
            break;
        case 5:
            count();
            break;
        case 4:
            display_front();
            break;
        case 6:
            return 0;
        default:
            printf("Invalid choice!\n");
        }
    }

    return 0;
}