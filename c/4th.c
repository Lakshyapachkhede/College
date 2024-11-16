// Input in c

#include <stdio.h>

int main(){
    // lets make a program to input a number from user

    int a; // variable to store input

    printf("Enter a number: ");
    // message to tell user to enter a value this is not neccessary without this the program expects to enter a value 

    scanf("%d", &a); 
    // this will take a int from user 
    // the use of %d is same as in printf
    // the & before a is address of operator. just write it i will not discuss this

    

    // now we can use a in our program
    // example: adding 5 to a and output the value

    printf("%d", a + 5);

    return 0;
}
