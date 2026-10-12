/*
Write a C program to implement a Circular Header Linked List. The linked list should contain a header
node that stores information about the list, such as the number of nodes. Implement the
following operations:
(i) Insert a new node at the Beginning of the List.
(ii) Delete a node from the beginning of the list and display information.
(iii) Insert a new node at the End of the List.
(iv) Delete a node from the end of the list and display information.
(v) Traverse and Display all elements of the list.
(vi) Display the total number of nodes stored in the header node.
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


void insertBegin(Node *header, int data)
{
    Node *new = getNode(data);

    if(new == NULL) 
    {
        printf("OVERFLOW\n");
        return;
    }

    if(header->data == 0)
    {
        new->next = header;
    }

    new->next = header->next;
    header->next= new;
    header->data++;
    
}

void deleteBegin(Node *header)
{
    if(header->next == header)
    {
        printf("UNDERFLOW\n");
        return;
    }

    Node* tmp = header->next;
    header->next = tmp->next;


    printf("Deleted element: %d\n", tmp->data);

    free(tmp);

    header->data--;

}

void insertEnd(Node *header, int data)
{
    Node *new = getNode(data);

    if(new == NULL) 
    {
        printf("OVERFLOW\n");
        return;
    }


   Node *ptr = header;

   while (ptr->next != header)
   {
        ptr = ptr->next;
   } 
   

   new->next = header;
   ptr->next = new;
   header->data++;
}

void deleteEnd(Node *header)
{
    if(header->next == header)
    {
        printf("UNDERFLOW\n");
        return;
    }



    Node *ptr = header;
    
    while (ptr->next->next != header)
    {
        ptr = ptr->next;
    } 

    Node *tmp = ptr->next;
    ptr->next = header;

    printf("Deleted element: %d\n", tmp->data);
    free(tmp);

    header->data--;
   
}


void traverse(Node *header)
{
    if(header->next == header)
    {
        printf("List is Empty.\n");
        return;
    }

    Node *ptr = header->next;

    printf("List :");

    while (ptr != header)
    {
        printf(" %d", ptr->data);
        ptr = ptr->next;
    }
    
    printf("\n");
}


int main()
{

    Node *header = getNode(0);
    header->next = header;

    int choice, data, pos, key;
    while (1)
    {
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            scanf("%d", &data);
            insertBegin(header, data);
   
            break;

        case 2:
            deleteBegin(header);
            break;

        case 3:
            scanf("%d", &data);
            insertEnd(header, data);
            break;

        case 4:
            deleteEnd(header);
            break;

        case 5:
            traverse(header);
            break;

        case 6:
            printf("Total number of nodes: %d\n", header->data);
        
            break;

        case 7:
            return 0;
        
        default:
            break;
        }
    }



    return 0;
}



