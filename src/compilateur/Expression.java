package compilateur;

import java.util.Stack;
import compilateur.CompilationError.Error;

/**
 * Compute the expressions and the affectations.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class Expression {
	private Stack<Type> stackType;
	private Stack<Operator> stackOp;
	private boolean invert;
	private Ident affectTo;
	
	/**
	 * Constructor
	 */
	public Expression() {
		this.stackType = new Stack<Type>();
		this.stackOp = new Stack<Operator>();
		this.invert = false;
	}
	
	/**
	 * Pop the type from the stack and return it.
	 * @return The type on top of the type stack.
	 */
	public Type getType() {
		return stackType.pop();
	}
	
	/**
	 * Tell Expression that the next value need to be inverted.
	 */
	public void invert() {
		this.invert = true;
	}
	
	/**
	 * Push an operator to the stack of operators.
	 * @param op The operator.
	 */
	public void pushOperator(Operator op) {
		this.stackOp.push(op);
	}
	
	/**
	 * Invert the last value.
	 * This value need to be an integer.
	 */
	private void invertInteger() {
		Operator op = this.stackOp.pop();
		if(op==Operator.NEG) {
			Yaka.yvm.ineg();
		} else {
			Yaka.errors.addError(Error.INVALID_OPERATION, "Encountered a "+op+" operator while trying to invert but a - operator was expected.");
		}
	}
	
	/**
	 * Invert the last value.
	 * This value need to be a boolean.
	 */
	private void invertBoolean() {
		Operator op = this.stackOp.pop();
		if(op==Operator.NOT) {
			Yaka.yvm.inot();
		} else {
			Yaka.errors.addError(Error.INVALID_OPERATION, "Encountered a "+op+" operator while trying to invert but a NOT was expected.");
		}
	}
	
	/**
	 * Push an integer to the stack of values.
	 * @param ent The integer.
	 */
	public void pushInteger(int ent) {
		this.stackType.push(Type.INT);
		Yaka.yvm.iconst(ent);
		if(this.invert) {
			this.invertInteger();
			this.invert = false;
		}
	}
	
	/**
	 * Push a boolean to the stack of values.
	 * @param bool The boolean.
	 */
	public void pushBoolean(int bool) {
		this.stackType.push(Type.BOOL);
		Yaka.yvm.iconst(bool);
		if(this.invert) {
			this.invertBoolean();
			this.invert = false;
		}
	}
	
	/**
	 * Push an ident to the stack of values.
	 * @param str The key for the ident.
	 */
	public void pushIdent(String str) {
		Ident ident = Yaka.tabIdent.getIdent(str);
		if(ident!=null) {
			this.stackType.push(ident.getType());
			if(ident.isVar() || ident.isParam()) {
				Yaka.yvm.iload(((IdVar)ident).getOffset());
			} else if(ident.isConst()) {
				Yaka.yvm.iconst(((IdConst)ident).getValue());
			} else {
				Yaka.errors.addError(Error.CODE_ERROR, "An IdFunction has been found in the table of local variables!");
			}
			
			// Invert:
			if(this.invert) {
				if(ident.getType()==Type.BOOL) {
					this.invertBoolean();
				} else if(ident.getType()==Type.INT) {
					this.invertInteger();
				} else {
					Yaka.errors.addError(Error.CODE_ERROR, "Found an ident of type ERROR!");
				}
				this.invert = false;
			}
		} else {
			this.stackType.push(Type.ERROR);
			Yaka.errors.addError(Error.IDENT_UNKNOWN, "Ident '"+str+"' not found.");
		}
	}
	
	/**
	 * Push the returned type of a function to the stack of types.
	 * @param returnedType The returned type of the function.
	 */
	public void pushFunction(Type returnedType) {
		this.stackType.push(returnedType);
	}
	
	/**
	 * Add the operation part of the expression according to the two last values.
	 * Display an error if the type of the values doesn't match with the operator.
	 */
	public void compute() {
		Type b = this.stackType.pop();
		Type a = this.stackType.pop();
		Operator op = this.stackOp.pop();
		if((a==Type.BOOL || a==Type.ERROR) && (b==Type.BOOL || b==Type.ERROR)) {
			switch(op) {
				case OR:
					Yaka.yvm.ior();
					this.stackType.push(Type.BOOL);
					break;
				case AND:
					Yaka.yvm.iand();
					this.stackType.push(Type.BOOL);
					break;
				case DIFF:
					Yaka.yvm.idiff();
					this.stackType.push(Type.BOOL);
					break;
				case EQUAL:
					Yaka.yvm.iegal();
					this.stackType.push(Type.BOOL);
					break;
				default:
					Yaka.errors.addError(Error.INVALID_OPERATION, "Invalid boolean operation: "+op+".");
					this.stackType.push(Type.ERROR);
			}
		} else if((a==Type.INT || a==Type.ERROR) && (b==Type.INT || b==Type.ERROR)) {
			switch(op) {
				case ADD:
					Yaka.yvm.iadd();
					this.stackType.push(Type.INT);
					break;
				case SUB:
					Yaka.yvm.isub();
					this.stackType.push(Type.INT);
					break;
				case MUL:
					Yaka.yvm.imul();
					this.stackType.push(Type.INT);
					break;
				case DIV:
					Yaka.yvm.idiv();
					this.stackType.push(Type.INT);
					break;
				case LT:
					Yaka.yvm.iinf();
					this.stackType.push(Type.BOOL);
					break;
				case LTE:
					Yaka.yvm.iinfegal();
					this.stackType.push(Type.BOOL);
					break;
				case GT:
					Yaka.yvm.isup();
					this.stackType.push(Type.BOOL);
					break;
				case GTE:
					Yaka.yvm.isupegal();
					this.stackType.push(Type.BOOL);
					break;
				case DIFF:
					Yaka.yvm.idiff();
					this.stackType.push(Type.BOOL);
					break;
				case EQUAL:
					Yaka.yvm.iegal();
					this.stackType.push(Type.BOOL);
					break;
				default:
					Yaka.errors.addError(Error.INVALID_OPERATION, "Invalid integer operation: "+op+".");
					this.stackType.push(Type.ERROR);
			}
		} else {
			Yaka.errors.addError(Error.OPERANDS_DONT_MATCH, "The two operands doesn't match: "+a+" "+op+" "+b+".");
			this.stackType.push(Type.ERROR);
		}
	}
	
	/**
	 * Record the ident for the affectation.
	 * @param name The name of the ident.
	 */
	public void setAffectation(String name) {
		if(Yaka.tabIdent.containsIdent(name)) {
			this.affectTo = Yaka.tabIdent.getIdent(name);
		} else {
			Yaka.errors.addError(Error.IDENT_UNKNOWN, "Ident '"+name+"' doesn't exist in the table of idents.");
		}
	}
	
	/**
	 * Add the affectation part of an expression.
	 */
	public void affectation() {
		Type varType = this.affectTo.getType();
		Type valType = this.stackType.pop();
		if(this.affectTo.isVar()) {
			if(varType==valType) {
				Yaka.yvm.istore(((IdVar)this.affectTo).getOffset());
			} else {
				String message = "Types don't match at the affectation.\n";
				message += "Variable is of type "+varType+" but value is of type "+valType+".";
				if(valType==Type.ERROR) {
					Yaka.errors.addError(Error.NO_ERROR, message);
				} else {
					Yaka.errors.addError(Error.TYPE_AFFECTATION_DONT_MATCH, message);
				}
			}
		} else {
			Yaka.errors.addError(Error.AFFECTATION_CONSTANT, "Trying to affect constant.");
		}
	}
}