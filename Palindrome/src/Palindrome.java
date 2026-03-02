package src;


 public class Palindrome {
    public static void main(String[] args) {
        String original = "madam";
        String reversed = "";

        // Loop to reverse the string
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed = reversed + original.charAt(i);
        }

        // Compare original and reversed
        if (original.equals(reversed)) {
            System.out.println("\"" + original + "\" is a Palindrome");
        } else {
            System.out.println("\"" + original + "\" is not a Palindrome");
        }
    }
}



