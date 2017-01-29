package parser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import lexer.LexicalException;
import lexer.MyLexer;
import lexer.T_Integer;
import lexer.T_LeftCurlyBracket;
import lexer.T_RightCurlyBracket;
import lexer.T_Skip;
import lexer.Task1;
import lexer.Task1Exception;
import lexer.Token;

public class ParserTest {
	Parser p = Task2.create();

	@Test
	public void testSimpleIntBlock() throws SyntaxException, Task2Exception {
		ArrayList<Token> tl = new ArrayList<>();
		tl.addAll(Arrays.asList(new T_LeftCurlyBracket(), new T_Integer(0), new T_RightCurlyBracket()));
		Block b = p.parse(tl);
		assertTrue(b.exps.get(0) instanceof IntLiteral);
	}

	@Test
	public void testParseENE() throws LexicalException, Task1Exception, SyntaxException, Task2Exception {
		ArrayList<Token> tl = new ArrayList<>();
		tl.addAll(Task1.create().lex("{0;1}"));
		Block b = p.parse(tl);
		assertTrue(b.exps.get(0) instanceof IntLiteral);
		assertTrue(b.exps.get(1) instanceof IntLiteral);
	}

	@Test
	public void testBlockExpressionBlock() throws SyntaxException, Task2Exception {
		ArrayList<Token> tl = new ArrayList<>();
		tl.addAll(Arrays.asList(new T_LeftCurlyBracket(), new T_LeftCurlyBracket(), new T_Skip(),new T_RightCurlyBracket(), new T_RightCurlyBracket()));
		Block b = p.parse(tl);
		assertTrue(b.exps.get(0) instanceof BlockExp);
		BlockExp be = (BlockExp) b.exps.get(0);
		Block b1 = be.b;
		assertTrue(b1.exps.get(0) instanceof Skip);
	}

	@Test
	public void test2BlocksIn1() throws LexicalException, Task1Exception, SyntaxException, Task2Exception {
		ArrayList<Token> tl = new ArrayList<>();
		tl.addAll(Task1.create().lex("{{skip};1}"));
		Block b = p.parse(tl);
		BlockExp be = (BlockExp) b.exps.get(0);
		Block b1 = be.b;
		assertTrue(b1.exps.get(0) instanceof Skip);
		assertTrue(b.exps.get(1) instanceof IntLiteral);
	}

	@Test
	public void testBlockSeparatedBySemicolon()
			throws LexicalException, Task1Exception, SyntaxException, Task2Exception {
		ArrayList<Token> tl = new ArrayList<>();
		tl.addAll(Task1.create().lex("{{0};{0}}"));
		Block b = p.parse(tl);
		printBlock(b);
		assertTrue(b.exps.get(0) instanceof BlockExp);
		assertTrue(b.exps.get(1) instanceof BlockExp);
	}

	private MyLexer l = Task1.create();

	@Test(expected = SyntaxException.class)
	public void testFailure() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{skip;skip;66;}");
		Block b = p.parse(tl);
	}

	@Test(expected = SyntaxException.class)
	public void testFailure2() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("93{54;skip");
		Block b = p.parse(tl);
	}

	@Test(expected = SyntaxException.class)
	public void testFailure3() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{43;skip");
		Block b = p.parse(tl);
	}

	@Test(expected = SyntaxException.class)
	public void testFailure4() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{43;;skip}");
		Block b = p.parse(tl);
	}

	@Test(expected = SyntaxException.class)
	public void testFailure5() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{{78;skip}");
		Block b = p.parse(tl);
	}

	@Test(expected = SyntaxException.class)
	public void testFailure6() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{4;}{123;skip}");
		Block b = p.parse(tl);
	}
	
	@Test(expected = SyntaxException.class)
	public void testFailure7() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{{}}");
		Block b = p.parse(tl);
	}
	
	@Test(expected = SyntaxException.class)
	public void testFailure8() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{4}}");
		Block b = p.parse(tl);
	}

	@Test
	public void test3FirstBrackets() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{{{23};skip};43}");
		Block b = p.parse(tl);
		BlockExp be = (BlockExp) b.exps.get(0);
		Block b1 = be.b;
		assertTrue(b1.exps.get(0) instanceof BlockExp);
		BlockExp be2 = (BlockExp) b1.exps.get(0);
		assertTrue(be2.b.exps.get(0) instanceof IntLiteral);
		assertTrue(b1.exps.get(1) instanceof Skip);
		assertTrue(b.exps.get(1) instanceof IntLiteral);
	}

	@Test
	public void testBlockThenInt() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{{skip;66};43}");
		Block b = p.parse(tl);
		BlockExp be = (BlockExp) b.exps.get(0);
		Block b1 = be.b;
		assertTrue(b1.exps.get(0) instanceof Skip);
		assertTrue(b1.exps.get(1) instanceof IntLiteral);
		assertTrue(b.exps.get(1) instanceof IntLiteral);
	}

	@Test
	public void testDoubleBlock() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{{31231};{skip}}");
		Block b = p.parse(tl);
		BlockExp be = (BlockExp) b.exps.get(0);
		Block b1 = be.b;
		assertTrue(b1.exps.get(0) instanceof IntLiteral);
		BlockExp be2 = (BlockExp) b.exps.get(1);
		Block b2 = be2.b;
		assertTrue(b2.exps.get(0) instanceof Skip);
	}

	@Test
	public void testDoubleBracket() throws SyntaxException, Task2Exception, LexicalException, Task1Exception {
		List<Token> tl = l.lex("{{4}}");
		Block b = p.parse(tl);
		BlockExp be = (BlockExp) b.exps.get(0);
		Block b1 = be.b;
		assertTrue(b1.exps.get(0) instanceof IntLiteral);
	}

	static public void printBlock(Block b) {
		for (Exp e : b.exps) {
			if (e instanceof BlockExp) {
				System.out.println("Block start");
				printBlock(((BlockExp) e).b);
				System.out.println("Block end");
			} else {
				System.out.println(e.getClass().getName());
			}
		}
	}
}