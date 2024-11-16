// write a c program to find sum of two numbers a and b
#include <stdio.h>

int main(){
    int a, b, sum;

    printf("Enter two numbers: ");
    scanf("%d%d", &a, &b);

    sum = a + b;

    printf("%d", sum);
    
    return 0;
}
