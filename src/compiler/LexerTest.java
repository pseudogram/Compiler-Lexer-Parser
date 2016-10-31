package compiler;

import static org.junit.Assert.*;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
 
import org.junit.*;
 
public class LexerTest {
    private String input;
    private Lexer la = Task1.create();
   
    @Test
    public void testPartialKeywordIdentifiers() throws LexicalException, Task1Exception{
        input = "de whil continu ski i d the els repea unti brea";
        String words[] = input.split("\\s+");
        T_Identifier partialIdentifiers[] = new T_Identifier[11];
        List<Token> tl = la.lex(input);
        assertFalse(tl.size() == 0);
        for(int i = 0; i < 11; i++){
            partialIdentifiers[i] = (T_Identifier) tl.get(i);
            assertTrue(partialIdentifiers[i].s.equals(words[i]));
        }
    }
 
    @Test
    public void testDef() {
        input = "def";
        try {
            assertTrue(la.lex(input).get(0) instanceof T_Def);
        } catch (LexicalException | Task1Exception e) {
            e.printStackTrace();
        }
    }
   
    @Test
    public void testGreed() throws LexicalException, Task1Exception{
        input = "define bob+joe";
        assertTrue(la.lex(input).get(0) instanceof T_Identifier);
        T_Identifier defi = (T_Identifier) la.lex(input).get(0);
        assertTrue(defi.s.equals("define"));
        assertTrue(la.lex(input).get(1) instanceof T_Identifier);
        assertTrue(la.lex(input).get(2) instanceof T_Plus);
        assertTrue(la.lex(input).get(3) instanceof T_Identifier);
    }
   
    @Test
    public void crazyTest() throws LexicalException, Task1Exception{
        input =";;{{{}}{{{ {{}}}} }}}}}}}}10 10 if if then then then else";
        assertTrue(la.lex(input).size() == 32);
    }
   
    @Test
    public void partialTest() throws LexicalException, Task1Exception{
        input = "def f(x,y,z)";
        List<Token> tl = la.lex(input);
        printTokens(tl);
        assertTrue(tl.get(0) instanceof T_Def);
        assertTrue(tl.get(1) instanceof T_Identifier);
        T_Identifier f = (T_Identifier) tl.get(1);
        assertTrue(f.s.equals("f"));
        assertTrue(tl.get(2) instanceof T_LeftBracket);
        assertTrue(tl.get(3) instanceof T_Identifier);
        T_Identifier x = (T_Identifier) tl.get(3);
        assertTrue(x.s.equals("x"));
        assertTrue(tl.get(4) instanceof T_Comma);
        assertTrue(tl.get(5) instanceof T_Identifier);
        T_Identifier y = (T_Identifier) tl.get(5);
        assertTrue(y.s.equals("y"));
        assertTrue(tl.get(6) instanceof T_Comma);
        assertTrue(tl.get(7) instanceof T_Identifier);
        T_Identifier z = (T_Identifier) tl.get(7);
        assertTrue(z.s.equals("z"));
        assertTrue(tl.get(8) instanceof T_RightBracket);
    }
    @Test
    public void fullTest() throws LexicalException, Task1Exception{
        input = "def f(x,y,z) = { if x == y then { z } else { 0 } }";
        List<Token> tl = la.lex(input);
        printTokens(tl);
       
        assertTrue(tl.get(0) instanceof T_Def); //def
        assertTrue(tl.get(1) instanceof T_Identifier); //f
        T_Identifier f = (T_Identifier) tl.get(1);
        assertTrue(f.s.equals("f"));
        assertTrue(tl.get(2) instanceof T_LeftBracket); //(
        assertTrue(tl.get(3) instanceof T_Identifier);//x
        T_Identifier x = (T_Identifier) tl.get(3);
        assertTrue(x.s.equals("x"));
        assertTrue(tl.get(4) instanceof T_Comma);//,
        assertTrue(tl.get(5) instanceof T_Identifier);//y
        T_Identifier y = (T_Identifier) tl.get(5);
        assertTrue(y.s.equals("y"));
        assertTrue(tl.get(6) instanceof T_Comma);//,
        assertTrue(tl.get(7) instanceof T_Identifier);//z
        T_Identifier z = (T_Identifier) tl.get(7);
        assertTrue(z.s.equals("z"));
        assertTrue(tl.get(8) instanceof T_RightBracket);//)
        assertTrue(tl.get(9) instanceof T_EqualDefines);//=
        assertTrue(tl.get(10) instanceof T_LeftCurlyBracket);//{
        assertTrue(tl.get(11) instanceof T_If);//if
        assertTrue(tl.get(12) instanceof T_Identifier); //x
        x = (T_Identifier) tl.get(12);
        assertTrue(x.s.equals("x"));
        System.out.println(x);
        assertTrue(tl.get(13) instanceof T_Equal);//==
        assertTrue(tl.get(14) instanceof T_Identifier);//y
        y = (T_Identifier) tl.get(14);
        assertTrue(y.s.equals("y"));
        assertTrue(tl.get(15) instanceof T_Then);//then
        assertTrue(tl.get(16) instanceof T_LeftCurlyBracket);//{
        assertTrue(tl.get(17) instanceof T_Identifier);//z
        z = (T_Identifier) tl.get(17);
        assertTrue(z.s.equals("z"));
        assertTrue(tl.get(18) instanceof T_RightCurlyBracket);//}
        assertTrue(tl.get(19) instanceof T_Else);//else
        assertTrue(tl.get(20) instanceof T_LeftCurlyBracket);//{
        assertTrue(tl.get(21) instanceof T_Integer);//0
        T_Integer zero = (T_Integer) tl.get(21);
        assertTrue(zero.n == 0);
        assertTrue(tl.get(22) instanceof T_RightCurlyBracket);//}
        assertTrue(tl.get(23) instanceof T_RightCurlyBracket);//}
       
    }
   
    public void testWhileDoDef() throws LexicalException, Task1Exception{
        input = "while do def";
        List<Token> tl = la.lex(input);
        assertTrue(tl.get(0) instanceof T_While);
        assertTrue(tl.get(1) instanceof T_Do);
        assertTrue(tl.get(2) instanceof T_Def);
    }
   
    public void testIdentifier() throws LexicalException, Task1Exception{
        input = "Apologies";
        assertTrue(la.lex(input).isEmpty());
        input = "this_Is_theAnswer";
        assertTrue(la.lex(input).size() == 1);
        assertTrue(la.lex(input).get(0) instanceof T_Identifier);
    }
   
    @Test
    public void testAssignmentOperator() throws LexicalException, Task1Exception{
        input = "x:=5";
        List<Token> tl = la.lex(input);
        assertTrue(tl.size() == 3);
        assertTrue(tl.get(0) instanceof T_Identifier);
        assertTrue(tl.get(1) instanceof T_Assign);
        assertTrue(tl.get(2) instanceof T_Integer);
    }
   
    private void printTokens(List<Token> lt){
        T_Identifier ti;
        for(Token t : lt){
            System.out.print(t.getClass().getName());
            if (t instanceof T_Identifier){
                ti = (T_Identifier) t;
                System.out.println(" " + ti.s);
            } else {
                System.out.println();
            }
        }
    }
 
}