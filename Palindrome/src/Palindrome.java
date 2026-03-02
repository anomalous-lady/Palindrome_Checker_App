import java.util.Stack;

public class Palindrome {
    public static void main(String[] args) {
        String original = "madam";

        // Step 1: Push all characters into Stack
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < original.length(); i++) {
            stack.push(original.charAt(i));
        }

        // Step 2: Pop characters and build reversed string
        String reversed = "";

        while (!stack.isEmpty()) {
            reversed = reversed + stack.pop();
        }

        // Step 3: Compare and display result
        if (original.equals(reversed)) {
            System.out.println("\"" + original + "\" is a Palindrome");
        } else {
            System.out.println("\"" + original + "\" is not a Palindrome");
        }
    }
}