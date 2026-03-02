package src;


public class Palindrome {
    public static void main(String[] args) {
        String original = "madam";

        // Convert String to character array
        char[] chars = original.toCharArray();

        // Two-pointer approach
        int left = 0;
        int right = chars.length - 1;
        boolean isPalindrome = true;

        while (left < right) {
            if (chars[left] != chars[right]) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }

        // Display result
        if (isPalindrome) {
            System.out.println("\"" + original + "\" is a Palindrome");
        } else {
            System.out.println("\"" + original + "\" is not a Palindrome");
        }
    }
}



