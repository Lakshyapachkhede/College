/*
Write a C program to dynamically create a polynomial using a linked list. Accept the coefficient and
exponent of each term from the user, create nodes containing coefficient, exponent, and address of next
node. Display the polynomial. The terms should be stored in the list in descending order of exponent
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct Node
{
    int coef;
    int exp;
    struct Node *next;
}Node;

Node *getNode(int coef, int exp)
{
    Node *new = malloc(sizeof(Node));
    if(new == NULL) return NULL;
    new->next = NULL;
    new->coef = coef;
    new->exp = exp;

    return new;
}

Node *addTerm(Node *head, int coef, int exp)
{
    Node *new= getNode(coef, exp);
    if(new == NULL)
    {
        printf("OVERFLOW\n");
        return head;
    }


    if(head == NULL || exp > head->exp)
    {
        new->next = head;
        return new;
    }

    Node *ptr = head;
    while (ptr->next != NULL && exp < ptr->next->exp)
    {
        ptr = ptr->next;
    }
    
    new->next = ptr->next;
    ptr->next = new;

    return head;
}

void display(Node *head)
{
    if(head == NULL)
    {
        printf("Empty\n");
        return;
    }

    printf("Polynomial:");
    Node *ptr = head;
    while (ptr != NULL)
    {
        printf(" %dx^%d", ptr->coef, ptr->exp);
        if(ptr->next) printf(" +");
        ptr = ptr->next;
    }

    printf("\n");
    
}


int main(){
    
    Node *head = NULL;
    int n, coef, exp;
    scanf("%d", &n);

    for (int i = 0; i < n; i++)
    {
        scanf("%d %d", &coef, &exp);
        head = addTerm(head, coef, exp);
    }

   display(head);
    

    return 0;
}
