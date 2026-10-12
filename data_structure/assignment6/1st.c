/*
Write a C program to implement Binary Tree using Linked List and perform following operations on
the Tree:
1. Insert nodes into the binary tree.
2. Perform Preorder traversal.
3. Perform Inorder traversal.
4. Perform Postorder traversal
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

    Node *queue[100];
    int front = 0, rear = 0;

    queue[rear++] = root;

    while (front < rear)
    {
        Node *ptr = queue[front];
        if(ptr->left)
        {
            queue[rear++] = ptr->left;
        }
        else
        {
            ptr->left = getNode(data);
            return root;
        }

        if(ptr->right)
        {
            queue[rear++] = ptr->right;
        }
                else
        {
            ptr->right = getNode(data);
            return root;
        }

        front++;
        
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

int main(){
    
    Node *root = NULL;
    int n, val;
    scanf("%d", &n);

    for (int i = 0; i < n; i++)
    {
        scanf("%d", &val);
        root = insert(root, val);
    }

    printf("Preorder Traversal:");
    preorder(root);
    printf("\n");


    printf("Inorder Traversal:");
    inorder(root);
    printf("\n");


    printf("Postorder Traversal:");
    postorder(root);
    printf("\n");



    return 0;
}
