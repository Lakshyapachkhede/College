/*
Q6. A bookstore stores the following details of a Book:
• Book ID
• Title
• Author
• Price
• Quantity Available
Write a program using Structures to:
(i) Display the details of the most expensive book.
(ii) Calculate the total value of the inventory (Price × Quantity).
(iii) Display books with quantity less than 5.
*/

#include <stdio.h>
#define MAX_LEN 100

typedef struct
{
    int id;
    char title[MAX_LEN];
    char author[MAX_LEN];
    float price;
    int quantity;
} Book;

void display_book(Book book)
{
    printf("Id: %d\n", book.id);
    printf("Title: %s\n", book.title);
    printf("Author: %s\n", book.author);
    printf("Price: %0.2f\n", book.price);
    printf("Quantity: %d\n", book.quantity);
}

int main()
{
    int N;
    printf("Enter number of books: ");
    scanf("%d", &N);

    if (N <= 0)
    {
        printf("Invalid number of books.\n");
        return 1;
    }

    Book books[N];

    for (int i = 0; i < N; i++)
    {
        printf("Enter Book(%d) :\n", i + 1);

        printf("id: ");
        scanf("%d", &books[i].id);

        printf("title: ");
        scanf(" %99[^\n]", books[i].title);

        printf("author: ");
        scanf(" %99[^\n]", books[i].author);

        printf("price: ");
        scanf("%f", &books[i].price);

        printf("quantity: ");
        scanf("%d", &books[i].quantity);
    }

    // (i) Display the details of the most expensive book.

    int max = 0;
    for (int i = 0; i < N; i++)
    {
        if (books[i].price > books[max].price)
            max = i;
    }

    printf("\n\nMost Expensive Book:\n");
    display_book(books[max]);

    //(ii) Calculate the total value of the inventory (Price × Quantity).

    float total = 0.0f;
    for (int i = 0; i < N; i++)
        total += (books[i].price * books[i].quantity);

    printf("\n\nTotal value of inventory: %f\n", total);

    //(iii) Display books with quantity less than 5.
    printf("\n\nBooks with quantify less than 5: \n");
    int found = 0;
    for (int i = 0; i < N; i++)
    {
        if (books[i].quantity < 5)
        {
            display_book(books[i]);
            found = 1;
        }
    }
    if (!found)
        printf("No books with quantity less than 5.\n");

    return 0;
}
