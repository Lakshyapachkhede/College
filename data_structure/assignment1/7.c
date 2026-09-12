/*
Q7. Define a structure named Employee to store the following details:
• Employee ID
• Employee Name
• Salary
Write a C program to dynamically allocate memory for a single employee record using
pointers. Read the employee details and display:
1. Employee information
2. Annual salary of the employee
Release the dynamically allocated memory after completing the operation.
*/


#include <stdio.h>
#include <stdlib.h>
#define MAX_LEN 100

typedef struct 
{
    int id;
    char name[MAX_LEN];
    float salary;
}Employee;

int main()
{
    Employee *emp = malloc(sizeof(Employee));

    printf("Enter id: ");
    scanf("%d", &emp->id);

    printf("Enter name: ");
    scanf(" %99[^\n]", emp->name);

    printf("Enter salary: ");
    scanf("%f", &emp->salary);

    printf("\n\nEmployee Information:\n");
    printf("Id: %d\n", emp->id);
    printf("Name: %s\n", emp->name);
    printf("Salary: %.2f\n", emp->salary);

    printf("\n\nAnnual Salary: %0.2f", emp->salary * 12);


    free(emp);

    return 0;
}
