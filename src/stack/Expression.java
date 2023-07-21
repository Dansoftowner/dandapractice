package stack;

/**
 *
 */
public class Expression {

    /**
     * Closing - opening tags
     */
    private static final java.util.Map<Character, Character> TAGS =
            java.util.Map.of(
                    ')', '(',
                    ']', '[',
                    '}', '{',
                    '>', '<'
            );


    private String exp;

    public Expression(String exp) {
        this.exp = exp;
    }

    public boolean isBalanced() {
        // [ABC]
        MyStack<Character> openingTags = new MyStack<>();
        for (char c : exp.toCharArray()) {
            if (isOpeningTag(c))
                openingTags.push(c);
            if (isClosingTag(c)) {
                if (TAGS.get(c) != openingTags.peek())
                    return false;
                openingTags.pop();
            }
        }
        return openingTags.isEmpty();
    }

    private boolean isOpeningTag(char ch) {
        return TAGS.containsValue(ch);
    }

    private boolean isClosingTag(char ch) {
        return TAGS.containsKey(ch);
    }
}
