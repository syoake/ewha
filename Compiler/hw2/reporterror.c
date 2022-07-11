/*
reporterror.c - error reporting
programmer - ������, ���ϰ�
date - 2022/04/25
*/

#include <stdio.h>
#include <stdlib.h>
#include "tn.h"
#include "glob.h"

/*
reporterror() - token�� error type ���� ����ϴ� �Լ�
*/
void reporterror(ERRORtypes err) {
	printf(" %d\t\t\t", cLine);
	printf("**Error**\t\t\t\t\t");
	printf("%s ", yytext);

	if (err == stover)
		printf("stack overflow\n");
	else if (err == illid)
		printf("illegal identifier\n");
	else if (err == illchar)
		printf("illegal character\n");
	else if (err == illnum)
		printf("illegal number format\n");

	cErrors++;
}