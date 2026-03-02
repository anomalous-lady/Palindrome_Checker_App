public class Palindrome{

    // Step 1: Normalize the string
    static String normalize(String s) {
        String lower    = s.toLowerCase();               // convert to lowercase
        String cleaned  = lower.replaceAll("[^a-z0-9]", ""); // remove non-alphanumeric
        return cleaned;
    }

    // Step 2: Two-pointer palindrome check
    static boolean checkPalindrome(String s) {
        int left  = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {

        // Test cases
        String[] testCases = {
                "madam",
                "Madam",
                "A man a plan a canal Panama",
                "Race a car",
                "Was it a car or a cat I saw",
                "Hello World"
        };

        System.out.println("=========================================");
        System.out.println("  Case-Insensitive Palindrome Checker");
        System.out.println("=========================================");

        for (String original : testCases) {

            String normalized = normalize(original);

            System.out.println("\nOriginal   : \"" + original   + "\"");
            System.out.println("Normalized : \"" + normalized  + "\"");

            boolean result = checkPalindrome(normalized);

            System.out.println("Result     :  "  +
                    (result
                            ? "✅ Palindrome"
                            : "❌ Not a Palindrome"));
            System.out.println("-----------------------------------------");
        }
    }
}