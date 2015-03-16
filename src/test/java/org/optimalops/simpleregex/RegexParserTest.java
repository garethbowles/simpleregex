package org.optimalops.simpleregex;

import com.google.common.base.Strings;

import junit.framework.TestCase;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RegexParserTest {

    @Test
    public void testLiteralMatch() throws Exception {
        assertTrue(RegexParser.match("abcdefgh", "abcdefgh"));
    }

    @Test
    public void testLiteralMismatch() throws Exception {
        assertFalse(RegexParser.match("abcdefgh", "badcfehg"));
    }

    @Test
    public void testExtraInitialStarMatch() throws Exception {
        assertTrue(RegexParser.match("*abcdefgh", "abcdefgh"));
    }

    @Test
    public void testInitialStarSubstituteMatch() throws Exception {
        assertTrue(RegexParser.match("*defgh", "abcdefgh"));
    }
    
    @Test
    public void testExtraMiddleStarMatch() throws Exception {
        assertTrue(RegexParser.match("abcd*efgh", "abcdefgh"));
    }

    @Test
    public void testMiddleStarSubstituteMatch() throws Exception {
        assertTrue(RegexParser.match("ab*fgh", "abcdefgh"));
    }

    @Test
    public void testExtraEndStarMatch() throws Exception {
        assertTrue(RegexParser.match("abcdefgh*", "abcdefgh"));
    }

    @Test
    public void testEndStarSubstituteMatch() throws Exception {
        assertTrue(RegexParser.match("ab*", "abcdefgh"));
    }

    @Test
    public void testInitialPlusMatch() throws Exception {
        assertTrue(RegexParser.match("+defgh", "abcdefgh"));
    }

    @Test
    public void testInitialPlusMismatch() throws Exception {
        assertFalse(RegexParser.match("+abcdefgh", "abcdefgh"));
    }

    @Test
    public void testMiddlePlusMatch() throws Exception {
        assertTrue(RegexParser.match("ab+gh", "abcdefgh"));
    }

    @Test
    public void testMiddlePlusMismatch() throws Exception {
        assertFalse(RegexParser.match("abcd+efgh", "abcdefgh"));
    }

    @Test
    public void testEndPlusMatch() throws Exception {
        assertTrue(RegexParser.match("abcdefg+", "abcdefgh"));
    }

    @Test
    public void testEndPlusMismatch() throws Exception {
        assertFalse(RegexParser.match("abcdefgh+", "abcdefgh"));
    }

    @Test
    public void testAllQMarksMatch() throws Exception {
        String matchQMarks = Strings.repeat("?", 8);
        assertTrue(RegexParser.match(matchQMarks, "abcdefgh"));
    }

    @Test
    public void testAllQMarksMismatch() throws Exception {
        String mismatchQMarks = Strings.repeat("?", 6);
        assertFalse(RegexParser.match(mismatchQMarks, "abcdefgh"));
    }

    @Test
    public void testElementumExample() throws Exception {
        assertTrue(RegexParser.match( "a?*e+h", "abcdefgh"));
    }
}
