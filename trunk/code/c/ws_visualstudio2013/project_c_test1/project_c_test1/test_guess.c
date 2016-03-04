

#include <stdio.h>
void guess(){
	int guess = 1;
	printf("Pick an integer for 1 to 100 ,I will try to guess it. \n Respond with a "
		"y if my guess is right and with an n if it is wrong.\n");
	printf("Uh...Is you number %d ? \n", guess);
	while (getchar() != 'y')
	{
		printf("Then...Is you number %d ? \n", ++guess);
		while (getchar()!='\n')
		{
			continue;
		}
	}
	printf("I konw i could do it .Haha \n", guess);

}