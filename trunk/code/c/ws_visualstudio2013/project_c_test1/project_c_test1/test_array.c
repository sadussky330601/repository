


#include <stdio.h>
#define ARRAY_1  3 
#define ARRAY_2  5 
#define ARRAY_3  10 
#define START "---------------------------------------"
void test_array(){
	int test[][5][10] = {
		{ { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
		{ 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 },
		{ 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 },
		{ 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 } },
		{ { 101, 102, 103, 104, 105, 106, 107, 108, 109, 110 } },
		{ { 201, 202, 203, 204, 205, 206, 207, 208, 209, 210 } }
	};

	int * p_test = test;
	printf("test pointer is %p: \n", p_test);
	printf("test pointer p_test value is : %d \n", *p_test);

	for (size_t f = 0; f < ARRAY_1; f++)
	{
		for (size_t s = 0; s < ARRAY_2; s++)
		{

			printf("ARRAY_3 with [%d][%d]"START"\n", f, s);

			for (size_t t = 0; t < ARRAY_3; t++)
			{
				printf("%d ,", *(p_test + f *ARRAY_2 *  ARRAY_3 + s * ARRAY_3 + t));
			}
			printf("\n");
			//printf("\nARRAY_3"START"\n");
		}

	}


}

//void test_array_vla(int row, int col, int arrays[row][col]){
//
//}

