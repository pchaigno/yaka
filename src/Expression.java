
import java.util.Stack;

/**
 * Compute the expressions.
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
	 * @return True if the type of the top of the stack is boolean.
	 */
	public boolean isBool() {
		return stackType.pop()==Type.BOOL;
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
		Yaka.program += Yaka.yvm.iconstInt(ent);
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
		Yaka.program += Yaka.yvm.iconstBool(bool);
	}
	
	/**
	 * Push an ident to the stack of values.
	 * @param str The key for the ident.
	 */
	public void pushIdent(String str) {
		Ident ident = Yaka.tabIdent.getIdent(str);
		if(ident==null) {
			System.err.println("Ident not found: "+str);
			return;
		}
		this.stackType.push(ident.getType());
		if(ident.isVar()) {
			Yaka.program += Yaka.yvm.iload(ident.getValue());
		} else {
			Yaka.program += Yaka.yvm.iconstInt(ident.getValue());
		}
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
					Yaka.program += Yaka.yvm.ior();
					this.stackType.push(Type.BOOL);
					break;
				case AND:
					Yaka.program += Yaka.yvm.iand();
					this.stackType.push(Type.BOOL);
					break;
				case DIFF:
					Yaka.program += Yaka.yvm.idiff();
					this.stackType.push(Type.BOOL);
					break;
				case EQUAL:
					Yaka.program += Yaka.yvm.iegal();
					this.stackType.push(Type.BOOL);
					break;
				default:
					this.stackType.push(Type.ERROR);
			}
		} else if((a==Type.INT || a==Type.ERROR) && (b==Type.INT || b==Type.ERROR)) {
			switch(op) {
				case ADD:
					Yaka.program += Yaka.yvm.iadd();
					this.stackType.push(Type.INT);
					break;
				case SUB:
					Yaka.program += Yaka.yvm.isub();
					this.stackType.push(Type.INT);
					break;
				case MUL:
					Yaka.program += Yaka.yvm.imul();
					this.stackType.push(Type.INT);
					break;
				case DIV:
					Yaka.program += Yaka.yvm.idiv();
					this.stackType.push(Type.INT);
					break;
				case LT:
					Yaka.program += Yaka.yvm.iinf();
					this.stackType.push(Type.BOOL);
					break;
				case LTE:
					Yaka.program += Yaka.yvm.iinfegal();
					this.stackType.push(Type.BOOL);
					break;
				case GT:
					Yaka.program += Yaka.yvm.isup();
					this.stackType.push(Type.BOOL);
					break;
				case GTE:
					Yaka.program += Yaka.yvm.isupegal();
					this.stackType.push(Type.BOOL);
					break;
				case DIFF:
					Yaka.program += Yaka.yvm.idiff();
					this.stackType.push(Type.BOOL);
					break;
				case EQUAL:
					Yaka.program += Yaka.yvm.iegal();
					this.stackType.push(Type.BOOL);
					break;
				default:
					this.stackType.push(Type.ERROR);
			}
		} else {
			System.err.println("Expression: The two operands doesn't match.");
		}
	}
	
	/**
	 * @return The final result: the generated code.
	 */
	public String getResult() {
		return Yaka.program;
	}
	
	/**
	 * Record the ident for the affectation.
	 * @param name The name of the ident.
	 */
	public void setAffectation(String name) {
		if(Yaka.tabIdent.containsIdent(name)) {
			this.affectTo = Yaka.tabIdent.getIdent(name);			
		} else {
			System.err.println("Expression: Ident does not exist.");
		}
	}
	
	/**
	 * Add the affectation part of an expression.
	 */
	public void affectation() {
		if(this.affectTo.getType()==this.stackType.pop()) {
			Yaka.program += Yaka.yvm.istore(this.affectTo.getValue());
		} else {
			System.err.println("Expression: Different types.");
		}
	}
}