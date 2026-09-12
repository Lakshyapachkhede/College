/*
Write a C program to implement a priority queue (Descending Order Priority Queue) using an
array. Each element should contain a data value and priority. The element with the highest
priority should be deleted first.
*/

#include <stdio.h>
#define MAX 5

typedef struct 
{
    int data;
    int priority;
}Element;

Element pq[MAX];
int n = 0;

void insert()
{
    if(n == MAX)
    {
        printf("OVERFLOW\n");
        return;
    }

    Element e;

    printf("Enter data: ");
    scanf("%d", &e.data);
    printf("Enter priority: ");
    scanf("%d", &e.priority);

    int i = n - 1;

    while (i >= 0 && pq[i].priority <= e.priority)
    {
        pq[i + 1] = pq[i];
        i--;
    }

    pq[i+1] = e;
    n++;

    printf("Insertion successfull\n");
}

void delete()
{
    if(n == 0)
    {
        printf("UNDERFLOW\n");
        return;
    }

    printf("Deleted data: %d with priority: %d\n", pq[0].data, pq[0].priority);

    for (int i = 0; i < n -1 ; i++)
    {
        pq[i] = pq[i+1];
    }
    
    n--;
}


void display()
{
    if(n == 0)
    {
        printf("Priority Queue is empty\n");
        return;
    }

    printf("Data\tPriority\n");

    for (int i = 0; i < n; i++)
    {
        printf("%d\t%d\n",pq[i].data, pq[i].priority);
    }


    printf("\n");
    
}


int main()
{
    int c;
    while (1)
    {
        printf("\nMenu\nEnqueue(1)\nDequeue(2)\nDisplay(3)\nExit(4)\nEnter Choice: ");
        scanf("%d", &c);

        switch (c)
        {
        case 1:
            insert();
            break;

        case 2:
            delete();
            break;

        case 3:
            display();
            break;

        case 4:
            printf("Exiting...\n");
            return 0;
        default:
            printf("Invalid choice!\n");
        }
    }

    return 0;
}