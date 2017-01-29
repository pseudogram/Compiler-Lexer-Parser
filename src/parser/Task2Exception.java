package parser;


class Task2Exception extends Exception {
	private static final long serialVersionUID = 6277106036817619238L;
	public String msg;
    public Task2Exception ( String _msg ) { msg = _msg; } }