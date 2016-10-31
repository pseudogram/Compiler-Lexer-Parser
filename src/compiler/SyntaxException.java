package compiler;

class SyntaxException extends Exception {
	private static final long serialVersionUID = 1484020357726503037L;
	public String msg;
    public SyntaxException ( String _msg ) { msg = _msg; } 
    }
