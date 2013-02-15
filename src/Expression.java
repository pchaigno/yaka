import java.util.Stack;

/**
 * TODO Add comments.
 * @author pchaigno
 */
public class Expression {
	// TODO Use a more specific type than Object. One of our own?
	private Stack<Object> stackVal;
	private Stack<Character> stackOp;
	
	/**
	 * Constructor
	 */
	public Expression() {
		this.stackVal = new Stack<Object>();
		this.stackOp = new Stack<Character>();
	}
	
	/**
	 * Push an operator to the stack of operators.
	 * @param op The operator.
	 */
	public void pushOperator(char op) {
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
	public void generer() {
		Object b = this.stackVal.pop();
		Object a = this.stackVal.pop();
		char op = this.stackOp.pop();
		String expr = "";
		// TODO Factorize the code:
		if(Integer.class.equals(a.getClass())) {
			expr = "iconst "+a+"\n";
		} else {
			String str = (String)a;
			if(isVariable(str)) {
				expr = "iload "+str+"\n";
			} else {
				expr = str;
			}
		}
		if(Integer.class.equals(b.getClass())) {
			expr += "iconst "+b+"\n";
		} else {
			String str = (String)b;
			if(isVariable(str)) {
				expr += "iload "+str+"\n";
			} else {
				expr += str;
			}
		}
		switch(op) {
			case '+':
				expr += "iadd\n";
				break;
			case '-':
				expr += "isub\n";
				break;
			case '*':
				expr += "imul\n";
				break;
			case '/':
				expr += "idiv\n";
				break;
		}
		this.stackVal.push(expr);
	}
	
	/**
	 * Invert the value at the top of the values' stack.
	 * Check if the type match with the last operator.
	 */
	public void invert() {
		Object a = this.stackVal.pop();
		char op = this.stackOp.pop();
		if(op=='-') {
			if(a.getClass()==int.class) {
				int val = -(int)a;
				this.stackVal.push(val);
			} else {
				System.err.println("Expecting an integer...");
			}
		} else if(op=='n') {
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
	 * Write the affectation in Yaka.
	 */
	public void allocate() {
		String expr = (String)this.stackVal.pop();
		String var = (String)this.stackVal.pop();
		this.stackVal.push(expr+"istore "+var+"\n");
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
