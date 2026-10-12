/*
Write a program to implement a linear Linked List using static memory allocation. Implement
following operations:
(i) Insert a new node at the Beginning of the List.
(ii) Delete a node from the beginning of the list and display information.
(iii) Traverse and display all the elements of the list.
Appropriately handle overflow and underflow conditions.
*/

#include <stdio.h>

#define MAX 5
#define DATA 0
#define NEXT 1



int MEMORY[MAX][2];
int AVAIL;
int HEAD;


void init()
{
    for (int i = 0; i < MAX - 1; i++)
    {
        MEMORY[i][DATA] = -1;
        MEMORY[i][NEXT] = i+1;
    }
    MEMORY[MAX-1][NEXT] = -1;

    AVAIL = 0;
    HEAD = -1;
}

void traverse()
{

    printf("List elements:");

    int tmp = HEAD;
    while (tmp != -1)
    {
        printf(" %d", MEMORY[tmp][DATA]);
        tmp = MEMORY[tmp][NEXT];
    }
    printf("\n");
}

void insert(int data)
{
    int new = AVAIL;
    if(new == -1)
    {
        printf("List Overflow! Cannot insert %d.\n", data);
        return;
    }

    AVAIL = MEMORY[AVAIL][NEXT];

    MEMORY[new][DATA] = data;

    MEMORY[new][NEXT] = HEAD;

    HEAD = new;

    printf("%d inserted at beginning.\n", data);
}

int delete()
{
    if(HEAD == -1)
    {
        printf("UNDERFLOW\n");
        return -1;
    }

    int tmp = MEMORY[HEAD][DATA];

    MEMORY[HEAD][DATA] = -1;

    HEAD = MEMORY[HEAD][NEXT];

    printf("%d deleted from beginning.\n", tmp);

    return tmp;
}






int main()
{

    init();

    int choice, data;
    while (1)
    {
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            scanf("%d", &data);
            insert(data);
            break;
        case 2:
            delete();
            break;
        case 3:
            traverse();
            break;
        case 4:
        printf("Program terminated.");
            return 0;
        
        default:
            break;
        }
    }
    return 0;
}