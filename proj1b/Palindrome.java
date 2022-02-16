public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast((Character) word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            for (int i = 0; i < deque.size() / 2; i++) {
                if (deque.get(i) != deque.get(deque.size() - 1 - i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            for (int i = 0; i < deque.size() / 2; i++) {
                if (!cc.equalChars(deque.get(i), deque.get(deque.size() - 1 - i))) {
                    return false;
                }
            }
        }
        return true;
    }

}
