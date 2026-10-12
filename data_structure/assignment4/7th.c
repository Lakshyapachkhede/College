/*
Write a C program to implement Circular Linked List. Implement following operations:
(i) Insert a new node at the Beginning of the List.
(ii) Delete a node from the beginning of the list and display information.
(iii) Insert a new node at the End of the List.
(iv) Delete a node from the end of the list and display information.
(v) Traverse and Display all elements of the list.
(vi) Count number of elements present in the list.
Appropriately handle overflow and underflow conditions.
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


Node *insertBegin(Node *head, int data)
{
    Node *new = getNode(data);
    if(new == NULL) return head;

    if(head == NULL)
    {
        printf("%d inserted at beginning.\n", data);
        new->next = new;
        return new;
    }

    new->next = head;

    Node *ptr = head;
    do
    {
        ptr = ptr->next;
    } while (ptr->next != head);

    ptr->next = new;
    
    printf("%d inserted at beginning.\n", data);
    return new;
}

Node *deleteBegin(Node *head)
{
    if(head == NULL)
    {
        printf("List Underflow! List is empty.\n");
        return NULL;
    }

    if (head->next == head)
    {
        printf("%d deleted from beginning.\n", head->data);
        free(head);
        return NULL;
    }

    int tmp = head->data;
    Node *temp = head->next;

    Node *ptr = head;
    do
    {
        ptr = ptr->next;
    } while (ptr->next != head);

    ptr->next = temp;

    printf("%d deleted from beginning.\n", tmp);

    free(head);

    return temp;
}

Node *insertEnd(Node *head, int data)
{
    Node *new = getNode(data);
    if(new == NULL) return head;

    if(head == NULL)
    {
        new->next = new;
        printf("%d inserted at end.\n", data);
        return new;
    }

    Node *ptr = head;
    do
    {
        ptr = ptr->next;
    } while (ptr->next != head);

    ptr->next = new;

    new->next = head;

    printf("%d inserted at end.\n", data);

    return head;
}

Node *deleteEnd(Node *head)
{
    if(head == NULL)
    {
        printf("List Underflow! List is empty.\n");
        return NULL;
    }
    if(head->next == head)
    {
        printf("%d deleted from end.\n", head->data);
        free(head);
        return NULL;
    }


    Node *ptr = head;
    while (ptr->next->next != head)
    {
        ptr = ptr->next;
    } 

    Node *tmp = ptr->next;

    printf("%d deleted from end.\n", tmp->data);

    ptr->next = head;

    free(tmp);

    return head;
}




void traverse(Node *head)
{
    if(head == NULL)
    {
        printf("List is Empty.\n");
        return;
    }

    Node *ptr = head;

    printf("List Elements:");

    do{
        printf(" %d", ptr->data);
        ptr = ptr->next;
    }while (ptr != head);
    
    printf("\n");
}

int count(Node *head)
{

    if (head == NULL)
    {
        printf("Total number of elements: 0\n");
        return 0;
    }

    Node *ptr = head;
    int count = 0;
    do{
        count++;
        ptr = ptr->next;
    }while (ptr != head);

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
            head = insertBegin(head, data);
            break;

        case 2:
            head = deleteBegin(head);
            break;

        case 3:
            scanf("%d", &data);
            head = insertEnd(head, data);
            break;

        case 4:
            head = deleteEnd(head);
            break;


        case 5:
            traverse(head);
            break;

        case 6:
            count(head);
            break;

        case 7:
        printf("Program terminated.");
            return 0;
        
        default:
            break;
        }
    }
    return 0;
}
