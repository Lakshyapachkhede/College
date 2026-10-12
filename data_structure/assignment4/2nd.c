/*
Write a menu driven C program to implement a linear Linked List using dynamic memory
allocation. Implement following operations:
(i) Insert a new node at the Beginning of the List.
(ii) Delete a node from the beginning of the list and display information.
(iii) Insert a new node at the End of the List.
(iv) Delete a node from the end of the list and display information.
(v) Insert a new node at the specified position in the List.
(vi) Delete a node from the specified position of the list and display information.
(vii) Insert a node after specified key value of the existing node in the list.
(viii) Delete a node containing specified key value given by the user.
(ix) Traverse and Display all elements of the list.
(x) Count number of elements present in the list.
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
    new->next = head;
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

    int tmp = head->data;
    Node *temp = head->next;

    printf("%d deleted from beginning.\n", tmp);

    free(head);

    return temp;
}

Node *insertEnd(Node *head, int data)
{

    if(head == NULL)
    {
        printf("%d inserted at end.\n", data);
        return getNode(data);
    }

    Node *ptr = head;
    while (ptr->next != NULL)
        ptr=ptr->next;

    ptr->next = getNode(data);
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
    if(head->next == NULL)
    {
        printf("%d deleted from end.\n", head->data);
        free(head);
        return NULL;
    }

    Node *ptr = head;
    while (ptr->next->next != NULL)
        ptr = ptr->next;
    
    Node *tmp = ptr->next;

    printf("%d deleted from end.\n", ptr->data);

    free(tmp);
    ptr->next = NULL;

    return head;
}

//1 based index
Node *insertPos(Node *head, int data, int pos)
{

    if (pos < 1)
    {
        printf("Invalid position!\n");
        return head;
    }

    if (pos == 1)
        return insertBegin(head, data);


    int count = 1;
    Node *ptr = head;
    while (ptr != NULL && count < pos - 1)
    {
        ptr=ptr->next;
        count++;
    }

    if (ptr == NULL)
    {
        printf("Position Does not Exist\n");
        return head;
    }


    Node *new = getNode(data);
    new->next = ptr->next;
    ptr->next = new;
    printf("%d inserted at position %d.\n", data, pos);

    return head;
}

Node *deletePos(Node *head, int pos)
{
    
    if(head == NULL)
    {
        printf("List Underflow! List is empty.\n");
        return NULL;
    }

    if (pos < 1)
    {
        printf("Invalid position!\n");
        return head;
    }

    if (pos == 1)
        return deleteBegin(head);


    int count = 1;
    Node *ptr = head;
    while (ptr != NULL && count < pos - 1)
    {
        ptr=ptr->next;
        count++;
    }

    if (ptr == NULL || ptr->next == NULL)
    {
        printf("Position Does not Exist\n");
        return head;
    }

    Node* tmp = ptr->next;

    ptr->next = tmp->next;

    printf("%d deleted from position %d.\n", tmp->data, pos);
    free(tmp);

    return head;

}

Node *insertAfter(Node *head, int data, int key)
{
    Node *ptr = head;
    while (ptr != NULL && ptr->data != key)
    {
        ptr=ptr->next;
    }

    if(ptr == NULL)
    {
        printf("Key not found\n");
        return head;
    }


    Node *new = getNode(data);
    new->next = ptr->next;
    ptr->next = new;

    printf("%d inserted after key %d.\n", data, key);
    return head;


}

Node *deleteKey(Node *head, int key)
{
    if (head == NULL)
    {
        printf("List Underflow! List is empty.\n");
        return NULL;
    }

    if (head->data == key)
        return deleteBegin(head);


    Node *ptr = head;
    while (ptr != NULL && ptr->next->data != key)
    {
        ptr=ptr->next;
    }

    if(ptr->next == NULL)
    {
        printf("Key not found\n");
        return head;
    }


    Node *tmp = ptr->next;
    ptr->next = tmp->next;
    printf("%d deleted from the list.\n", tmp->data);
    free(tmp);

    return head;

}

void traverse(Node *head)
{


    if(head == NULL)
    {
        printf("List is Empty.\n");
    }

    Node *ptr = head;

    printf("List Elements:");
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
            scanf("%d %d", &data, &pos);
            head = insertPos(head, data, pos);
            break;
                
        case 6:
            scanf("%d", &pos);
            head = deletePos(head, pos);
            break;
                
        case 7:
            scanf("%d %d", &key, &data);
            head = insertAfter(head, data, key);
            break;
                
        case 8:
            scanf("%d", &key);
            head = deleteKey(head, key);
            break;

        case 9:
            traverse(head);
            break;

        case 10:
            count(head);
            break;

        case 11:
        printf("Program terminated.");
            return 0;
        
        default:
            break;
        }
    }
    return 0;
}
