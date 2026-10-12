/*
Write a C program to represent a polynomial using a linked list and evaluate its value for a given value
of x. Each node of the linked list should store the coefficient, exponent, and address of the next node.
The program should create and display the polynomial, accept a value of x from the user, and calculate
the value of the polynomial by evaluating each term. Finally, display the evaluated value of the
polynomial.
Example: For the polynomial 5x³ + 4x² + 2x + 7 and x = 2, the output should be 67.
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
