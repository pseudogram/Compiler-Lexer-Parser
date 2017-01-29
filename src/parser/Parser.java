package parser;


import java.util.List;

import lexer.Token;

interface Parser {
    public Block parse ( List < Token > input ) throws SyntaxException, 
                                               Task2Exception; }
