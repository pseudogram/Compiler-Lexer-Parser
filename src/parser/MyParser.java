package parser;

import java.util.ArrayList;
import java.util.List;

import lexer.T_Integer;
import lexer.T_LeftCurlyBracket;
import lexer.T_RightCurlyBracket;
import lexer.T_Semicolon;
import lexer.T_Skip;
import lexer.Token;

public class MyParser implements Parser{

	List<Token> tokens;
	
	@Override
	public Block parse(List<Token> input) throws SyntaxException,  Task2Exception {
		tokens = input;
		// TODO Auto-generated method stub
		Block AST = blockParser();
		System.out.println(tokens);
		tokens.remove(0);
		if (!tokens.isEmpty()){
			throw new SyntaxException("");
		}
		return AST;
	}
	
	private Token grabToken() throws Task2Exception, SyntaxException {
		try{
			return tokens.get(0);
		}catch (IndexOutOfBoundsException e){
			throw new SyntaxException("Syntax Error, expected grammar completion");
		}catch (Exception e){
			throw new Task2Exception("");
		}
	}
	
	private Block blockParser() throws SyntaxException, Task2Exception{
		if (grabToken() instanceof T_LeftCurlyBracket){
			Block b = new Block(eneParser());
			if(grabToken() instanceof T_RightCurlyBracket){
				return b;
			}
		}
		throw new SyntaxException("Closing curly bracket missing");
	}
	
	private List<Exp> eneParser() throws SyntaxException, Task2Exception {
		List<Exp> list = new ArrayList<Exp>();
		do{
			tokens.remove(0);
			list.add(eParser());
		}while(grabToken() instanceof T_Semicolon);
		return list;
	}

	private Exp eParser() throws SyntaxException,  Task2Exception {
		if (grabToken() instanceof T_Integer){
			IntLiteral i = new IntLiteral(((T_Integer)tokens.get(0)).n);
			tokens.remove(0);
			return i;
		}else if (grabToken() instanceof T_Skip){
			Skip s = new Skip();
			tokens.remove(0);
			return s;
		}else if (grabToken() instanceof T_LeftCurlyBracket){
			BlockExp b = new BlockExp(blockParser());
			tokens.remove(0);
			return b;
		}else{
			throw new SyntaxException("");
		}
	}
}