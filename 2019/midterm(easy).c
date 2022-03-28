#include <stdio.h>
int i=0;

void Hanoi(int disk, int left, int middle , int right){
   if(disk==1)
    {       printf("The upmost disk in rod %d is moved to rod %d .\n ", left, middle);
            i++; 
            printf("The upmost disk in rod %d is moved to rod %d .\n ", middle, right);
            i++;
    } 
else  { 
		Hanoi(disk - 1, left, middle, right); 
		i++; 
		printf("The upmost disk in rod %d is moved to rod %d .\n ", left, middle);
                Hanoi(disk - 1, right, middle, left);
                printf("The upmost disk in rod %d is moved to rod %d .\n ", middle, right); 
                Hanoi(disk -1 , left , middle ,right);		
                i++; 
      } 
} 
int main(void){
     int disk;
     scanf("%d",&disk);
     Hanoi(disk,1,2,3);
     printf("Thus , the minimal number of moves is %d ¿‘¥œ¥Ÿ. \n ", i); 
     return 0;
} 