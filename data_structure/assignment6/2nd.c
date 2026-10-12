/*
Write a C program to implement Binary Tree using Linked List and perform following operations on
the Tree:
1. Insert nodes into the binary tree.
2. Count the total number of nodes in the Tree.
3. Delete a node from the Binary Tree.
4. Perform Level Order Traversal of the Tree.
Display node information of Tree after every Insertion or Deletion operation
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

int count(Node *root)
{
    if(root == NULL) return 0;
    return 1 + count(root->left) + count(root->right);
}

void levelOrder(Node *root)
{
    printf("Level Order:");
    Node *queue[100];
    int front = 0, rear = 0;

    if(root == NULL)
        return;

    queue[rear++] = root;

    while (front < rear)
    {
        Node *ptr = queue[front++];
        printf(" %d", ptr->data);

        if(ptr->left)
        {
            queue[rear++] = ptr->left;
        }


        if(ptr->right)
        {
            queue[rear++] = ptr->right;
        }
                

    }

    printf("\n");
}

Node *delete(Node *root, int key)
{
    if(root == NULL) 
    {
        printf("Tree is empty\n");
        return root;
    }

    Node *queue[100];
    Node *parent[100];

    int front = 0, rear = 0;
    int i, keyIndex = -1;

    queue[rear] = root;
    parent[rear] = NULL;
    rear++;


    while (front < rear)
    {
        Node *ptr = queue[front];
        if(ptr->data == key && keyIndex == -1)
        {
            keyIndex = front;
        }

        front++;

        if(ptr->left != NULL)
        {
            queue[rear] = ptr->left;
            parent[rear] = ptr;
            rear++;
        }
        if(ptr->left != NULL)
        {
            queue[rear] = ptr->right;
            parent[rear] = ptr;
            rear++;
        }

    }

    if(keyIndex == -1)
    {
        printf("%d Key not found\n", key);
        return root;
    }

    Node *toDelete = queue[keyIndex];
    Node *deepest = queue[rear-1];
    Node *deepestParent = parent[rear-1];

    if(deepest == root)
    {
        free(root);
        return NULL;
    }

    toDelete->data = deepest->data;

    if(deepestParent->left == deepest)
        deepestParent->left = NULL;
    else
        deepestParent->right = NULL;

    
    free(deepest);

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
        levelOrder(root);
        printf("Total Nodes: %d\n\n", count(root));

    }

    scanf("%d", &val);
    delete(root, val);
    printf("After deleting %d:\n", val);
    levelOrder(root);
    printf("Total Nodes: %d\n\n", count(root));



    return 0;
}
