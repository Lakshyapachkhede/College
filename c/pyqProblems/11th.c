// write a program to swap two numbers

#include <stdio.h>

int main(){
    int a, b, temp;

    printf("Enter two numbers a and b: ");
    scanf("%d%d", &a, &b);

    // swapping a and b
    temp = a;
    a = b;
    b = temp;

    printf("After swapping:\na=%d and b=%d", a, b);



    return 0;
}
