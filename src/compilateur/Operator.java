package compilateur;

/**
 * Represents an operator.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public enum Operator {
	ADD, // +
	SUB, // -
	MUL, // *
	DIV, // /
	NEG, // -
	NOT, // NON
	OR, // OU
	AND, // ET
	EQUAL, // ==
	DIFF, // <>
	LT, // <
	GT, // >
	LTE, // <=
	GTE; // >=
}