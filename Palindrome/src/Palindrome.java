// ─────────────────────────────────────────
//  PalindromeChecker.java  – Reusable Class
// ─────────────────────────────────────────
class PalindromeChecker {

    private String original;     // encapsulated field
    private String normalized;   // encapsulated field

    // ── Constructor ──────────────────────
    public PalindromeChecker(String input) {
        this.original   = input;
        this.normalized = normalize(input);
    }

    // ── Private helper: normalize ────────
    private String normalize(String s) {
        return s.toLowerCase()
                .replaceAll("[^a-z0-9]", "");
    }

    // ── Private helper: two-pointer ──────
    private boolean twoPointerCheck(String s) {
        char[] chars = s.toCharArray();
        int left     = 0;
        int right    = chars.length - 1;

        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // ── Public API: checkPalindrome ──────
    public boolean checkPalindrome() {
        return twoPointerCheck(normalized);
    }

    // ── Public API: getOriginal ──────────
    public String getOriginal() {
        return original;
    }

    // ── Public API: getNormalized ────────
    public String getNormalized() {
        return normalized;
    }

    // ── Public API: printResult ──────────
    public void printResult() {
        System.out.println("Original   : \"" + original   + "\"");
        System.out.println("Normalized : \"" + normalized + "\"");
        System.out.println("Result     :  "  +
                (checkPalindrome()
                        ? "✅ Palindrome"
                        : "❌ Not a Palindrome"));
        System.out.println("-----------------------------------------");
    }
}


// ─────────────────────────────────────────
//  Main.java  – Driver Class
// ─────────────────────────────────────────
public class Palindrome {

    public static void main(String[] args) {

        String[] testCases = {
                "madam",
                "Madam",
                "A man a plan a canal Panama",
                "Race a car",
                "Was it a car or a cat I saw",
                "No lemon no melon",
                "Hello World"
        };

        System.out.println("=========================================");
        System.out.println("   OOP-Based Palindrome Checker");
        System.out.println("=========================================\n");

        for (String input : testCases) {
            PalindromeChecker checker = new PalindromeChecker(input);
            checker.printResult();
        }
    }
}