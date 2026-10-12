/*
Write a C program to implement a circular doubly linked list with the following operations:
(i) Insert a node at the beginning of the List.
(ii) Insert a node at the end of the List.
(iii) Insert a node at a specified position in the list.
(iv) Delete a node from the beginning of the List.
(v) Delete a node from the end of the list.
(vi) Delete a node from the specified position of the list.
(vii) Display the elements of the list in forward direction.
(viii) Display the elements of the list in backward direction.
(ix) Search for a given element in the doubly linked list.
Handle appropriate conditions such as an empty list and an invalid position. Ensure that both previous
and next pointers are updated correctly after every insertion and deletion.
*/

#include <stdio.h>
#include <stdlib.h>



typedef struct Node
{
    int data;
    struct Node *next;
    struct Node *prev;
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
    new->prev = NULL;

    return new;
}


int main(){
    
    return 0;
}