import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

public class TestYaka extends TestCase {
	
	/**
	 * First test just to intialize the Yaka compiler.
	 */
	public void testFirst() {
		new Yaka(System.in);
	}
	
	/**
	 * Test an empty program.
	 */
	public void testEmpty() {
		String program = "PROGRAMME empty FPROGRAMME";
		this.launchAnalyse(program);
		//System.out.println(Yaka.expression.getResult());
	}
	
	/**
	 * Test the declaration of the constants.
	 */
	public void testDeclarationConstants() {
		String program = "PROGRAMME const " +
				"CONST aa=10, ba=VRAI, cc=aa;" +
				"FPROGRAMME";
		this.launchAnalyse(program);
		//System.out.println(Yaka.expression.getResult());
	}
	
	/**
	 * Test the declaration of the variables.
	 */
	public void testDeclarationVariables() {
		String program = "PROGRAMME var " +
				"VAR ENTIER c1,c2; VAR BOOLEEN b1,b2; " +
				"FPROGRAMME";
		this.launchAnalyse(program);
		//System.out.println(Yaka.expression.getResult());
	}
	
	/**
	 * Test all types of declarations.
	 */
	public void testDeclaration() {
		String program = "PROGRAMME declar " +
				"CONST aa=10, ba=VRAI, cc=aa; " +
				"VAR ENTIER c1,c2; VAR BOOLEEN b1,b2; " +
				"FPROGRAMME";
		this.launchAnalyse(program);
		//System.out.println(Yaka.expression.getResult());
	}
	
	/**
	 * Test the program for expressions part.
	 */
	public void testExpression() {
		String program = "PROGRAMME declar " +
				"CONST aa=10, ba=VRAI, cc=aa; " +
				"VAR ENTIER c1,c2; VAR BOOLEEN b1,b2; " +
				"(aa+cc/2)/5; c1+3*c1-aa; ba OU VRAI; c1<=(c2+4);" +
				"FPROGRAMME";
		this.launchAnalyse(program);
		//System.out.println(Yaka.expression.getResult());
	}
	
	/**
	 * Compile the program.
	 * @param program The program.
	 */
	private void launchAnalyse(String program) {
		InputStream input = new ByteArrayInputStream(program.getBytes());
		Yaka.ReInit(input);
	    try {
			Yaka.analyse();
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}
}