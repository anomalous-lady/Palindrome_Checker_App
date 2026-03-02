import java.util.ArrayDeque;
import java.util.Deque;

public class Palindrome {
    public static void main(String[] args) {
        String original = "madam";

        // Step 1: Insert all characters into Deque
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < original.length(); i++) {
            deque.addLast(original.charAt(i));  // insert at rear
        }

        // Step 2: Compare front and rear until empty
        boolean isPalindrome = true;

        System.out.println("Comparing Front vs Rear in Deque:");
        System.out.println("----------------------------------");

        while (deque.size() > 1) {
            char front = deque.removeFirst();  // remove from front
            char rear  = deque.removeLast();   // remove from rear

            System.out.println("Front: " + front +
                    "  |  Rear: " + rear  +
                    "  |  Match: " + (front == rear));

            if (front != rear) {
                isPalindrome = false;
                break;
            }
        }

        // Step 3: Display result
        System.out.println("----------------------------------");
        if (isPalindrome) {
            System.out.println("\"" + original + "\" is a Palindrome");
        } else {
            System.out.println("\"" + original + "\" is not a Palindrome");
        }
    }
}