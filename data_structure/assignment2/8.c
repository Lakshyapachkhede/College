/*
Write a program to Implement both recursive and iterative solutions for
Factorial and Fibonacci problems. Compare their execution time and
memory usage and summarize your observations.
*/

#include <stdio.h>
#include <time.h>

int factorial_r(int n)
{   
    if (n < 0)
        return -1;
    if(n == 1 || n == 0)
        return 1;
    
    return n * factorial_r(n - 1);
}

int factorial_i(int n)
{
    if (n < 0)
        return -1;

    int fact = 1;

    for (int i = 2; i <= n; i++)
    {
        fact *= i;
    }
    
    return fact;
}


int fibonacci_r(int n)
{   
    if(n < 0)
        return -1;

    if(n == 0)
        return 0;

    if (n == 1)
        return 1;

    return fibonacci_r(n - 1) + fibonacci_r(n - 2);
}

int fibonacci_i(int n)
{

    if (n < 0)
        return -1;
    if (n == 0)
        return 0;

    int a = 0, b = 1;
    for (int i = 0; i < n-1; i++)
    {
        int c = a + b;
        a = b;
        b = c;
    }

    return b;    
}

// void measure_time(int (*func)(int), int x)
// {
//     clock_t start_time, end_time;
//     double time_taken;

//     start_time = clock();

//     func(x);

//     end_time = clock();

//     time_taken = (double)(end_time - start_time) / CLOCKS_PER_SEC;

//     printf("Time Used By Function: %f seconds\n", time_taken);

// }

int main()
{

    printf("Factorial 5: %d\n", factorial_r(5));
    printf("Factorial 5: %d\n", factorial_i(5));

    printf("Fibonacci 5: %d\n", fibonacci_r(5));
    printf("Fibonacci 5: %d\n", fibonacci_i(5));

    return 0;
}