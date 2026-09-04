/*
Q5. Prices of items in a shopping cart are stored in an array. Write a program to:
(i) Find the total bill.
(ii) Identify the costliest item.
(iii) Apply a 10% discount to items costing more than ₹1000.
(iv) Display the updated bill.
*/

#include <stdio.h>

int main()
{
    int N;
    printf("Enter number of items: ");
    scanf("%d", &N);

    float prices[N];

    for (int i = 0; i < N; i++)
    {
        printf("Enter item %d price: ", i + 1);
        scanf("%f", &prices[i]);
    }

    float max = 0, total = 0;
    int maxItem = -1;

    for (int i = 0; i < N; i++)
    {
        total += prices[i];
        if (prices[i] > max)
        {
            max = prices[i];
            maxItem = i;
        }
    }

    printf("\n\nTotal Bill : Rs.%.2f\n", total);
    printf("\n\nItem number %d is costliest and its price: %0.2f\n", maxItem + 1, max);


    total = 0;

    for (int i = 0; i < N; i++)
    {
        if (prices[i] > 1000.0f)
        {
            prices[i] -= (prices[i] / 10.0f);
        }

        total += prices[i];
    }
    
    printf("\n\nDiscounted Bill: %0.2f", total);



}