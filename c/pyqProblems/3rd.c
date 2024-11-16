// demonstrate the use of nested if using c program
#include <stdio.h>

int main(){

    int i;

    printf("Enter either 1 or 2: ");
    scanf("%d", &i);

    if (i == 1)
        printf("you entered 1");
    else
    {
        if (i == 2)
            printf("you entered 2");
        else
            printf("you entered other than 1 and 2");
    }
    
    


    return 0;
}
