/*
Q8. Write a program to define a structure Rectangle containing: Length and Breadth.
Allocate memory dynamically and calculate:
(i) Area of Rectangle
(ii) Perimeter of Rectangle
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct 
{
    int l;
    int b;
}Rectangle;

int main()
{
    Rectangle *r = malloc(sizeof(Rectangle));

    printf("Enter length of rectangle: ");
    scanf("%d", &r->l);
    printf("Enter bredth of rectangle: ");
    scanf("%d", &r->b);


    int area = r->l * r->b;
    int perimeter = 2 * (r->l + r->b);

    printf("\n\nArea of Rectangle: %d square units\n", area);
    printf("\n\nPerimeter of Rectangle: %d units\n", perimeter);

    free(r);

    return 0;   
}
