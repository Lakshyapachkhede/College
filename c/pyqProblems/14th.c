// write a program to print all odd numbers in between 1 to 50 using loop

#include <stdio.h>

int main(){
    printf("Odd numbers between 1 and 50 are: \n");
    for (int i = 1; i <= 50; i+=2)
    {
        printf("%d\n", i);
    }
    
    return 0;
}
