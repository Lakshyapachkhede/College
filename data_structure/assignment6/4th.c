/*
Write a C program to implement a Binary Search Tree (BST) using Linked List and perform the
following operations:
1. Insert new nodes into the BST.
2. Perform Inorder traversal.
3. Perform Preorder traversal.
4. Perform Postorder traversal.
5. Search for a given element in the BST
*/


#include <stdio.h>
#include <stdlib.h>

typedef struct Node
{
    int data;
    struct Node *left;
    struct Node *right;
}Node;

Node *getNode(int data)
{
    Node *new = malloc(sizeof(Node));

    new->data = data;
    new->left = NULL;
    new->right = NULL;

    return new;
}


Node *insert(Node *root, int data)
{

    if(root == NULL)
        return getNode(data);

    if(data < root->data)
    {
        root->left = insert(root->left, data);
    }
    else
    {
        root->right = insert(root->right, data);
    }

    return root;
}

void preorder(Node *root)
{
    if(root == NULL)
        return;

    printf(" %d", root->data);
    preorder(root->left);
    preorder(root->right);
}


void inorder(Node *root)
{
    if(root == NULL)
        return;

    inorder(root->left);
    printf(" %d", root->data);
    inorder(root->right);
}


void postorder(Node *root)
{
    if(root == NULL)
        return;

    postorder(root->left);
    postorder(root->right);
    printf(" %d", root->data);
}

int search(Node *root, int key)
{

    if(root == NULL)
        return 0;

    if(root->data == key)
        return 1;

    if(key < root->data)
        return search(root->left, key);
    else
        return search(root->right, key);    
}


int main(){
    
    Node *root = NULL;
    int n, val;
    scanf("%d", &n);

    for (int i = 0; i < n; i++)
    {
        scanf("%d", &val);
        root = insert(root, val);
    }

    printf("Inorder Traversal:");
    inorder(root);
    printf("\n");

    printf("Preorder Traversal:");
    preorder(root);
    printf("\n");

    printf("Postorder Traversal:");
    postorder(root);
    printf("\n");

    scanf("%d", &val);
    if(search(root, val))
        printf("Element %d is found in the BST\n", val);
    else
        printf("Element %d is not found in the BST\n", val);


    return 0;
}
