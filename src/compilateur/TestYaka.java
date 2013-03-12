package compilateur;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.TestCase;

/**
 * Unit tests.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class TestYaka extends TestCase {
	boolean showYVMCode = false;
	boolean showASMCode = false;
	
	/**
	 * First test just to intialize the Yaka compiler.
	 */
	public void testFirst() {
		new Yaka(System.in);
	}
	
	/**
	 * Test the declaration of the variables.
	 */
	public void testVariablesDeclaration() throws IOException {
		String program = getContentOfFile("tests/declaration_variables.yaka");
		
		String programYVM = getContentOfFile("tests/declaration_variables.yvm");
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Variables declaration in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = getContentOfFile("tests/declaration_variables.asm");
		compileToASM(program);
		if(this.showASMCode) {
			System.out.println("Variables declaration in ASM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programASM, Yaka.yvm.getProgram());
	}
	
	/**
	 * Test all types of declarations.
	 */
	public void testDeclaration() throws IOException {
		String program = getContentOfFile("tests/declaration.yaka");
		
		String programYVM = getContentOfFile("tests/declaration.yvm");
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Declaration in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = getContentOfFile("tests/declaration.asm");
		compileToASM(program);
		if(this.showASMCode) {
			System.out.println("Declaration in ASM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programASM, Yaka.yvm.getProgram());
	}
	
	/**
	 * Test the program for affectations part.
	 */
	public void testAffectations() throws IOException {
		String program = getContentOfFile("tests/affectations.yaka");
		
		String programYVM = getContentOfFile("tests/affectations.yvm");
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Affectations in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = getContentOfFile("tests/affectations.asm");
		compileToASM(program);
		if(this.showASMCode) {
			System.out.println("Affectations in ASM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programASM, Yaka.yvm.getProgram());
	}
	
	/**
	 * Test the inputs and outputs.
	 */
	public void testInputOutput() throws IOException {
		String program = getContentOfFile("tests/input_output.yaka");
		
		String programYVM = getContentOfFile("tests/input_output.yvm");
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Inputs and outputs in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = getContentOfFile("tests/input_output.asm");
		compileToASM(program);
		if(this.showASMCode) {
			System.out.println("Inputs and outputs in ASM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programASM, Yaka.yvm.getProgram());
	}
	
	/**
	 * Test a simple iteration.
	 */
	public void testSimpleIteration() throws IOException {
		String program = getContentOfFile("tests/simple_iteration.yaka");
		
		String programYVM = getContentOfFile("tests/simple_iteration.yvm");
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Simple iteration in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = getContentOfFile("tests/simple_iteration.asm");
		compileToASM(program);
		if(this.showASMCode) {
			System.out.println("Simple iteration in ASM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programASM, Yaka.yvm.getProgram());
	}
	
	/**
	 * Test nested interations.
	 */
	public void testNestedIterations() throws IOException {
		String program = getContentOfFile("tests/nested_iterations.yaka");
		
		String programYVM = getContentOfFile("tests/nested_iterations.yvm");
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Nested iterations in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = getContentOfFile("tests/nested_iterations.asm");
		compileToASM(program);
		if(this.showASMCode) {
			System.out.println("Nested iterations in ASM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programASM, Yaka.yvm.getProgram());
	}
	
	/**
	 * Test an expression.
	 * Written by the teachers.
	 */
	public void testTeachersExpr() throws IOException {
		for(int i=1 ; i<6 ; i++) {
			String program = getContentOfFile("tests/tests_prof/expr"+i+".yaka");
			
			String programYVM = getContentOfFile("tests/tests_prof/expr"+i+".yvm");
			compileToYVM(program);
			if(this.showYVMCode) {
				System.out.println("From teachers - expr"+i+" in YVM:");
				System.out.println(Yaka.yvm.getProgram());
			}
			assertEquals(programYVM, Yaka.yvm.getProgram());
			
			String programASM = getContentOfFile("tests/tests_prof/expr"+i+".asm");
			compileToASM(program);
			if(this.showASMCode) {
				System.out.println("From teachers - expr"+i+" in ASM:");
				System.out.println(Yaka.yvm.getProgram());
			}
			assertEquals(programASM, Yaka.yvm.getProgram());
		}
	}
	
	/**
	 * Test a simple condition bloc.
	 */
	public void testSimpleCondition() throws IOException {
		String program = getContentOfFile("tests/simple_condition.yaka");
		
		String programYVM = getContentOfFile("tests/simple_condition.yvm");
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Simple condition in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = getContentOfFile("tests/simple_condition.asm");
		compileToASM(program);
		if(this.showASMCode) {
			System.out.println("Simple condition in ASM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programASM, Yaka.yvm.getProgram());
	}
	
	/**
	 * Compile the program to YVM code.
	 * @param program The Yaka program.
	 */
	private static void compileToYVM(String program) {
		InputStream input = new ByteArrayInputStream(program.getBytes());
		Yaka.ReInit(input);
		Yaka.initVariables(true);
	    try {
			Yaka.analyse();
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Compile the program to ASM code.
	 * @param program The Yaka program.
	 */
	private static void compileToASM(String program) {
		InputStream input = new ByteArrayInputStream(program.getBytes());
		Yaka.ReInit(input);
		Yaka.initVariables(false);
	    try {
			Yaka.analyse();
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}
	
	private static String getContentOfFile(String nameFile) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nameFile)));
			String content = "";
			String line;
			while ((line = br.readLine()) != null) {
				content += line+"\n";
			}
			br.close();
			return content;
		} catch (IOException e) {
			fail(e.getMessage());
		}
		return null;
	}
}