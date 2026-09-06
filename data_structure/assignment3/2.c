/*
Q2.Write a menu driven C program to implement circular queue using an array. Implement
following operations while correctly handling overflow and underflow conditions:
(i) enqueue
(ii) dequeue
(iii) display
(iv) count
(v) qempty
(vi) qfull
*/

#include <stdio.h>

#define MAX 10

int queue[MAX];
int front = -1, rear = -1;

int qempty()
{
    return front == -1;
}

int qfull()
{
    return (rear + 1) % MAX == front;
}

void enqueue()
{
    if (qfull())
    {
        printf("OVERFLOW\n");
        return;
    }

    int value;
    printf("Enter element :");
    scanf("%d", &value);

    if (qempty())
    {
        front = 0;
        rear = 0;
    }
    else
    {
        rear = (rear + 1) % MAX;
    }

    queue[rear] = value;

    printf("Element %d inserted", value);
}

void dequeue()
{
    if (qempty())
    {
        printf("UNDERFLOW\n");
        return;
    }

    int value = queue[front];
    if (front == rear)
    {
        front = -1;
        rear = -1;
    }
    else
    {
        front = (front + 1) % MAX;
    }

    printf("Deleted element: %d\n", value);
}

void display()
{
    if (qempty())
    {
        printf("Queue is empty\n");
        return;
    }

    printf("Queue elements: ");
    int i = front;

    while (1)
    {
        printf("%d ", queue[i]);
        if (i == rear)
            break;

        i = (i + 1) % MAX;
    }
    printf("\n");
}

void count()
{
    if (qempty())
    {
        printf("Total elements: 0\n");
    }
    else if (rear >= front)
    {
        printf("Total elements: %d\n", rear - front + 1);
    }
    else
    {
        printf("Total elements: %d\n", MAX - front + rear + 1);
    }
}

int main()
{
    int c;
    while (1)
    {
        printf("\nMenu\nEnqueue(1)\nDequeue(2)\nDisplay(3)\nDisplay count(4)\nQempty(5)\nqFull(6)\nExit(7)\nEnter Choice: ");
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

        case 4:
            count();
            break;

        case 5:
            if (qempty())
                printf("Queue is empty\n");
            else
                printf("Queue is not empty\n");
            break;

        case 6:
            if (qfull())
                printf("Queue is full\n");
            else
                printf("Queue is not full\n");
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