import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertEquals(true, palindrome.isPalindrome("a"));
        assertEquals(true, palindrome.isPalindrome(""));
        assertEquals(false, palindrome.isPalindrome("Thomas"));
//        assertEquals(false, palindrome.isPalindrome("abcd dcba"));
        assertEquals(true, palindrome.isPalindrome("noon"));

        assertEquals(true, palindrome.isPalindrome("a", new OffByOne()));
        assertEquals(false, palindrome.isPalindrome("append", new OffByOne()));
        assertEquals(true, palindrome.isPalindrome("acde fedb", new OffByOne()));
        assertEquals(true, palindrome.isPalindrome("flake", new OffByOne()));
    }
}
