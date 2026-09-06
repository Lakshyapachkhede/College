/*
Write a C program to simulate a hospital emergency department using a priority queue. Each
patient should have:
• Patient ID
• Patient name
• Patient Age
• Severity level
Patients with higher severity should be treated first. If two patients have the same severity,
the patient who arrived earlier should be treated first.
*/

#include <stdio.h>

#define MAX 10

typedef struct
{
    int id;
    char name[50];
    int age;
    int level;
} Patient;

Patient pq[MAX];
int n = 0;

void addPatient()
{
    if (n == MAX)
    {
        printf("Cannot add more patients\n");
        return;
    }

    printf("Enter patient id: ");
    scanf("%d", &pq[n].id);

    printf("Enter patient name: ");
    scanf(" %[^\n]", pq[n].name);

    printf("Enter patient age: ");
    scanf("%d", &pq[n].age);

    printf("Enter patient level: ");
    scanf("%d", &pq[n].level);

    n++;

    printf("Patient added successfully\n");
}

void deletePatient()
{
    if (n == 0)
    {
        printf("No Patients in queue\n");
        return;
    }

    int maxIndex;

    for (int i = 0; i < n; i++)
    {
        if (pq[i].level > pq[maxIndex].level)
            maxIndex = i;
    }

    printf("\n Patient Deleted\n");
    printf("ID: %d\n", pq[maxIndex].id);
    printf("name: %s\n", pq[maxIndex].name);
    printf("age: %d\n", pq[maxIndex].age);
    printf("level: %d\n", pq[maxIndex].level);

    for (int i = maxIndex; i < n - 1; i++)
    {
        pq[i] = pq[i + 1];
    }

    n--;
}

void displayPatients()
{
    if (n == 0)
    {
        printf("No Patients in queue\n");
        return;
    }

    printf("All Patients: \n");
    for (int i = 0; i < n; i++)
    {
        printf("Patient %d\n", i + 1);
        printf("ID: %d\n", pq[i].id);
        printf("name: %s\n", pq[i].name);
        printf("age: %d\n", pq[i].age);
        printf("level: %d\n", pq[i].level);
    }
    printf("\n");
}



int main()
{
    int c;
    while (1)
    {
        printf("\nMenu\nAdd Patient(1)\nTreat Patient(2)\nAll Patients(3)\nExit(4)\nEnter Choice: ");
        scanf("%d", &c);

        switch (c)
        {
        case 1:
            addPatient();
            break;

        case 2:
            deletePatient();
            break;

        case 3:
            displayPatients();
            break;

        case 4:
            printf("Exiting...\n");
            return 0;
        default:
            printf("Invalid choice!\n");
        }
    }

    return 0;
}