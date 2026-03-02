import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Palindrome {
    public static void main(String[] args) {
        String original = "madam";

        // Data Structures
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        // Step 1: Enqueue and Push all characters
        for (int i = 0; i < original.length(); i++) {
            queue.add(original.charAt(i));    // FIFO
            stack.push(original.charAt(i));   // LIFO
        }

        // Step 2: Compare dequeue vs pop
        boolean isPalindrome = true;

        System.out.println("Comparing Queue (FIFO) vs Stack (LIFO):");
        System.out.println("----------------------------------------");

        while (!queue.isEmpty() && !stack.isEmpty()) {
            char fromQueue = queue.poll();   // removes from front
            char fromStack = stack.pop();    // removes from top

            System.out.println("Queue(front): " + fromQueue +
                    "  |  Stack(top): " + fromStack +
                    "  |  Match: " + (fromQueue == fromStack));

            if (fromQueue != fromStack) {
                isPalindrome = false;
            }
        }

        // Step 3: Display result
        System.out.println("----------------------------------------");
        if (isPalindrome) {
            System.out.println("\"" + original + "\" is a Palindrome");
        } else {
            System.out.println("\"" + original + "\" is not a Palindrome");
        }
    }
}