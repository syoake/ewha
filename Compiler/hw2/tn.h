/*
tn.h - token types
programmer - 류서영, 이하경
date - 2022/04/25
*/

enum tnumber {
	TEOF, TCONST, TELSE, TIF, TINT, TRETURN, TVOID, TWHILE, TADD, TSUB, TMUL, TDIV, TMOD, TASSIGN, TNOT, TEQUAL, TNOTEQU, TLESS, TGREAT, TLESSEQL, TGREATEQL, TAND, TOR, TINC, TDEC, TADDASSIGN, TSUBASSIGN, TMULASSIGN, TDIVASSIGN, TMODASSIGN, TOPENSBRAC, TCLOSESBRAC, TCOMMA, TOPENMBRAC, TCLOSEMBRAC, TOPENLBRAC, TCLOSELBRAC, TSEMICOL, TNUMBER, TOCT, THEX, TREALNUM, TIDENT, TNEWLINE
};