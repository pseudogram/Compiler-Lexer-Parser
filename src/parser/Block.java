package parser;


import java.util.List;

class Block {
    public List <Exp> exps;
    public Block ( List <Exp> _exps ) {
	assert ( _exps.size () > 0 );
	exps = _exps; } }

// The member variable exps should contain expressions in the same
// order as the program that the Block AST represent. E.g.
//
//     { 2; SKIP; 4 }
// 
// gives rise to something like the following list exps:
//
//    List ( new IntLiteral ( 2 ), new Skip (); new IntLiteral ( 4 ) )
