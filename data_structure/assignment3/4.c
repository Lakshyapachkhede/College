/*
Write a C program to implement an Ascending Order priority queue using arrays in which
elements are inserted in incoming order but deletion of smallest priority element will be done
first. Elements having the same priority are processed according to FIFO manner. Consider
data values as priority.
*/


#include <stdio.h>
#define MAX 5


int pq[MAX];
int n = 0;

void insert()
{
    if(n == MAX)
    {
        printf("OVERFLOW\n");
        return;
    }


    int data;
    printf("Enter data: ");
    scanf("%d", &data);

    int i = n - 1;

    pq[n] = data;
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

    int minIndex = 0;
    for (int i = 0; i < n; i++)
    {
        if(pq[i] < pq[minIndex])
            minIndex = i;
    }
    
    printf("Deleted element: %d\n", pq[minIndex]);

    for (int i = minIndex; i < n -1 ; i++)
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

    printf("Priority Queue: ");

    for (int i = 0; i < n; i++)
    {
        printf("%d ", pq[i]);
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