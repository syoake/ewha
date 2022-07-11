/*
main.c - �� token�� ���� ���
programmer - ������, ���ϰ�
date - 2022/06/06
*/

#include <stdio.h>
#include <stdlib.h>
#include "tn.h"
#include "glob.h"

extern int cLine;
extern int cErrors;

extern int yyparse();
extern yyerror(char* s);

void main() {
	cLine = 1;
	cErrors = 0;

	printf("\n\t*** MiniC parsing begins\n");
	printf("\n\t[Error Report : Error Information]\n");
	yyparse();
	printf("\n\tParsing ends. ***\n");


	if (cErrors == 0) {
		printf("\n\tno error detected\n\n");
		PrintHStable();	// identifier�� �� type�� print�ϴ� �Լ�
	}
	else
		printf("\n\t%d error(s) detected\n\n", cErrors);
}