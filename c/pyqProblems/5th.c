// write a c program to print
// *
// * * 
// * * *


#include <stdio.h>

int main(){

    int n;
    printf("Enter number of rows: ");
    scanf("%d", &n);

    for (int i = 1; i <= n; i++)
    {
        for (int j = 0; j < i; j++)
        {
            printf("* ");
        }
        
        printf("\n");

    }
    
    return 0;
}
