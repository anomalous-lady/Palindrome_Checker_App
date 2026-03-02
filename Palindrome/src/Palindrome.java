import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// ─────────────────────────────────────────
//  PalindromeStrategy.java  – Interface
// ─────────────────────────────────────────
interface PalindromeStrategy {
    boolean check(String input);
    String strategyName();
}


// ─────────────────────────────────────────
//  TwoPointerStrategy.java
// ─────────────────────────────────────────
class TwoPointerStrategy implements PalindromeStrategy {

    @Override
    public boolean check(String input) {
        char[] chars = input.toCharArray();
        int left     = 0;
        int right    = chars.length - 1;

        while (left < right) {
            if (chars[left] != chars[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    @Override
    public String strategyName() {
        return "Two Pointer (char[])";
    }
}


// ─────────────────────────────────────────
//  StackStrategy.java
// ─────────────────────────────────────────
class StackStrategy implements PalindromeStrategy {

    @Override
    public boolean check(String input) {
        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        for (char c : input.toCharArray()) {
            if (c != stack.pop()) return false;
        }
        return true;
    }

    @Override
    public String strategyName() {
        return "Stack (LIFO)";
    }
}


// ─────────────────────────────────────────
//  DequeStrategy.java
// ─────────────────────────────────────────
class DequeStrategy implements PalindromeStrategy {

    @Override
    public boolean check(String input) {
        Deque<Character> deque = new ArrayDeque<>();

        for (char c : input.toCharArray()) {
            deque.addLast(c);
        }

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) return false;
        }
        return true;
    }

    @Override
    public String strategyName() {
        return "Deque (Front vs Rear)";
    }
}


// ─────────────────────────────────────────
//  RecursionStrategy.java
// ─────────────────────────────────────────
class RecursionStrategy implements PalindromeStrategy {

    private boolean recurse(char[] chars, int left, int right) {
        if (left >= right)                    return true;
        if (chars[left] != chars[right])      return false;
        return recurse(chars, left + 1, right - 1);
    }

    @Override
    public boolean check(String input) {
        char[] chars = input.toCharArray();
        return recurse(chars, 0, chars.length - 1);
    }

    @Override
    public String strategyName() {
        return "Recursion (Call Stack)";
    }
}


// ─────────────────────────────────────────
//  PalindromeChecker.java  – Context Class
// ─────────────────────────────────────────
class PalindromeContext {

    private PalindromeStrategy strategy;    // injected strategy

    // ── Constructor injection ────────────
    public PalindromeContext(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    // ── Runtime strategy swap ────────────
    public void setStrategy(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    // ── Normalize input ──────────────────
    private String normalize(String s) {
        return s.toLowerCase()
                .replaceAll("[^a-z0-9]", "");
    }

    // ── Execute current strategy ─────────
    public void execute(String input) {
        String normalized = normalize(input);
        boolean result    = strategy.check(normalized);

        System.out.println("  Strategy   : " + strategy.strategyName());
        System.out.println("  Original   : \"" + input       + "\"");
        System.out.println("  Normalized : \"" + normalized  + "\"");
        System.out.println("  Result     :  " +
                (result ? "✅ Palindrome" : "❌ Not a Palindrome"));
        System.out.println("  -----------------------------------------");
    }
}


// ─────────────────────────────────────────
//  Main.java  – Driver Class
// ─────────────────────────────────────────
public class Palindrome {

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("   Strategy Pattern Palindrome Checker");
        System.out.println("=========================================");

        // ── All available strategies ─────
        PalindromeStrategy[] strategies = {
                new TwoPointerStrategy(),
                new StackStrategy(),
                new DequeStrategy(),
                new RecursionStrategy()
        };

        // ── Test inputs ──────────────────
        String[] testCases = {
                "madam",
                "A man a plan a canal Panama",
                "Race a car"
        };

        // ── Run each test on all strategies
        for (String input : testCases) {
            System.out.println("\n┌────────────────────────────────────────");
            System.out.println("│ Input: \"" + input + "\"");
            System.out.println("└────────────────────────────────────────");

            PalindromeContext context = new PalindromeContext(strategies[0]);

            for (PalindromeStrategy strategy : strategies) {
                context.setStrategy(strategy);   // swap at runtime
                context.execute(input);
            }
        }

        // ── Runtime strategy injection demo
        System.out.println("\n=========================================");
        System.out.println("   Runtime Strategy Injection Demo");
        System.out.println("=========================================\n");

        PalindromeContext dynamic = new PalindromeContext(new StackStrategy());
        dynamic.execute("Was it a car or a cat I saw");

        dynamic.setStrategy(new DequeStrategy());       // swap strategy
        dynamic.execute("Was it a car or a cat I saw");

        dynamic.setStrategy(new TwoPointerStrategy());  // swap again
        dynamic.execute("Was it a car or a cat I saw");
    }
}