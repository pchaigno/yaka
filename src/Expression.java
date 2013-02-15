import java.util.Stack;

public class Expression {
	private Stack<Object> pileEnt;
	private Stack<Character> pileOp;
	
	/**
	 * Constructor
	 */
	public Expression() {
		this.pileEnt = new Stack<Object>();
		this.pileOp = new Stack<Character>();
	}
	
	/**
	 * Add an operator to the stack.
	 * @param op The operator.
	 */
	public void operator(char op) {
		this.pileOp.push(op);
	}
	
	/**
	 * Add an integer to the stack.
	 * @param ent The integer.
	 */
	public void integer(int ent) {
		this.pileEnt.push(ent);
	}
	
	/**
	 * Add an ident to the stack.
	 * @param ident The ident.
	 */
	public void ident(String ident) {
		this.pileEnt.push(ident);
	}
	
	/**
	 * Compute the two last integer with the last operator.
	 */
	public void generer() {
		Object b = this.pileEnt.pop();
		Object a = this.pileEnt.pop();
		char op = this.pileOp.pop();
		String expr = "";
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
		this.pileEnt.push(expr);
	}
	
	/**
	 * Write the affectation in Yaka.
	 */
	public void affecter() {
		String expr = (String)this.pileEnt.pop();
		String var = (String)this.pileEnt.pop();
		this.pileEnt.push(expr+"istore "+var+"\n");
	}
	
	/**
	 * Display the result.
	 */
	public void displayResult() {
		System.out.println("Yaka:\n"+this.pileEnt.pop());
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
