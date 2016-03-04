

/*
Copyright (c) Kevin.young Personally. All rights reserved.
Pupouse:
1. just for Test Format of all Basic Types .
2. limits.h  and float.h 获取更多的类型信息。
*/
#include <stdio.h> 
#include "stdafx.h"
#include <inttypes.h>
#include <limits.h> 
#include <float.h> 


void test_format(){
	printf("\nType char has a size of %u bytes ", sizeof(char));
	printf("\nType int has a size of %u bytes ", sizeof(int));
	printf("\nType long has a size of %u bytes ", sizeof(long));
	printf("\nType long long has a size of %u bytes ", sizeof(long long));
	printf("\nType float has a size of %u bytes ", sizeof(float));
	printf("\nType double has a size of %u bytes ", sizeof(double));
	printf("\nType long double  has a size of %u bytes ", sizeof(long double));


	printf("\nType int_least8_t has a size of %u bytes ", sizeof(int_least8_t));
	printf("\nType int_least16_t has a size of %u bytes ", sizeof(int_least16_t));
	printf("\nType int_least32_t has a size of %u bytes ", sizeof(int_least32_t));
	printf("\nType int_least64_t has a size of %u bytes ", sizeof(int_least64_t));
	printf("\nType uint_least8_t has a size of %u bytes ", sizeof(uint_least8_t));
	printf("\nType uint_least16_t has a size of %u bytes ", sizeof(uint_least16_t));
	printf("\nType uint_least32_t has a size of %u bytes ", sizeof(uint_least32_t));
	printf("\nType uint_least64_t has a size of %u bytes ", sizeof(uint_least64_t));
}