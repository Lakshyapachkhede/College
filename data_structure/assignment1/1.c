/*
Q1. Write a program to Store N integers in an Array.
(i) Print them in reverse order and
(ii) Search for a given element.
*/

#include <stdio.h>

int main()
{
    int N;
    printf("Enter number of integers: ");
    scanf("%d", &N);

    int array[N];

    printf("Enter %d integers (space seprated): ", N);
    for (int i = 0; i < N; i++)
    {  
        scanf("%d", &array[i]);
    }


    printf("Printing in reverse order: ");
    for (int i = N - 1; i >= 0; i--)
    {
        printf("%d ", array[i]);
    }

    printf("\n");


    int x;
    printf("Enter element to search: ");
    scanf("%d", &x);

    int index = -1;
    for (int i = 0; i < N; i++)
    {
        if(array[i] == x)
        {

            index = i;
            break;
        }
    }
    
    if (index != -1)
            printf("Element %d found at index %d", x, index);
    else
        printf("Element %d not found", x);
      

    return 0;
}