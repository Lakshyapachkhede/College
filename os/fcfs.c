#include <stdio.h>
#include <stdlib.h>

typedef struct process
{
    int number;
    int arrivalTime;
    int burstTime;
    
}process;

void input(const char* message, const char* format , void *var){
    printf("%s", message);
    scanf(format, var);
}


void sort(process* arr, int n)
{
    for (int i = 0; i < (n-1); i++)
    {
        int didSwap = 0;
        for (int j = 0; j < (n-i-1); j++)
        {          
            if(arr[j].arrivalTime > arr[j+1].arrivalTime){
                process p1 = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = p1;

                didSwap = 1;
            }
        }

        if(!didSwap){
            break;
        }
        
    }
}



void fcfs()
{   
    int numProcess;

    input("Enter number of processes: ", "%d", &numProcess);
    printf("%d", numProcess);

    process* processArray = (process*) malloc(sizeof(process) * numProcess);

    char messageBuffer[100];


    for (int i = 0; i < numProcess; i++)
    {   
        processArray[i].number = i + 1;
        sprintf(messageBuffer, "Enter arrival time for process p%d: ", i);
        input(messageBuffer, "%d", &processArray[i].arrivalTime);

        sprintf(messageBuffer, "Enter burst time for process p%d: ", i);
        input(messageBuffer, "%d", &processArray[i].burstTime);
    }


    sort(processArray, numProcess);







    printf("%-15s %-15s %-15s\n", "Process", "Arrival Time", "Burst Time");

    for (int i = 0; i < numProcess; i++)
    {   
        printf("p%-14d %-15d %-15d\n", processArray[i].number, processArray[i].arrivalTime, processArray[i].burstTime);
    }


}


int main(){
    fcfs();
    return 0;
}
