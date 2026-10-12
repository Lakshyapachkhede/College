/*
Write a C program to implement Descending priority queue using ordered linked list
(descending order sorted linked list).
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


Node *enqueue(Node *head, int data)
{

    Node *new = getNode(data);

    if (new == NULL)
        return head;


    if (head == NULL || data > head->data)
    {
        printf("%d inserted into priority queue.\n", data);
        new->next = head;
        return new;
    }



    Node *ptr = head;
    while (ptr->next != NULL && ptr->next->data >= data)
        ptr=ptr->next;


   
    new->next = ptr->next;
    ptr->next = new;

    printf("%d inserted into priority queue.\n", data);

    return head;
}


Node *dequeue(Node *head)
{
    if(head == NULL)
    {
        printf("Queue Underflow! Queue is empty.\n");
        return NULL;
    }

    int tmp = head->data;
    Node *temp = head->next;

    printf("%d deleted (Highest Priority).\n", tmp);

    free(head);

    return temp;
}


void traverse(Node *head)
{
    if(head == NULL)
    {
        printf("Queue is Empty.\n");
    }

    Node *ptr = head;

    printf("Queue Elements:");
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
            head = enqueue(head, data);
            break;

        case 2:
            head = dequeue(head);
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
