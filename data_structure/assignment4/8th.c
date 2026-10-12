/*
Write a C program to merge two sorted linear linked list into a single sorted linked list.
Implement all required functions to create, display and merge the lists.
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


Node *insertEnd(Node *head, int data)
{
    if(head == NULL)
    {
        return getNode(data);
    }

    Node *ptr = head;
    while (ptr->next != NULL)
        ptr=ptr->next;

    ptr->next = getNode(data);

    return head;
}

void traverse(Node *head)
{
    if(head == NULL)
    {
        printf("List is Empty.\n");
    }

    Node *ptr = head;

    printf("sorted list:");
    while (ptr != NULL)
    {
        printf(" %d", ptr->data);
        ptr = ptr->next;
    }
    
    printf("\n");
}

Node *merge(Node *head1, Node *head2)
{
    Node *dummy = getNode(-1);
    Node *curr = dummy;
    
    while (head1 && head2)
    {
        if(head1->data < head2->data)
        {
            curr->next = head1;
            head1 = head1->next;
        }
        else
        {
            curr->next = head2;
            head2 = head2->next;
        }
        curr = curr->next;
    }

    while (head1)
    {
        curr->next = head1;
        head1=head1->next;
        curr = curr->next;
    }
    
    while (head2)
    {
        curr->next = head2;
        head2=head2->next;
        curr = curr->next;
    }
    
    
    return dummy->next;
}



int main()
{

    Node *head1 = NULL;
    Node *head2 = NULL;
    int n1, n2, data;

    scanf("%d", &n1);

    for (int i = 0; i < n1; i++)
    {
        scanf("%d", &data);
        head1= insertEnd(head1, data);
    }

    printf("First ");
    traverse(head1);


    scanf("%d", &n2);

    for (int i = 0; i < n2; i++)
    {
        scanf("%d", &data);
        head2 = insertEnd(head2, data);
    }

    printf("Second ");
    traverse(head2);

    Node *head3 = merge(head1, head2);

    printf("Merged ");
    traverse(head3);
    
    return 0;
}
