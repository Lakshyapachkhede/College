// program to print largest of three numbers

#include <stdio.h>

int main(){
    int a, b, c;

    printf("Enter three numbers: ");
    scanf("%d%d%d", &a, &b, &c);

    if (a > b)
    {
        if (a > c)
            printf("%d is greatest", a);
        else
            printf("%d is greatest", c);
    }
    else
    {
        if (b > c)
            printf("%d is greatest", b);
        else
            printf("%d is greatest", c);
    }

    return 0;
}
