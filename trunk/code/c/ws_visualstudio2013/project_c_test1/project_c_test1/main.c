

/*
* Copyright (c) 1992-2010 by Sadu.Young.  ALL RIGHTS RESERVED.
* Consult your license regarding permissions and restrictions.
* @Since 1.0.0  */


#include <ctype.h>
#include <stdio.h>
#include <assert.h>
#include <conio.h>
#include <stdbool.h>
#include "test_guess.h"
#include "test_choice.h"
#include "test_array.h"
#include "test_pointer.h"




int main(){
	char ch;

	//while ((ch = getchar()) != '#')  {
	//	putchar(ch);
	//	//fprintf(stdin, "which you input is %s \n", ch);
	//	//fprintf(stderr, "which you input is %s \n",ch);
	//	//fprintf(stderr, "which you input is %s \n",ch);
	//	//fprintf(stdout, "which you input is %s \n",ch);
	//}

	/*while ((ch = getchar()) != EOF){
		putchar(ch);
	}*/

	//guess();
	//test_choice();
	//test_array();
	test_pointer();

	system("pause");
	return 0;
}



