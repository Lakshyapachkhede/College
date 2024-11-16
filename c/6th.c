// use of if else in c

#include <stdio.h>

int main(){
    // if is used to execute a block of code if a condition in true (will understand in a second)
    // example: if weather is good i will go to park

    // we will denote good with 1 and bad with 0 just for example

    int weather = 1;
    
    if (weather == 1){ 
        printf("I will go to park.");
    }

    // in the above example since the condition (weather == 1 ) is true the output will be "I will go to park"
    // but what if we have to do something if the condition in the if is not true
    // we will use an else block 
    // the else will run only if the condition is not true

    else {
        printf("I will stay at home.");
    }

    // now change the value of weather to 0 and see what happens


    return 0;
}
