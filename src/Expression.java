import java.util.Stack;

/**
 * Compute the expressions.
 */
public class Expression {
	private Stack<Type> stackType;
	private Stack<Operator> stackOp;
	private String expr;
	private boolean invert;
	
	/**
	 * Constructor
	 */
	public Expression() {
		this.stackType = new Stack<Type>();
		this.stackOp = new Stack<Operator>();
		this.invert = false;
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
	 * Push an integer to the stack of values.
	 * @param ent The integer.
	 */
	public void pushInteger(int ent) {
		this.stackType.push(Type.INT);
		if(this.invert) {
			ent = -ent;
			this.invert = false;
		}
		this.expr += Yaka.yvm.iconstInt(ent);
	}
	
	/**
	 * Push a boolean to the stack of values.
	 * @param bool The boolean.
	 */
	public void pushBoolean(boolean bool) {
		this.stackType.push(Type.BOOL);
		if(this.invert) {
			bool = !bool;
			this.invert = false;
		}
		this.expr += Yaka.yvm.iconstBool(bool);
	}
	
	/**
	 * Push an ident to the stack of values.
	 * @param str The key for the ident.
	 */
	public void pushIdent(String str) {
		Ident ident = Yaka.tabIdent.searchIdent(str);
		this.stackType.push(ident.getType());
		if(ident.isVar()) {
			Yaka.yvm.iload(ident.getValue());
		} else {
			Yaka.yvm.iconstInt(ident.getValue());
		}
	}
	
	/**
	 * Add the operation part of the expression according to the two last values.
	 * Display an error if the type of the values doesn't match with the operator.
	 */
	public void compute() {
		boolean isPredicate = this.stackType.pop()==Type.BOOL && this.stackType.pop()==Type.BOOL;
		Operator op = this.stackOp.pop();
		if(isPredicate) {
			switch(op) {
				case ADD:
					this.expr += Yaka.yvm.iadd();
					break;
				case SUB:
					this.expr += Yaka.yvm.isub();
					break;
				case MUL:
					this.expr += Yaka.yvm.imul();
					break;
				case DIV:
					this.expr += Yaka.yvm.idiv();
					break;
				default:
					System.err.println("Unexpected operator in a predicat: "+op);
			}
		} else {
			switch(op) {
				case ADD:
					this.expr += Yaka.yvm.iadd();
					break;
				case OR:
					this.expr += Yaka.yvm.ior();
					break;
				case LT:
					this.expr += Yaka.yvm.iinf();
					break;
				case LTE:
					this.expr += Yaka.yvm.iinfegal();
					break;
				case GT:
					this.expr += Yaka.yvm.isup();
					break;
				case GTE:
					this.expr += Yaka.yvm.isupegal();
					break;
				case DIFF:
					this.expr += Yaka.yvm.idiff();
					break;
				case EQUAL:
					this.expr += Yaka.yvm.iegal();
					break;
				default:
					System.err.println("Unexpected operator in an expression: "+op);
			}
		}
	}
	
	/**
	 * Display the result.
	 * TODO Useless?
	 */
	public void displayResult() {
		System.out.println("Yaka:\n"+this.expr);
	}
}
