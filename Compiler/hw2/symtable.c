/*
symtable.c - symbol table
programmer - ������, ���ϰ�
date - 2022/04/25
*/

#include <stdio.h>
#include <stdlib.h>
#include "tn.h"
#include "glob.h"

int nextid = 0;          // the current identifier 
int nextfree = 0;        // the next available index of ST
int hashcode;            // hash code of identifier
int sameid;              // first index of identifier
int found;               // for the previous occurrence of an identifier
int idx1, idx2;          // index of identifier in hash table

ERRORtypes err;          // error types

/*
ComputeHS() - Hash Table�� hashcode�� ����ϴ� �Լ�
*/
void ComputeHS(int nid, int nfree) {
	int sum = 0;
	char a;
	for (int i = nid; i < nfree; i++) {
		a = ST[i];
		sum += a;
	}
	hashcode = (sum % HTsize) + 1;
}

/*
LookupHS() - Hash Table�� �Ȱ��� identifier��
             ����Ǿ� �ִ��� ã�� �Լ�
*/
void LookupHS(int nid, int hscode) {
	//hashcode�� �ش��ϴ� HT�� ����Ʈ�� Ž���Ͽ� �ش� identifier�� �̹� HT�� �ִ��� ������ ���θ� �Ǵ�
	//�Ǵ� ����� found ������ ����
	HTentry* node = HT[hscode]; // hascode�� �ش��ϴ� HT�� ����Ʈ�� ù��° ���
	int len1 = 0;
	int len2 = 0;
	char ch1, ch2;
	int same;

	found = 0;

	while (node != NULL) {
		idx1 = node->index; // ���� HT[hscode]�� �����ϴ� identifier�� ù ������ ST �ε��� 
		idx2 = nid; // HT�� �߰��ϰ��� �ϴ� identifier�� ù ������ ST �ε���
		ch1 = ST[idx1]; // ���� HT[hscode]�� �����ϴ� identifier�� ù ����
		ch2 = ST[idx2]; // HT�� �߰��ϰ��� �ϴ� identifier�� ù ����
		same = 0; // same�� 0�̸� ch1�� ch2�� ���� ���� �ǹ�, 1�̸� ���� �ǹ�

		while (ch1 != '\0' && ch2 != '\0') {
			if (ch1 == ch2) { // �� ���ڰ� ���� ���
				same = 1;
				ch1 = ST[++idx1]; // ���� ����
				ch2 = ST[++idx2]; // ���� ����
				if (ch1 != '\0') { len1++; }
				if (ch2 != '\0') { len2++; }
			}
			else { // �� ���ڰ� ���� ���� ���
				same = 0;
				break;
			}
		}

		if (same == 1 && (len1==len2)) { // identifier�� �̹� HT�� �ִ� ���
			idx1 = node->index; // �̹� �����ϴ� identifier�� ST index�� ����
			found = 1;
			break;
		}
		node = node->next; // ���� ���
	}
}

/*
ADDHT() - Hash Table�� identifier�� �����
          String Table�� index ���� �����ϴ� �Լ�
*/
void ADDHT(int hscode) {
	HTpointer new_node = (HTpointer)malloc(sizeof(HTpointer));
	new_node->index = nextid;
	new_node->next = HT[hscode];
	HT[hscode] = new_node;
}

/*
SymbolTable() - token�� error�� �ִ��� �˻��ϰ� error�� ������
			    ComputeHS, LookupHS, ADDHT �Լ��� �����ϴ� �Լ�
*/
void SymbolTable() {
	int i;
	err = noerror;

	if (nextfree == STsize) {
		err = stover;
		reporterror(err);
	}

	if (yyleng > 12) {
		err = illid;
		reporterror(err);
		return 1;
	}

	if (isDigit(yytext[0])) {
		err = illid;
		reporterror(err);
		return 1;
	}
	nextid = nextfree;

	for (i = 0; yytext[i] != '\0'; i++) {
		if (nextfree == STsize) {
			err = stover;
			reporterror(err);
			return 1;
		}

		if (!isLetter(yytext[i]) && !isDigit(yytext[i]) && !isUnderbar(yytext[i])) {
			err = illchar;
			reporterror(err);
			return 1;
		}

		if (err == noerror) {

			ST[nextfree] = yytext[i];
			nextfree++;
		}
	}

	ST[nextfree++] = '\0';
	ComputeHS(nextid, nextfree);
	LookupHS(nextid, hashcode);

	if (!found)
				ADDHT(hashcode);
	else {
		for (int i = nextfree - 1; i >= nextid; i--) {
			ST[i] = '\0';
		}
		nextfree = nextid;
	}
}
	

