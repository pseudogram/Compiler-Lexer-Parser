package compiler;

import java.util.List;

interface Parser {
    public Block parse ( List < Token > input ) throws SyntaxException, 
                                               Task2Exception; }
