/*
Write a C program to implement a Binary Search Tree (BST) using Linked List and perform the
following operations:
1. Insert new nodes into the BST.
2. Perform Inorder traversal.
3. Delete a given node from the BST.
4. Print Maximum element in the Tree.
5. Print Minimum element in the Tree.
Display node information of Tree after every Insertion or Deletion operation.
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


void inorder(Node *root)
{
    if(root == NULL)
        return;

    inorder(root->left);
    printf(" %d", root->data);
    inorder(root->right);
}


Node* minimum(Node *root)
{
    if(root == NULL)
    {
        return NULL;
    }

    Node *ptr = root;

    while (ptr->left != NULL)
    {
        ptr = ptr->left;
    }

    return ptr;
}

Node* maximum(Node *root)
{

    if(root == NULL)
    {
        return NULL;
    }


    Node *ptr = root;

    while (ptr->right != NULL)
    {
        ptr = ptr->right;
    }

    return ptr;
}

Node *delete(Node *root, int key)
{
    if(root == NULL) 
    {
        printf("Tree is empty.\n");
        return NULL;
    }

    if(key < root->data)
    {
        root->left = delete(root->left, key);
    }
    else if(key > root->data)
    {
        root->right = delete(root->right, key);
    }
    else
    {
        if(root->left == NULL)
        {
            Node *temp = root->right;
            free(root);
            return temp;
        }
        if(root->right == NULL)
        {
            Node *temp = root->left;
            free(root);
            return temp;
        }

        Node *temp = minimum(root->right);
        root->data = temp->data;

        root->right = delete(root->right, temp->data);
    }

    return root;
}




int main(){
    
    Node *root = NULL;
    int n, val;
    scanf("%d", &n);

    for (int i = 0; i < n; i++)
    {
        scanf("%d", &val);
        root = insert(root, val);
        printf("After Inserting %d:\n", val);
        printf("Inorder Traversal:");
        inorder(root);
        printf("\n");
        printf("Minimum Element: %d\n", minimum(root)->data);   
        printf("Maximum Element: %d\n\n", maximum(root)->data); 
    }

    scanf("%d", &val);
    delete(root, val);
    printf("After deleting %d:\n", val);
    printf("Inorder Traversal:");
    inorder(root);
    printf("\n");
    printf("Minimum Element: %d\n", minimum(root)->data);   
    printf("Maximum Element: %d\n", maximum(root)->data); 


    return 0;
}
