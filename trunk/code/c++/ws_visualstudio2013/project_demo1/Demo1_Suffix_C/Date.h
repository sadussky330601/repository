

#include <stdio.h>

struct  Date
{
	int year;
	int month;
	int day; 

	void   toString(void){
		printf("DATE: %d-%d-%d", year, month, day);
	}

	void (*p)(void) ;
	
};