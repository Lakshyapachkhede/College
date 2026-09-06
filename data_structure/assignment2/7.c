/*
Write a program to solve the Tower of Hanoi problem and demonstrate how
stack concepts can be used to simulate recursive function calls.
*/
#include <stdio.h>

void solve(int s, int d, int h, int n)
{
    if (n == 1)
    {
        printf("moving plate %d from %d to %d\n", n, s, d);
        return;
    }

    solve(s, h, d, n - 1);

    printf("moving plate %d from %d to %d\n", n, s, d);

    solve(h, d, s, n-1);
}

int main()
{
    solve(1, 3, 2, 3);

    return 0;
}