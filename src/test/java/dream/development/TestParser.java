package dream.development;

import dream.development.controllers.Parser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestParser {

    private Parser parser;

    @Before
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parse1() {
        String input = "(a+(b*c))";
        String stringRPN = parser.infixToRPN(input);
        assertEquals("abc*+", stringRPN);
    }

    @Test
    public void parse2() {
        String input = "((a+b)*(z+x))";
        String stringRPN = parser.infixToRPN(input);
        assertEquals("ab+zx+*", stringRPN);
    }

    @Test
    public void parse3() {
        String input = "((a+t)*((b+(a+c))^(c+d)))";
        String stringRPN = parser.infixToRPN(input);
        assertEquals("at+bac++cd+^*", stringRPN);
    }

}
