/*
Q2. Write a program using function to count even and odd elements in an array
*/

#include <stdio.h>

void count_even_odd(int array[], int n)
{

    int even = 0, odd = 0;
    for (int i = 0; i < n; i++)
    {
        if(array[i] % 2 == 0) 
            even++;
        else 
            odd++;
    }
    
    printf("Count of Even Numbers: %d\n", even);
    printf("Count of Odd Numbers: %d\n", odd);

}

int main()
{

    int n = 10;
    int array[10] = {1,2,3,4,5,6,7,8,9,10};
    count_even_odd(array, n);


    return 0;
}