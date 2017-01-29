package parser;


abstract class Exp {} 

class IntLiteral extends Exp { 
    public int n;
    IntLiteral ( int _n ) {
    	n = _n; }
    }

class Skip extends Exp { 
    public Skip () {} }

class BlockExp extends Exp { 
    public Block b;
    public BlockExp ( Block _b ) { b = _b; } }

