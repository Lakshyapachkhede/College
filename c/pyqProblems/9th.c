// function example
#include <stdio.h>

// function to calculate sum of two numbers
int sum(int a, int b)
{
    return a + b;
}


int main(){

    int result = sum(32, 21);
    printf("The sum is: %d", result);

    return 0;
}













































/**
 * @brief Calculates the sum of two given numbers.
 * 
 * This function computes the sum of two numbers.
 * The sum of two numbers `a` and `b` is calculated as:
 * - a + b
 * 
 * @param a The first number.
 * @param b The second number.
 * 
 * @return int The sum of `a` and `b`.
 * 
 * 
 * @example
 * int result = sum(2, 3);
 * printf("Sum of 2, 3 is %d\n", result); // Output: 5
 */