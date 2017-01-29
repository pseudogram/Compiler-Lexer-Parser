package lexer;

public class LexicalException extends Exception {

	private static final long serialVersionUID = 4860896829014714383L;
	public String msg;
    public LexicalException ( String _msg ) { msg = _msg; } }