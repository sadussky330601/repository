

#include <stdio.h>
char get_choice();
void count(void);
char get_frist(void);
int get_int2();

 
/**
#################################
###
###this is discarded ............
void dibs(x, y, z) int x, y, z; {

}
void dibs(x, y, z)
int x;
int y;
int z; {

}
*/

void test_choice(){
	/** function prototype  can be here . */
	int get_int();

	int choice = 0;
	while ((choice = get_choice()) != 'q')
	{
		switch (choice)
		{
		case 'a':
			printf("Buy low,sell high! \n");
			break;
		case 'b':
			putchar('\a');
			break;
		case 'c':
			count();
			break;
		default:
			printf("Program error!");
			break;
		}
	}
}

char get_choice(){
	int ch;
	printf("Enter the letter fo your choice \n");
	printf("a. advice b. bell \n");
	printf("c. count  q. quit \n");
	ch = get_frist();
	while (ch != 'a' && ch != 'b' && ch != 'c' &&  ch != 'q')
	{
		printf("Please respond with a,b,c,q,\n");
		ch = get_frist();
	}
	return ch;
}

void count(){
	int n, i;
	printf("Count how far ? Enter an integer:\n");
	n = get_int2();
	printf("you have input integer with %d \n, postion is %p \n " ,n ,&n);
	//printf("you have input integer with %d \n", n);
	for (i = 1; i <= n; i++)
	{
		printf("%d \n", i);
	}
	while (getchar() != '\n')
		continue;

}

char get_frist(void){
	int ch;
	ch = getchar();
	while (getchar() != '\n')
		continue;
	return ch;
}

int get_int(){
	int input;
	char ch;
	while (scanf_s("%d", &input) != 1){
		while ((ch = getchar() != '\n'))
		{
			putchar(ch);//delete error input .
		}
		printf("is not an integer,try agian .integer just like 22 ,-21,0 \n");
	}
	return input;
}

int get_int2(){
	int input; 
	printf("please inupt a integer.such as 2.\n");
	while (( scanf_s("%d", &input)) != 1)
	{
		scanf_s("%*s");
		printf("please inupt a integer.such as 2.\n");
	}
	return input;
}