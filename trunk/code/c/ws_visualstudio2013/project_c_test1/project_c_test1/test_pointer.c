

#include <stdio.h>



void test_pointer(){
	int *** p_p_p;
	int ** p_p;
	int * p; 
	int test_p =10000; 
	p = &test_p; 
	p_p = &p;
	p_p_p = &p_p;

	//ָ���ά�����ָ�����������
	int (*p_array_2)[2]; // ������������� int *p_array_2[2], ��Ϊ[]�����ȼ�����* 


	printf("the int value %d , address %p .\n", test_p, &test_p);
	printf("the pointer p value %p , address %p .\n", p, &p);
	printf("the pointer p_p_p value %p .\n", p_p_p);



}