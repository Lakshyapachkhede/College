// example of recursion
// program to find factorial of a number

#include <stdio.h>

int factorial(int n)
{   
    // negative check
    if (n < 0)
        return -1;

    // base case
    if (n == 0 || n == 1)
        return 1;

    // recursive case
    return n * factorial(n - 1);
}


int main(){

    int a;
    printf("Enter number to find factorial: ");
    scanf("%d", &a);

    printf("The factorial is %u", factorial(a));

    return 0;
}
