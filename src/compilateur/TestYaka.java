package compilateur;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

/**
 * Unit tests.
 */
public class TestYaka extends TestCase {
	boolean showYVMCode = false;
	boolean showASMCode = true;
	
	/**
	 * First test just to intialize the Yaka compiler.
	 */
	public void testFirst() {
		new Yaka(System.in);
	}
	
	/**
	 * Test the declaration of the variables.
	 */
	public void testDeclarationVariables() {
		String program = "PROGRAMME var " +
				"VAR ENTIER c1,c2; VAR BOOLEEN b1,b2; " +
				"c1 = 5; " +
				"FPROGRAMME";
		
		String programYVM = "entete\n" +
				"ouvrePrinc 8\n" +
				"iconst 5\n" +
				"istore -2\n" +
				"queue\n";
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Variables declaration in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = "; entete\n" +
				"extrn lirent:proc, ecrent:proc\n" +
				"extrn ecrbool:proc\n" +
				"extrn ecrch:proc, ligsuiv:proc\n" +
				".model SMALL\n" +
				".586\n\n" +
				".CODE\n" +
				"debut :\n" +
				"STARTUPCODE\n\n" +
				"; ouvrePrinc 8\n" +
				"mov bp, sp\n" +
				"sub sp, 8\n\n" +
				"; iconst 5\n" +
				"push word ptr 5\n\n" +
				"; istore -2\n" +
				"pop ax\n" +
				"mov word ptr [bp-2], ax\n\n" +
				"; queue\n" +
				"nop\n" +
				"EXITCODE\n" +
				"end debut\n\n";
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
	public void testDeclaration() {
		String program = "PROGRAMME declar " +
				"CONST aa=10, ba=VRAI, cc=aa; " +
				"VAR ENTIER c1,c2; VAR BOOLEEN b1,b2; " +
				"c1 = 5; " +
				"FPROGRAMME";
		
		String programYVM = "entete\n" +
				"ouvrePrinc 8\n" +
				"iconst 5\n" +
				"istore -2\n" +
				"queue\n";
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Declaration in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = "; entete\n" +
				"extrn lirent:proc, ecrent:proc\n" +
				"extrn ecrbool:proc\n" +
				"extrn ecrch:proc, ligsuiv:proc\n" +
				".model SMALL\n" +
				".586\n\n" +
				".CODE\n" +
				"debut :\n" +
				"STARTUPCODE\n\n" +
				"; ouvrePrinc 8\n" +
				"mov bp, sp\n" +
				"sub sp, 8\n\n" +
				"; iconst 5\n" +
				"push word ptr 5\n\n" +
				"; istore -2\n" +
				"pop ax\n" +
				"mov word ptr [bp-2], ax\n\n" +
				"; queue\n" +
				"nop\n" +
				"EXITCODE\n" +
				"end debut\n\n";
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
	public void testAffectations() {
		String program = "PROGRAMME declar " +
				"VAR ENTIER x,y,z; " +
				"z=(x+y/2)/5; x=y+3*y-4;" +
				"FPROGRAMME";
		
		String programYVM = "entete\n" +
				"ouvrePrinc 6\n" +
				"iload -2\n"+
				"iload -4\n"+
				"iconst 2\n"+
				"idiv\n"+
				"iadd\n"+
				"iconst 5\n"+
				"idiv\n"+
				"istore -6\n"+
				"iload -4\n"+
				"iconst 3\n"+
				"iload -4\n"+
				"imul\n"+
				"iadd\n"+
				"iconst 4\n"+
				"isub\n"+
				"istore -2\n" +
				"queue\n";
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Affectations in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = "; entete\n" +
				"extrn lirent:proc, ecrent:proc\n" +
				"extrn ecrbool:proc\n" +
				"extrn ecrch:proc, ligsuiv:proc\n" +
				".model SMALL\n" +
				".586\n\n" +
				".CODE\n" +
				"debut :\n" +
				"STARTUPCODE\n\n" +
				"; ouvrePrinc 6\n" +
				"mov bp, sp\n" +
				"sub sp, 6\n\n" +
				"; iload -2\n" +
				"push word ptr [bp-2]\n\n" +
				"; iload -4\n" +
				"push word ptr [bp-4]\n\n" +
				"; iconst 2\n" +
				"push word ptr 2\n\n" +
				"; idiv\n" +
				"pop bx\n" +
				"pop ax\n" +
				"cwd\n" +
				"idiv bx\n" +
				"push ax\n\n" +
				"; iadd\n" +
				"pop bx\n" +
				"pop ax\n" +
				"add ax, bx\n" +
				"push ax\n\n" +
				"; iconst 5\n" +
				"push word ptr 5\n\n" +
				"; idiv\n" +
				"pop bx\n" +
				"pop ax\n" +
				"cwd\n" +
				"idiv bx\n" +
				"push ax\n\n" +
				"; istore -6\n" +
				"pop ax\n" +
				"mov word ptr [bp-6], ax\n\n" +
				"; iload -4\n" +
				"push word ptr [bp-4]\n\n" +
				"; iconst 3\n" +
				"push word ptr 3\n\n" +
				"; iload -4\n" +
				"push word ptr [bp-4]\n\n" +
				"; imul\n" +
				"pop bx\n" +
				"pop ax\n" +
				"imul bx\n" +
				"push ax\n\n" +
				"; iadd\n" +
				"pop bx\n" +
				"pop ax\n" +
				"add ax, bx\n" +
				"push ax\n\n" +
				"; iconst 4\n" +
				"push word ptr 4\n\n" +
				"; isub\n" +
				"pop bx\n" +
				"pop ax\n" +
				"sub ax, bx\n" +
				"push ax\n\n" +
				"; istore -2\n" +
				"pop ax\n" +
				"mov word ptr [bp-2], ax\n\n" +
				"; queue\n" +
				"nop\n" +
				"EXITCODE\n" +
				"end debut\n\n";
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
	public void testInputOutput() {
		String program = "PROGRAMME entreeSortie" +
				" VAR ENTIER x,y,z;" +
				" ECRIRE(\"x=\"); " +
				"LIRE(x); " +
				"ALALIGNE; " +
				"ECRIRE(\"y=\"); " +
				"LIRE(y); " +
				"ALALIGNE; " +
				"ECRIRE(\"x+y=\"); " +
				"ECRIRE(x+y); " +
				"ALALIGNE; " +
				"z=(x+y/2)/5; " +
				"x=y+3*y-4; " +
				"FPROGRAMME";
		
		String programYVM = "entete\n" +
				"ouvrePrinc 6\n" +
				"ecrireChaine \"x=\"\n" +
				"lireEnt -2\n" +
				"aLaLigne\n" +
				"ecrireChaine \"y=\"\n" +
				"lireEnt -4\n" +
				"aLaLigne\n" +
				"ecrireChaine \"x+y=\"\n" +
				"iload -2\n" +
				"iload -4\n" +
				"iadd\necrireEnt\n" +
				"aLaLigne\n" +
				"iload -2\n" +
				"iload -4\n" +
				"iconst 2\n" +
				"idiv\n" +
				"iadd\n" +
				"iconst 5\n" +
				"idiv\n" +
				"istore -6\n" +
				"iload -4\n" +
				"iconst 3\n" +
				"iload -4\n" +
				"imul\n" +
				"iadd\n" +
				"iconst 4\n" +
				"isub\n" +
				"istore -2\n" +
				"queue\n";
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Inputs and outputs in YVM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programYVM, Yaka.yvm.getProgram());
		
		String programASM = "; entete\n" +
				"extrn lirent:proc, ecrent:proc\n" +
				"extrn ecrbool:proc\n" +
				"extrn ecrch:proc, ligsuiv:proc\n" +
				".model SMALL\n" +
				".586\n\n" +
				".CODE\n" +
				"debut :\n" +
				"STARTUPCODE\n\n" +
				"; ouvrePrinc 6\n" +
				"mov bp, sp\n" +
				"sub sp, 6\n\n" +
				"; ecrireChaine \"x=\"\n" +
				".DATA\n" +
				"mess0 DB \"x=$\" \n" +
				".CODE\n" +
				"lea dx, mess0\n" +
				"push dx\n" +
				"call ecrch\n\n" +
				"; lireEnt -2\n" +
				"lea dx, [bp-2]\n" +
				"push dx\n" +
				"call lirent\n\n" +
				"; aLaLigne\n" +
				"call ligsuiv\n\n" +
				"; ecrireChaine \"y=\"\n" +
				".DATA\n" +
				"mess1 DB \"y=$\" \n" +
				".CODE\n" +
				"lea dx, mess1\n" +
				"push dx\n" +
				"call ecrch\n\n" +
				"; lireEnt -4\n" +
				"lea dx, [bp-4]\n" +
				"push dx\n" +
				"call lirent\n\n" +
				"; aLaLigne\n" +
				"call ligsuiv\n\n" +
				"; ecrireChaine \"x+y=\"\n" +
				".DATA\n" +
				"mess2 DB \"x+y=$\" \n" +
				".CODE\n" +
				"lea dx, mess2\n" +
				"push dx\n" +
				"call ecrch\n\n" +
				"; iload -2\n" +
				"push word ptr [bp-2]\n\n" +
				"; iload -4\n" +
				"push word ptr [bp-4]\n\n" +
				"; iadd\n" +
				"pop bx\n" +
				"pop ax\n" +
				"add ax, bx\n" +
				"push ax\n\n" +
				"; ecrireEnt\n" +
				"call ecrent\n\n" +
				"; aLaLigne\n" +
				"call ligsuiv\n\n" +
				"; iload -2\n" +
				"push word ptr [bp-2]\n\n" +
				"; iload -4\n" +
				"push word ptr [bp-4]\n\n" +
				"; iconst 2\n" +
				"push word ptr 2\n\n" +
				"; idiv\n" +
				"pop bx\n" +
				"pop ax\n" +
				"cwd\n" +
				"idiv bx\n" +
				"push ax\n\n" +
				"; iadd\n" +
				"pop bx\n" +
				"pop ax\n" +
				"add ax, bx\n" +
				"push ax\n\n" +
				"; iconst 5\n" +
				"push word ptr 5\n\n" +
				"; idiv\n" +
				"pop bx\n" +
				"pop ax\n" +
				"cwd\n" +
				"idiv bx\n" +
				"push ax\n\n" +
				"; istore -6\n" +
				"pop ax\n" +
				"mov word ptr [bp-6], ax\n\n" +
				"; iload -4\n" +
				"push word ptr [bp-4]\n\n" +
				"; iconst 3\n" +
				"push word ptr 3\n\n" +
				"; iload -4\n" +
				"push word ptr [bp-4]\n\n" +
				"; imul\n" +
				"pop bx\n" +
				"pop ax\n" +
				"imul bx\n" +
				"push ax\n\n" +
				"; iadd\n" +
				"pop bx\n" +
				"pop ax\n" +
				"add ax, bx\n" +
				"push ax\n\n" +
				"; iconst 4\n" +
				"push word ptr 4\n\n" +
				"; isub\n" +
				"pop bx\n" +
				"pop ax\n" +
				"sub ax, bx\n" +
				"push ax\n\n" +
				"; istore -2\n" +
				"pop ax\n" +
				"mov word ptr [bp-2], ax\n\n" +
				"; queue\n" +
				"nop\n" +
				"EXITCODE\n" +
				"end debut\n\n";
		compileToASM(program);
		if(this.showASMCode) {
			System.out.println("Inputs and outputs in ASM:");
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
}