/*
Write a C program to implement Binary Tree using Linked List and perform following operations on
the Tree:
1. Insert nodes into the binary tree.
2. Perform Inorder traversal.
3. Check whether Tree is a Complete Binary Tree.
4. Print height of the Tree.
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
        Node *ptr = queue[front++];
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
        
    }

    return root;
}

void inorder(Node *root)
{
    if(root == NULL)
        return;

    inorder(root->left);
    printf(" %d", root->data);
    inorder(root->right);
}

int height(Node *root)
{
    if(root == NULL)
    {
        return 0;
    }

    int left = 1+height(root->left);
    int right = 1+height(root->right);

    return left>right?left:right;
}

int isComplete(Node *root)
{    
    if(root == NULL)
    {
        return 1;
    }

    Node *queue[100];
    int front = 0, rear = 0;
    int gapFound = 0;

    queue[rear++] = root;

    while (front < rear)
    {
        Node *ptr = queue[front++];

        if(ptr->left != NULL)
        {
            if(gapFound)
                return 0;

            queue[rear++] = ptr->left;            
        }
        else
            gapFound = 1;


        if(ptr->right != NULL)
        {
            if(gapFound)
                return 0;

            queue[rear++] = ptr->right;            
        }
        else
            gapFound = 1;
        
    }
    
    return 1;
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

    if(isComplete(root))
    {
        printf("Tree is a Complete Binary Tree.\n");
    }
    else
    {
        printf("Tree is not Complete Binary Tree.\n");
    }

    printf("Height of Tree: %d", height(root));




    return 0;
}
