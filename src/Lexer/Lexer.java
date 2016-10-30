package Lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 
 * @author Dattlee
 */
public class Lexer {
	
	/**
	 * The String representation of each Token.
	 */
	public static enum TokenType {
		//
		//Numbers
		//
		INTEGER("-?[0-9]+", new T_Integer(0)),
		//
		// Operators and Structural Symbols
		//
		SEMICOLON(";", new T_Semicolon()), LEFTBRACKET("[(]", new T_LeftBracket()), RIGHTBRACKET("[)]", new T_RightBracket()), EQUALSDEFINES("=", new T_EqualDefines()),
		EQUAL("==", new T_Equal()), LESSTHAN("<", new T_LessThan()), GREATERTHAN(">", new T_GreaterThan()), LESSEQ("<=", new T_LessEq()), GREATEREQ(">=", new T_GreaterEq()),
		COMMA(",", new T_Comma()), LEFTCURLYBRACKET("[{]", new T_LeftCurlyBracket()), RIGHTCURLYBRACKET("[}]", new T_RightCurlyBracket()), ASSIGN(":=", new T_Assign()),
		PLUS("[+]", new T_Plus()),TIMES("[*]", new T_Times()),MINUS("[-]", new T_Minus()), DIV("/", new T_Div()), 
		
		//
		// Keywords
		//
		DEF("def(?!\\w)", new T_Def()), SKIP("skip(?!\\w)", new T_Skip()), IF("if(?!\\w)", new T_If()), THEN("then(?!\\w)", new T_Then()),
		ELSE("else(?!\\w)", new T_Else()), WHILE("while(?!\\w)", new T_While()), DO("do(?!\\w)", new T_Do()), REPEAT("repeat(?!\\w)", new T_Repeat()),
		UNTIL("until(?!\\w)", new T_Until()), BREAK("break(?!\\w)", new T_Break()), CONTINUE("continue(?!\\w)", new T_Continue()),

		//
		// Text and space
		// 
		IDENTIFIER("[a-z]\\w*", new T_Identifier("")), WHITESPACE("[ \n\f\r\t]+", null),
		
		//
		// Errors
		//
		IDENTIFIERFIRSTCHAR(".(\\w*)?", null);
		
		public final String pattern;
		public final Token token;
		
		private TokenType(String pattern, Token token) {
			this.pattern = pattern;
			this.token = token;
		}
	}
	
	
	

	/*public static class TokenSet {
		public TokenType type;
		public String data;

		@Override
		public String toString() {
			return String.format("(%s %s)", type.name(), data);
		}
	}*/
	
	

	public ArrayList<Token> lex(String input) throws LexicalException, Task1Exception{
		//
		// The TokenSets to return
		//
//		ArrayList<TokenSet> tokenSets = new ArrayList<TokenSet>();
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		//
		// Lexer logic begins here
		//
		StringBuffer tokenPatternsBuffer = new StringBuffer();
		for (TokenType tokenType : TokenType.values()){
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));}
		
		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));
		//
		// Begin assigning Tokens to character groups
		//
		Matcher matcher = tokenPatterns.matcher(input);
		while (matcher.find()) {
			if (matcher.group(TokenType.SEMICOLON.name()) != null) {
				tokens.add(TokenType.SEMICOLON.token);
				continue;
			} else if (matcher.group(TokenType.LEFTBRACKET.name()) != null) {
				tokens.add(TokenType.LEFTBRACKET.token);
				continue;
			} else if (matcher.group(TokenType.RIGHTBRACKET.name()) != null) {
				tokens.add(TokenType.RIGHTBRACKET.token);
				continue;
			} else if (matcher.group(TokenType.EQUALSDEFINES.name()) != null) {
				tokens.add(TokenType.EQUALSDEFINES.token);
				continue;
			} else if (matcher.group(TokenType.EQUAL.name()) != null) {
				tokens.add(TokenType.EQUAL.token);
				continue;
			} else if (matcher.group(TokenType.LESSTHAN.name()) != null) {
				tokens.add(TokenType.LESSTHAN.token);
				continue;
			} else if (matcher.group(TokenType.GREATERTHAN.name()) != null) {
				tokens.add(TokenType.GREATERTHAN.token);
				continue;
			} else if (matcher.group(TokenType.LESSEQ.name()) != null) {
				tokens.add(TokenType.LESSEQ.token);
				continue;
			} else if (matcher.group(TokenType.GREATEREQ.name()) != null) {
				tokens.add(TokenType.GREATEREQ.token);
				continue;
			} else if (matcher.group(TokenType.COMMA.name()) != null) {
				tokens.add(TokenType.COMMA.token);
				continue;
			} else if (matcher.group(TokenType.LEFTCURLYBRACKET.name()) != null) {
				tokens.add(TokenType.LEFTCURLYBRACKET.token);
				continue;
			} else if (matcher.group(TokenType.RIGHTCURLYBRACKET.name()) != null) {
				tokens.add(TokenType.RIGHTCURLYBRACKET.token);
				continue;
			} else if (matcher.group(TokenType.ASSIGN.name()) != null) {
				tokens.add(TokenType.ASSIGN.token);
				continue;
			} else if (matcher.group(TokenType.PLUS.name()) != null) {
				tokens.add(TokenType.PLUS.token);
				continue;
			} else if (matcher.group(TokenType.MINUS.name()) != null) {
				tokens.add(TokenType.MINUS.token);
				continue;
			} else if (matcher.group(TokenType.TIMES.name()) != null) {
				tokens.add(TokenType.TIMES.token);
				continue;
			} else if (matcher.group(TokenType.DIV.name()) != null) {
				tokens.add(TokenType.DIV.token);
				continue;
			} else if (matcher.group(TokenType.DEF.name()) != null) {
				tokens.add(TokenType.DEF.token);
				continue;
			} else if (matcher.group(TokenType.SKIP.name()) != null) {
				tokens.add(TokenType.SKIP.token);
				continue;
			} else if (matcher.group(TokenType.IF.name()) != null) {
				tokens.add(TokenType.IF.token);
				continue;
			} else if (matcher.group(TokenType.THEN.name()) != null) {
				tokens.add(TokenType.THEN.token);
				continue;
			} else if (matcher.group(TokenType.ELSE.name()) != null) {
				tokens.add(TokenType.ELSE.token);
				continue;
			} else if (matcher.group(TokenType.WHILE.name()) != null) {
				tokens.add(TokenType.WHILE.token);
				continue;
			} else if (matcher.group(TokenType.DO.name()) != null) {
				tokens.add(TokenType.DO.token);
				continue;
			} else if (matcher.group(TokenType.REPEAT.name()) != null) {
				tokens.add(TokenType.REPEAT.token);
				continue;
			} else if (matcher.group(TokenType.UNTIL.name()) != null) {
				tokens.add(TokenType.UNTIL.token);
				continue;
			} else if (matcher.group(TokenType.BREAK.name()) != null) {
				tokens.add(TokenType.BREAK.token);
				continue;
			} else if (matcher.group(TokenType.CONTINUE.name()) != null) {
				tokens.add(TokenType.CONTINUE.token);
				continue;
			} else if (matcher.group(TokenType.INTEGER.name()) != null) {
				try{
					tokens.add(new T_Integer(Integer.parseInt(matcher.group(TokenType.INTEGER.name()))));
					System.out.println(matcher.group(TokenType.INTEGER.name()));
				}catch(NumberFormatException e){
					System.out.println("Incorrect Number Format");
				}
				continue;
			} else if (matcher.group(TokenType.IDENTIFIER.name()) != null) {
				tokens.add(new T_Identifier(matcher.group(TokenType.IDENTIFIER.name())));
				System.out.println(matcher.group(TokenType.IDENTIFIER.name()));
				continue;
			} else if (matcher.group(TokenType.WHITESPACE.name()) != null) {
				continue;
			} else if (matcher.group(TokenType.IDENTIFIERFIRSTCHAR.name()) != null) {
				throw new LexicalException("First character of an identifier doesn't start with lower case letter");
			}
		}

		return tokens;
	}
	
}
