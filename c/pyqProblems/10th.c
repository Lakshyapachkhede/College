// write a program to calculate area of triangle

#include <stdio.h>

int main(){
    float b, h, area;

    printf("Enter base of triangle: ");
    scanf("%f", &b);

    printf("Enter height of triangle: ");
    scanf("%f", &h);

    // AREA OF TRIANGLE = 1/2 * b * h
    area = 0.5 * b * h;

    printf("The area is: %0.2f\n", area);

    return 0;
}
