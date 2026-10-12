/*
Write a C program to implement Stack using linear linked list. Implement Push, Pop, Traverse,
and Count functions.
*/


#include <stdio.h>
#include <stdlib.h>


typedef struct Node
{
    int data;
    struct Node *next;
}Node;


Node *getNode(int data)
{
    Node *new = malloc(sizeof(Node));
    if(new == NULL)
    {
        printf("OVERFLOW\n");
        return NULL;
    }

    new->data = data;
    new->next = NULL;

    return new;
}


Node *push(Node *head, int data)
{
    Node *new = getNode(data);
    new->next = head;
    printf("%d pushed into the stack.\n", data);
    return new;
}

Node *pop(Node *head)
{
    if(head == NULL)
    {
        printf("Stack Underflow! Stack is empty.\n");
        return NULL;
    }

    int tmp = head->data;
    Node *temp = head->next;

    printf("%d popped from the stack.\n", tmp);

    free(head);

    return temp;
}

void traverse(Node *head)
{
    if(head == NULL)
    {
        printf("Stack is Empty.\n");
    }

    Node *ptr = head;

    printf("Stack Elements:");
    while (ptr != NULL)
    {
        printf(" %d", ptr->data);
        ptr = ptr->next;
    }
    
    printf("\n");
}

int count(Node *head)
{
    Node *ptr = head;
    int count = 0;
    while (ptr != NULL)
    {
        count++;
        ptr = ptr->next;
    }
    
    printf("Total number of elements: %d\n", count);
    return count;
    
}

int main()
{

    Node *head = NULL;

    int choice, data, pos, key;
    while (1)
    {
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            scanf("%d", &data);
            head = push(head, data);
            break;

        case 2:
            head = pop(head);
            break;

        case 3:
            traverse(head);
            break;

        case 4:
            count(head);
            break;

        case 5:
        printf("Program terminated.");
            return 0;
        
        default:
            break;
        }
    }
    return 0;
}
