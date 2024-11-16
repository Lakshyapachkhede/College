// program to find given number is even or odd

#include <stdio.h>

int main(){
    int n;

    printf("Enter a number: ");
    scanf("%d", &n);

    if (n % 2 == 0)
        printf("Number is even\n");
    else
        printf("number is odd\n");


    return 0;
}
