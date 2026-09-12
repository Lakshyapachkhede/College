/*
Q4. Attendance of N students over M days is represented using a 2-D array (1 = Present, 0 = Absent).
Write a program to:
(i) Find and print the attendance percentage of each student.
(ii) Identify the student(s) with 100% attendance.
(iii) Find the day with the lowest attendance
*/

#include <stdio.h>

int main()
{
    int N = 6, M = 5;

    // rows = students
    // cols = days

    int attendence[6][5] = {
        {1, 0, 1, 0, 1},
        {1, 1, 1, 1, 1},
        {1, 0, 0, 1, 0},
        {0, 1, 0, 1, 0},
        {1, 1, 1, 1, 1},
        {1, 0, 0, 1, 0}};

    printf("|%-10s |%-15s|\n", "Student" ,"Attendence(%)");

    for (int i = 0; i < N; i++)
    {
        float count = 0.0f;

        for (int j = 0; j < M; j++)
        {
            count += attendence[i][j];
        }

        printf("|%-10d |%-14.2f |\n", i + 1, (count / M) * 100.0f);
    }


    printf("\n\nStudent(s) with 100%% attendence:\n");
    for (int i = 0; i < N; i++)
    {
        int count = 0;

        for (int j = 0; j < M; j++)
        {
            count += attendence[i][j];
        }

        if(count == M)
            printf("Student: %d\n", i+1);
    }
    

    printf("\n\nDay with lowest attendence: ");
    int min = N + 1;
    int minDay = -1;
    for (int j = 0; j < M; j++)
    {
        int count = 0;
        for (int i = 0; i < N; i++)
        {
            count += attendence[i][j];
        }

        if(count < min)
        {
            min = count;
            minDay = j;
        }


    }

    printf("Day %d", minDay + 1);
    





    return 0;
}
