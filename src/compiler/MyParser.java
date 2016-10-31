package compiler;

import java.util.ArrayList;
import java.util.List;

public class MyParser implements Parser{

	List<Token> tokens;
	
	@Override
	public Block parse(List<Token> input) throws SyntaxException {
		tokens = input;
		// TODO Auto-generated method stub
		Block AST = blockParser();
		//System.out.println(tokens);
		return AST;
	}
	
	private Block blockParser() throws SyntaxException{
		if (tokens.get(0) instanceof T_LeftCurlyBracket){
			tokens.remove(0);
			Block b = new Block(eneParser());
			if(tokens.get(0) instanceof T_RightCurlyBracket){
				return b;
			}else{
				throw new SyntaxException("Closing curly bracket missing");
			}
		}
		throw new SyntaxException("Closing curly bracket missing");
	}
	
	private List<Exp> eneParser() throws SyntaxException{
		List<Exp> list = new ArrayList<Exp>();
		list.add(eParser());
		if (tokens.get(0) instanceof T_Semicolon) {
			tokens.remove(0);
			list.add(eParser());
		}
		return list;
	}

	private Exp eParser() throws SyntaxException {
		if (tokens.get(0) instanceof T_Integer){
			IntLiteral i = new IntLiteral(((T_Integer)tokens.get(0)).n);
			System.out.println(((T_Integer)tokens.get(0)).n);
			tokens.remove(0);
			return i;
		}else if (tokens.get(0) instanceof T_Skip){
			Skip s = new Skip();
			System.out.println(tokens.get(0));
			tokens.remove(0);
			return s;
		}else if (tokens.get(0) instanceof T_LeftCurlyBracket){
			BlockExp b = new BlockExp(blockParser());
			System.out.println(tokens.get(0));
			tokens.remove(0);
			return b;
		}else{
			throw new SyntaxException("");
		}
	}
}
