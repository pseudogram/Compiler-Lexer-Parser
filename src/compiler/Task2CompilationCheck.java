package compiler;

import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;

public class Task2CompilationCheck {

	public void g () {

		SyntaxException se = new SyntaxException ( "" );
		String s = se.msg;
		Task2Exception te = new Task2Exception ( "" );
		s = te.msg;

		IntLiteral intLiteral = new IntLiteral ( 5 );
		int n = intLiteral.n;

		Block block = new Block ( new ArrayList<Exp> ( asList ( intLiteral ) ) );
		List <Exp> le = block.exps;

		Skip skip = new Skip ();

		BlockExp blockExp = new BlockExp ( block );

	} 

	void f () {	
		try {
			Parser p = Task2.create ();
			List<Token> tl = null;
			p.parse ( tl ); }
		catch ( Exception e ) {} }

	public static void main ( String [] args ) {
		System.out.println ( "Hello I'm the compilation tester for Task 2." );
		System.out.println ( "I only test if the submission can be compiled." ); 
		System.out.println ( "I do NOT test if the right functionality is implemented." ); } 

}
