// explain nesting of for loop with example
#include <stdio.h>

int main(){
    
    // program to print all locations of a 3x3 matrix

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("%d%d ", i, j);
        }
        printf("\n");
    }
       

    return 0;
}



// main( )
// {
//  int r, c, sum ;
//  for ( r = 1 ; r <= 3 ; r++ ) /* outer loop */
//  {
//  for ( c = 1 ; c <= 2 ; c++ ) /* inner loop */
//  {
//  sum = r + c ;
//  printf ( "r = %d c = %d sum = %d\n", r, c, sum ) ;
//  }
//  }
// }