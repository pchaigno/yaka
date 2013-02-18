import java.util.Stack;

/**
 * TODO Add comments.
 * @author pchaigno
 */
public class Expression {
	// TODO Use a more specific type than Object. One of our own?
	private Stack<Object> stackVal;
	private Stack<Operator> stackOp;
	
	/**
	 * Constructor
	 */
	public Expression() {
		this.stackVal = new Stack<Object>();
		this.stackOp = new Stack<Operator>();
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
		this.stackVal.push(ent);
	}
	
	/**
	 * Push a boolean to the stack of values.
	 * @param bool The boolean.
	 */
	public void pushBoolean(boolean bool) {
		this.stackVal.push(bool);
	}
	
	/**
	 * Push an ident to the stack of values.
	 * @param ident The ident.
	 */
	public void pushIdent(String ident) {
		this.stackVal.push(ident);
	}
	
	/**
	 * Compute the two last integer with the last operator.
	 */
	// TODO Change the name.
	public void compute() {
		Object b = this.stackVal.pop();
		Object a = this.stackVal.pop();
		Operator op = this.stackOp.pop();
		String expr = "";
		boolean isPredicate = false;
		// TODO Factorize the code.
		if(Integer.class.equals(a.getClass())) {
			expr += Yaka.yvm.iconst(String.valueOf((int)a));
		} else if(Boolean.class.equals(b.getClass())) {
			expr += Yaka.yvm.iconst(String.valueOf((boolean)a));
			isPredicate = true;
		} else {
			String str = (String)a;
			if(isVariable(str)) {
				expr += Yaka.yvm.iload(str);
			} else {
				expr += str;
			}
		}
		if(Integer.class.equals(b.getClass())) {
			expr += Yaka.yvm.iconst(String.valueOf((int)b));
		} else if(Boolean.class.equals(b.getClass())) {
			expr += Yaka.yvm.iconst(String.valueOf((boolean)b));
			isPredicate = true;
		} else {
			String str = (String)b;
			if(isVariable(str)) {
				expr += Yaka.yvm.iload(str);
			} else {
				expr += str;
			}
		}
		if(isPredicate) {
			switch(op) {
				case ADD:
					expr += Yaka.yvm.iadd();
					break;
				case SUB:
					expr += Yaka.yvm.isub();
					break;
				case MUL:
					expr += Yaka.yvm.imul();
					break;
				case DIV:
					expr += Yaka.yvm.idiv();
					break;
				default:
					System.err.println("Unexpected operator in a predicat: "+op);
			}
		} else {
			switch(op) {
				case ADD:
					expr += Yaka.yvm.iadd();
					break;
				case OR:
					expr += Yaka.yvm.ior();
					break;
				default:
					System.err.println("Unexpected operator in an expression: "+op);
			}
		}
		this.stackVal.push(expr);
	}
	
	/**
	 * Invert the value at the top of the values' stack.
	 * Check if the type match with the last operator.
	 */
	public void invert() {
		Object a = this.stackVal.pop();
		Operator op = this.stackOp.pop();
		if(op==Operator.NEG) {
			if(a.getClass()==int.class) {
				int val = -(int)a;
				this.stackVal.push(val);
			} else {
				System.err.println("Expecting an integer...");
			}
		} else if(op==Operator.NOT) {
			if(a.getClass()==boolean.class) {
				boolean val = (boolean)a;
				val = (val)? false : true;
				this.stackVal.push(val);
			} else {
				System.err.println("Expecting a boolean...");
			}
		}
	}
	
	/**
	 * Display the result.
	 * TODO Useless?
	 */
	public void displayResult() {
		System.out.println("Yaka:\n"+this.stackVal.pop());
	}
	
	/**
	 * Check if a string can be a variable.
	 * @param string The string.
	 * @return True if it can be a variable.
	 */
	private static boolean isVariable(String string) {
		char ch;
		for(int i=0 ; i<string.length() ; i++) {
			ch = string.charAt(i);
			if(Character.isWhitespace(ch)) {
				return false;
			}
		}
		return true;
	}
}
