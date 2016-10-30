package LexerTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Lexer.Lexer;
import Lexer.Lexer.TokenType;
import Lexer.Task1;
import Lexer.Token;

public class Task1Test {

	Task1 task1;
	Lexer machine;
	@Before
	public void setUp(){
			task1 = new Task1();
			machine = task1.create();
	}
	
   	public TokenType which(Token t){
		String x = t.getClass().getName();
		
		switch(x){
		case "Lexer.T_Semicolon": return TokenType.SEMICOLON;
		case "Lexer.T_LeftBracket": return TokenType.LEFTBRACKET;
		case "Lexer.T_RightBracket": return TokenType.RIGHTBRACKET;
		case "Lexer.T_EqualDefines": return TokenType.EQUALSDEFINES;
		case "Lexer.T_Equal": return TokenType.EQUAL;
		case "Lexer.T_LessThan": return TokenType.LESSTHAN;
		case "Lexer.T_GreaterThan": return TokenType.GREATERTHAN;
		case "Lexer.T_LessEq": return TokenType.LESSEQ;
		case "Lexer.T_GreaterEq": return TokenType.GREATEREQ;
		case "Lexer.T_Comma": return TokenType.COMMA;
		case "Lexer.T_LeftCurlyBracket": return TokenType.LEFTCURLYBRACKET;
		case "Lexer.T_RightCurlyBracket": return TokenType.RIGHTCURLYBRACKET;
		case "Lexer.T_Assign": return TokenType.ASSIGN;
		case "Lexer.T_Plus": return TokenType.PLUS;
		case "Lexer.T_Times": return TokenType.TIMES;
		case "Lexer.T_Minus": return TokenType.MINUS;
		case "Lexer.T_Div": return TokenType.DIV;
		case "Lexer.T_Def": return TokenType.DEF;
		case "Lexer.T_Skip": return TokenType.SKIP;
		case "Lexer.T_If": return TokenType.IF;
		case "Lexer.T_Then": return TokenType.THEN;
		case "Lexer.T_Else": return TokenType.ELSE;
		case "Lexer.T_While": return TokenType.WHILE;
		case "Lexer.T_Do": return TokenType.DO;
		case "Lexer.T_Repeat": return TokenType.REPEAT;
		case "Lexer.T_Until": return TokenType.UNTIL;
		case "Lexer.T_Break": return TokenType.BREAK;
		case "Lexer.T_Continue": return TokenType.CONTINUE;
		case "Lexer.T_Integer": return TokenType.INTEGER;
		case "Lexer.T_Identifier": return TokenType.IDENTIFIER;
		default:
			return null;
		}
   	}
   	
	@Test
	public void general() {
		try{
			for (Token tok:machine.lex(" -12 - 8+(())<> hello boris")){
				System.out.println(tok+"\t\t\t hello");
				which(tok);
				System.out.println(tok.getClass());
			 }
			}catch (Exception e) {
				System.out.println("whoops");
			}
	}
	

	
}
