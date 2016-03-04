
#include "stdafx.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <locale.h>
#include <inttypes.h> 
#include <float.h>
#include <cfloat>

#include "Date.h"

#pragma execution_character_set("utf-8") //Ĭ��ʹ��UTF8

Date * dates[10] = {};

void test(void){
	printf("\nmy is function test() \n");

}

void printfHelp();
float get_discard_number(float mustrate);
float get_rate(float mustrate);

#include "test_format.h"


int main(int argc, char* argv[])

{
	int  fristInt;
	int  secondeInt;
	bool  include_a = false;
	bool include_b = false;
	const char * _count_a = NULL;
	const char * _count_b = NULL;
	const char * _count_c = NULL;
	const char * _count_d = NULL;
	const char * _count_e = NULL;
	float calculate_a, calculate_b, calculate_c;
	float calculate_d, calculate_e;

	//_tsetlocale(LC_ALL, _T("chs"));

	Date d = { 2015, 8, 30 };
	d.toString();
	d.p = &test;
	d.p();

	/* 

	argc = 11;
	char* argv2[] = { "Demo1_Suffix_C", "-A", "11910", "-B", "430.74", "-C", "1000", "-D", "0.1", "-E", "0.1" };
	argv = argv2; */


	if (argc > 1){
		const char * tmp = *argv;
		int index = 0;
		while ((index < argc) && (tmp = (argv[index]))){
			if (!strcmp(tmp, "-A")){
				include_a = true;
				index++;
				_count_a = argv[index];
				continue;
			}
			else if (!_stricmp(tmp, "-B")){
				include_b = true;
				index++;
				_count_b = argv[index];
				continue;
			}
			else if (!_stricmp(tmp, "-C")){
				index++;
				_count_c = argv[index];
				continue;
			}
			else if (!_stricmp(tmp, "-D")){
				index++;
				_count_d = argv[index];
				continue;
			}
			else if (!_stricmp(tmp, "-E")){
				index++;
				_count_e = argv[index];
				continue;
			}
			else if (!_stricmp(tmp, "-H") || !_stricmp(tmp, "-h") || !_stricmp(tmp, "--help")){
				printfHelp();
				continue;
			}
			index++;
		}
		if (_count_a && _count_b && _count_c && _count_d && _count_e){
			printf("\nThe Input is: -A  %s -B %s -C %s -D %s -E %s  ", _count_a, _count_b, _count_c, _count_d, _count_e);

			calculate_a = atof(_count_a);
			calculate_b = atof(_count_b);
			calculate_c = atof(_count_c);
			calculate_d = atof(_count_d);
			calculate_e = atof(_count_e);
			float tmp_must_rate = calculate_a - calculate_b - calculate_c * calculate_e - 3500;
			float tmp_rate = tmp_must_rate * get_rate(tmp_must_rate) - get_discard_number(tmp_must_rate);
			float tmp_must_send = calculate_a - calculate_b - calculate_c * calculate_e - tmp_rate;

			//_tsetlocale(LC_ALL, _T("chs"));
			//_tprintf(_T("\n��ʵ���ܷ��Ĺ����� %.2f \n", tmp_must_send));


			printf("\n˰ǰ������ %.2f ", calculate_a);
			printf("\nӦ������ %.2f ", calculate_a);
			printf("\n���˽������պϼ� %.2f ", calculate_b);
			printf("\n���˽��ɹ����� %.2f ", calculate_c * calculate_e);
			printf("\n���˽�������˰ %.2f ", tmp_rate);
			printf("\nʵ������ %.2f ", tmp_must_send);
			printf("\n------" );
			printf("\n����˰���ɱ��� %.2f",get_rate(tmp_must_rate));
			printf("\n����˰����۳��� %.2f \n", get_discard_number(tmp_must_rate));
		
			/*printf("\nyou must surrender income tax  is %.2f \n", tmp_rate);
			printf("\nyou must get is %.2f \n", tmp_must_send);
			printf("\n��ʵ���ܷ��Ĺ����� %.2f \n", tmp_must_send);*/

		}
		else{
			printfHelp();
		}
	}
	else{
		printfHelp();
	}


	/*for (int i = 0; i < 10; i++)
	{
	dates[i] = &d;
	dates[i]->p = &test;
	dates[i]->p();
	}


	scanf_s(" %d %d", &fristInt, &secondeInt);
	printf("this is my first c++ programe %d !!! \n", 1);
	printf("fristInt is  %d and secondeInt is %d \n", fristInt, secondeInt);*/


	/*atof�����ַ���ת���ɸ���������
	��غ���  atoi��atol��strtod��strtol��strtoul
	��ͷ�ļ�  #include <stdlib.h>*/

	test_format();


	system("pause");
	return 0;
}


void printfHelp(){
	printf("\n-----------------------------------------------");
	printf("\n@CopyRrigth Kevin.young ");
	printf("\n@version -v 2.0.0 ..... 2015/10/21 12:00:00");
	printf("\n [-A] SHUIQIAN... [-B] WUXIAN... \n [-C] GONGJIJIN JISHU ");
	printf("[-D] GONGJIJIN RATE OF Company  [-E] GONGJIJIN RATE OF Personal  [-H|-h|--help] just for get help...");
	printf("\nThe Input is Example like this : -A 3000 -B 3000 -C 400 -D 0.8  -E 0.8   ");
	printf("\n\n");
	printf("\n-A  please input your Shui Qian Gong zi ,example : 3000");
	printf("\n-B  please input your Wu Xian  ,example ,example : 3000");
	printf("\n-C  please input your Gong ji jin jishu  ,example : 6000");
	printf("\n-D  please input your Gong ji jin --Company rate ,example : 0.08");
	printf("\n-E  please input your Gong ji jin --Personal rate ,example :0.08");
	printf("\n");
}

float get_rate(float mustrate){
	if (mustrate <= 1500){
		return 0.03f;
	}
	else if (mustrate > 1500 && mustrate <= 4500){
		return 0.1f;
	}
	else if (mustrate > 4500 && mustrate <= 9000){
		return 0.2f;
	}
	else if (mustrate > 9000 && mustrate <= 35000){
		return 0.25f;
	}
	else if (mustrate > 35000 && mustrate <= 55000){
		return 0.3f;
	}
	else if (mustrate > 55000 && mustrate <= 80000){
		return 0.35f;
	}
	return 0.45f;
}

float get_discard_number(float mustrate){
	if (mustrate <= 1500){
		return 0;
	}
	else if (mustrate > 1500 && mustrate <= 4500){
		return 105;
	}
	else if (mustrate > 4500 && mustrate <= 9000){
		return 555;
	}
	else if (mustrate > 9000 && mustrate <= 35000){
		return 1005;
	}
	else if (mustrate > 35000 && mustrate <= 55000){
		return 2755;
	}
	else if (mustrate > 55000 && mustrate <= 80000){
		return 5505;
	}
	return 13505;
}
