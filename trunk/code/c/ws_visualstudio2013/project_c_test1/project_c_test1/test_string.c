





/*
1. gets() ����ʽ����ǳ����㣬��ȡ���з�֮ǰ(���������з�)�����ַ���
������Щ�ַ��������'\0',Ȼ�������ַ����������õĳ���������ȡ
���з���������

Error	
1	error C4996: 'gets': This function or variable may be unsafe.
Consider using gets_s instead. To disable deprecation, 
use _CRT_SECURE_NO_WARNINGS. See online help for details.	
d:\workspace\sync_github\repository\trunk\code\c\ws_visualstudio2013\
project_c_test1\project_c_test1\test_string.c	20	1	project_c_test1



*/
#include <stdio.h>


#define MAX_INPUT 100 

void test_string_input(){
	char name[90]; 
	char pass[90];
	char test_inputs1[MAX_INPUT];


	//gets()���� |START|-----------------------

	do{
		printf("tips: please input you name : \n", name);
	} while (gets(name) == NULL);
	 
	do{
		printf("tips: please input you password : \n", name);
	} while (gets(pass) == NULL);

	//gets()�����ȡ�����Ļ��ͷ��ش����ָ������������ȡ����ͷ���NULLָ�롣 


	/*while (gets(name) == NULL)
		printf("tips: please input you name : \n", name);
	while (gets(pass) == NULL)
		printf("tips: please input you password : \n", pass);*/

	printf("your name is %s : \n", name);
	printf("your password  is %s : \n", pass);

	//fgets() ���� |START|-----------------------
	fgets(test_inputs1, MAX_INPUT, stdin);
	printf("your test_inputs1  is %s : \n", test_inputs1);




}


void test_string_output(){
	//C �����������ַ�������ĺ��� puts(), fputs(),��printf(); 


}



