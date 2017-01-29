package lexer;

import java.util.List;

interface Lexer {
    public List<Token> lex ( String input ) throws LexicalException, 
                                                   Task1Exception; }