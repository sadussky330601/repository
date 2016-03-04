



#ifndef INCLUDE_TEST_HEAD_H
#define INCLUDE_TEST_HEAD_H


#include <stdio.h>

struct  Date
{
	int year;
	int month;
	int day;

	void   toString(void){
		printf("DATE: %d-%d-%d", year, month, day);
	}

	void(*p)(void);

};


#endif /*INCLUDE_TEST_HEAD_H*/