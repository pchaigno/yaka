package compilateur;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

/**
 * Unit tests.
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
	
	public void testIteration() {
		String program = "PROGRAMME iter1 " +
				"VAR ENTIER s,i,n; " +
				"n=5; " +
				"i=1; " +
				"s=0; " +
				"TANTQUE i<=n " +
				"FAIRE s=s+i; i=i+1; " +
				"FAIT " +
				"FPROGRAMME";
		
		String programYVM = "entete\n" +
				"ouvrePrinc 6\n" +
				"iconst 5\n" +
				"istore -6\n" +
				"iconst 1\n" +
				"istore -4\n" +
				"iconst 0\n" +
				"istore -2\n" +
				"FAIRE1 :\n" +
				"iload -4\n" +
				"iload -6\n" +
				"iinfegal\n" +
				"iffaux FAIT1\n" +
				"iload -2\n" +
				"iload -4\n" +
				"iadd\n" +
				"istore -2\n" +
				"iload -4\n" +
				"iconst 1\n" +
				"iadd\n" +
				"istore -4\n" +
				"goto FAIRE1\n" +
				"FAIT1 :\n" +
				"queue\n";
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Simple iteration in YVM:");
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
				"; iconst 5\n" +
				"push word ptr 5\n\n" +
				"; istore -6\n" +
				"pop ax\n" +
				"mov word ptr [bp-6], ax\n\n" +
				"; iconst 1\n" +
				"push word ptr 1\n\n" +
				"; istore -4\n" +
				"pop ax\n" +
				"mov word ptr [bp-4], ax\n\n" +
				"; iconst 0\n" +
				"push word ptr 0\n\n" +
				"; istore -2\n" +
				"pop ax\n" +
				"mov word ptr [bp-2], ax\n\n" +
				"; iload -4\n" +
				"push word ptr [bp-4]\n\n" +
				"; iload -6\n" +
				"push word ptr [bp-6]\n\n" +
				"; iinfegal\n" +
				"pop bx\n" +
				"pop ax\n" +
				"cmp ax, bx\n" +
				"jg $+6\n" +
				"push -1\n" +
				"jmp $+4\n" +
				"push 0\n\n" +
				"; iload -2\n" +
				"push word ptr [bp-2]\n\n" +
				"; iload -4\n" +
				"push word ptr [bp-4]\n\n" +
				"; iadd\n" +
				"pop bx\n" +
				"pop ax\n" +
				"add ax, bx\n" +
				"push ax\n\n" +
				"; istore -2\npop ax\n" +
				"mov word ptr [bp-2], ax\n\n" +
				"; iload -4\n" +
				"push word ptr [bp-4]\n\n" +
				"; iconst 1\n" +
				"push word ptr 1\n\n" +
				"; iadd\n" +
				"pop bx\n" +
				"pop ax\n" +
				"add ax, bx\n" +
				"push ax\n\n" +
				"; istore -4\n" +
				"pop ax\n" +
				"mov word ptr [bp-4], ax\n\n" +
				"; queue\n" +
				"nop\n" +
				"EXITCODE\n" +
				"end debut\n\n";
		compileToASM(program);
		if(this.showASMCode) {
			System.out.println("Simple iteration in ASM:");
			System.out.println(Yaka.yvm.getProgram());
		}
		assertEquals(programASM, Yaka.yvm.getProgram());
	}
	
	public void testIterationImbriquee() {
		String program = "PROGRAMME iter1 " +
				"VAR ENTIER i,j,n; " +
				"n=5; " +
				"i=1; " +
				"j=1; " +
				"TANTQUE i<=n " +
				"FAIRE i=i+1; " +
				"TANTQUE j<=n " +
				"FAIRE j=j+1; " +
				"FAIT; " +
				"TANTQUE j<=n " +
				"FAIRE j=j+1; " +
				"FAIT " +
				"FAIT " +
				"FPROGRAMME";
		
		String programYVM = "entete\n" +
				"ouvrePrinc 6\n" +
				"iconst 5\n" +
				"istore -6\n" +
				"iconst 1\n" +
				"istore -2\n" +
				"iconst 1\n" +
				"istore -4\n" +
				"FAIRE1 :\n" +
				"iload -2\n" +
				"iload -6\n" +
				"iinfegal\n" +
				"iffaux FAIT1\n" +
				"iload -2\n" +
				"iconst 1\n" +
				"iadd\n" +
				"istore -2\n" +
				"FAIRE2 :\n" +
				"iload -4\n" +
				"iload -6\n" +
				"iinfegal\n" +
				"iffaux FAIT2\n" +
				"iload -4\n" +
				"iconst 1\n" +
				"iadd\n" +
				"istore -4\n" +
				"goto FAIRE2\n" +
				"FAIT2 :\n" +
				"FAIRE3 :\n" +
				"iload -4\n" +
				"iload -6\n" +
				"iinfegal\n" +
				"iffaux FAIT3\n" +
				"iload -4\n" +
				"iconst 1\n" +
				"iadd\n" +
				"istore -4\n" +
				"goto FAIRE3\n" +
				"FAIT3 :\n" +
				"goto FAIRE1\n" +
				"FAIT1 :\nqueue\n";
		compileToYVM(program);
		if(this.showYVMCode) {
			System.out.println("Nested iterations in YVM:");
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
				"debut :\nSTARTUPCODE\n\n" +
				"; ouvrePrinc 6\nmov bp, sp\nsub sp, 6\n\n" +
				"; iconst 5\npush word ptr 5\n\n; istore -6\n" +
				"pop ax\nmov word ptr [bp-6], ax\n\n" +
				"; iconst 1\npush word ptr 1\n\n" +
				"; istore -2\npop ax\nmov word ptr [bp-2], ax\n\n" +
				"; iconst 1\npush word ptr 1\n\n" +
				"; istore -4\npop ax\nmov word ptr [bp-4], ax\n\n" +
				"; iload -2\npush word ptr [bp-2]\n\n" +
				"; iload -6\npush word ptr [bp-6]\n\n" +
				"; iinfegal\npop bx\npop ax\ncmp ax, bx\n" +
				"jg $+6\npush -1\njmp $+4\npush 0\n\n" +
				"; iload -2\npush word ptr [bp-2]\n\n" +
				"; iconst 1\npush word ptr 1\n\n; iadd\n" +
				"pop bx\npop ax\nadd ax, bx\npush ax\n\n" +
				"; istore -2\npop ax\nmov word ptr [bp-2], ax\n\n" +
				"; iload -4\npush word ptr [bp-4]\n\n" +
				"; iload -6\npush word ptr [bp-6]\n\n; iinfegal\n" +
				"pop bx\npop ax\ncmp ax, bx\njg $+6\npush -1\n" +
				"jmp $+4\npush 0\n\n; iload -4\n" +
				"push word ptr [bp-4]\n\n; iconst 1\n" +
				"push word ptr 1\n\n; iadd\npop bx\n" +
				"pop ax\nadd ax, bx\npush ax\n\n" +
				"; istore -4\npop ax\nmov word ptr [bp-4], ax\n\n" +
				"; iload -4\npush word ptr [bp-4]\n\n" +
				"; iload -6\npush word ptr [bp-6]\n\n" +
				"; iinfegal\npop bx\npop ax\ncmp ax, bx\n" +
				"jg $+6\npush -1\njmp $+4\npush 0\n\n" +
				"; iload -4\npush word ptr [bp-4]\n\n" +
				"; iconst 1\npush word ptr 1\n\n" +
				"; iadd\npop bx\npop ax\nadd ax, bx\npush ax\n\n" +
				"; istore -4\npop ax\nmov word ptr [bp-4], ax\n\n" +
				"; queue\nnop\nEXITCODE\nend debut\n\n";
		compileToASM(program);
		if(this.showASMCode) {
			System.out.println("Nested iterations in ASM:");
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