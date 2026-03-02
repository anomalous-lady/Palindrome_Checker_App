public class Palindrome {

    // Recursive method to check palindrome
    static boolean checkPalindrome(String s, int left, int right) {

        // Base Condition 1: Single char or empty - always palindrome
        if (left >= right) {
            System.out.println("  Base condition reached → left(" + left +
                    ") >= right(" + right + ") → return true");
            return true;
        }

        // Compare current outer characters
        System.out.println("  Comparing s[" + left + "]=" + s.charAt(left) +
                " with s[" + right + "]=" + s.charAt(right));

        // Mismatch found - not a palindrome
        if (s.charAt(left) != s.charAt(right)) {
            System.out.println("  Mismatch! → return false");
            return false;
        }

        // Recursive call: move inward
        System.out.println("  Match ✅ → recurse inward");
        return checkPalindrome(s, left + 1, right - 1);
    }

    public static void main(String[] args) {
        String original = "madam";

        System.out.println("Checking: \"" + original + "\"");
        System.out.println("String indices:");

        // Print indexed string
        for (int i = 0; i < original.length(); i++) {
            System.out.print("  [" + i + "]=" + original.charAt(i));
        }

        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("Recursive Call Trace:");
        System.out.println("-------------------------------------");

        boolean result = checkPalindrome(original, 0, original.length() - 1);

        System.out.println("-------------------------------------");
        if (result) {
            System.out.println("\"" + original + "\" is a Palindrome");
        } else {
            System.out.println("\"" + original + "\" is not a Palindrome");
        }
    }
}
