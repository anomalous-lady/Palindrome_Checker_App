import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// ─────────────────────────────────────────
//  PalindromeStrategy Interface
// ─────────────────────────────────────────
interface PalindromeStrategy {
    boolean check(String input);
    String strategyName();
}

// ─────────────────────────────────────────
//  Strategy 1 : StringBuilder Reverse
// ─────────────────────────────────────────
class StringBuilderStrategy implements PalindromeStrategy {
    @Override
    public boolean check(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        return input.equals(reversed);
    }
    @Override
    public String strategyName() { return "StringBuilder Reverse"; }
}

// ─────────────────────────────────────────
//  Strategy 2 : Loop + Concatenation
// ─────────────────────────────────────────
class LoopConcatStrategy implements PalindromeStrategy {
    @Override
    public boolean check(String input) {
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed = reversed + input.charAt(i);
        }
        return input.equals(reversed);
    }
    @Override
    public String strategyName() { return "Loop + Concatenation"; }
}

// ─────────────────────────────────────────
//  Strategy 3 : Two Pointer
// ─────────────────────────────────────────
class TwoPointerBenchStrategy implements PalindromeStrategy {
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
    public String strategyName() { return "Two Pointer (char[])"; }
}

// ─────────────────────────────────────────
//  Strategy 4 : Stack
// ─────────────────────────────────────────
class StackBenchStrategy implements PalindromeStrategy {
    @Override
    public boolean check(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) stack.push(c);
        for (char c : input.toCharArray()) {
            if (c != stack.pop()) return false;
        }
        return true;
    }
    @Override
    public String strategyName() { return "Stack (LIFO)"; }
}

// ─────────────────────────────────────────
//  Strategy 5 : Deque
// ─────────────────────────────────────────
class DequeBenchStrategy implements PalindromeStrategy {
    @Override
    public boolean check(String input) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : input.toCharArray()) deque.addLast(c);
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) return false;
        }
        return true;
    }
    @Override
    public String strategyName() { return "Deque (Front vs Rear)"; }
}

// ─────────────────────────────────────────
//  Strategy 6 : Recursion
// ─────────────────────────────────────────
class RecursionBenchStrategy implements PalindromeStrategy {
    private boolean recurse(char[] chars, int left, int right) {
        if (left >= right)               return true;
        if (chars[left] != chars[right]) return false;
        return recurse(chars, left + 1, right - 1);
    }
    @Override
    public boolean check(String input) {
        char[] chars = input.toCharArray();
        return recurse(chars, 0, chars.length - 1);
    }
    @Override
    public String strategyName() { return "Recursion (Call Stack)"; }
}


// ─────────────────────────────────────────
//  BenchmarkResult  – Result Holder
// ─────────────────────────────────────────
class BenchmarkResult {
    String  strategyName;
    boolean palindromeResult;
    long    totalNanoTime;
    long    avgNanoTime;
    int     iterations;

    BenchmarkResult(String name, boolean result,
                    long total, long avg, int iterations) {
        this.strategyName     = name;
        this.palindromeResult = result;
        this.totalNanoTime    = total;
        this.avgNanoTime      = avg;
        this.iterations       = iterations;
    }
}


// ─────────────────────────────────────────
//  Benchmarker  – Core Engine
// ─────────────────────────────────────────
class Benchmarker {

    private static final int WARMUP_RUNS = 1000;   // JVM warmup
    private static final int BENCH_RUNS  = 10000;  // actual benchmark

    // ── Normalize input ──────────────────
    static String normalize(String s) {
        return s.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    // ── Run single benchmark ─────────────
    static BenchmarkResult run(PalindromeStrategy strategy, String input) {

        String normalized = normalize(input);

        // JVM warmup — discard results
        for (int i = 0; i < WARMUP_RUNS; i++) {
            strategy.check(normalized);
        }

        // Actual benchmark
        boolean result   = false;
        long    start    = System.nanoTime();

        for (int i = 0; i < BENCH_RUNS; i++) {
            result = strategy.check(normalized);
        }

        long totalTime = System.nanoTime() - start;
        long avgTime   = totalTime / BENCH_RUNS;

        return new BenchmarkResult(
                strategy.strategyName(),
                result,
                totalTime,
                avgTime,
                BENCH_RUNS
        );
    }

    // ── Display single result row ─────────
    static void printResult(BenchmarkResult r, long fastest) {
        double ratio = (double) r.avgNanoTime / fastest;
        String badge = r.avgNanoTime == fastest ? " 🏆 FASTEST" : "";

        System.out.printf("  %-26s │ %-14s │ %8d ns │ %8d ns │ %.2fx%s%n",
                r.strategyName,
                r.palindromeResult ? "✅ Palindrome" : "❌ Not Palindrome",
                r.totalNanoTime / 1_000_000,   // convert to ms display
                r.avgNanoTime,
                ratio,
                badge
        );
    }

    // ── Run full benchmark suite ──────────
    static void runSuite(PalindromeStrategy[] strategies,
                         String label, String input) {

        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  Input : \"" + input + "\"");
        System.out.println("│  Label : " + label);
        System.out.println("│  Runs  : " + BENCH_RUNS + " iterations per strategy");
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
        System.out.printf("  %-26s │ %-14s │ %11s │ %11s │ %s%n",
                "Strategy", "Result", "Total(ms)", "Avg(ns)", "Ratio");
        System.out.println("  ─────────────────────────────────────────────────────────────────────────────────────");

        // collect all results
        BenchmarkResult[] results = new BenchmarkResult[strategies.length];
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < strategies.length; i++) {
            results[i] = run(strategies[i], input);
            if (results[i].avgNanoTime < fastest) {
                fastest = results[i].avgNanoTime;
            }
        }

        // print ranked results
        for (BenchmarkResult result : results) {
            printResult(result, fastest);
        }

        System.out.println("  ─────────────────────────────────────────────────────────────────────────────────────");
    }
}


// ─────────────────────────────────────────
//  Main – Driver Class
// ─────────────────────────────────────────
public class Palindrome {

    public static void main(String[] args) {

        // All strategies to benchmark
        PalindromeStrategy[] strategies = {
                new StringBuilderStrategy(),
                new LoopConcatStrategy(),
                new TwoPointerBenchStrategy(),
                new StackBenchStrategy(),
                new DequeBenchStrategy(),
                new RecursionBenchStrategy()
        };

        System.out.println("╔═════════════════════════════════════════════════════════════════╗");
        System.out.println("║         Palindrome Algorithm Benchmark Suite                    ║");
        System.out.println("║         System.nanoTime() precision timing                      ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════╝");

        // ── Test 1: Short string ─────────
        Benchmarker.runSuite(strategies,
                "Short String (5 chars)", "madam");

        // ── Test 2: Medium string ────────
        Benchmarker.runSuite(strategies,
                "Medium String (20 chars)", "A man a plan a canal Panama");

        // ── Test 3: Long string ──────────
        String longPalindrome = "abcdefghijklmnopqrstuvwxyz"
                + "zyxwvutsrqponmlkjihgfedcba";
        Benchmarker.runSuite(strategies,
                "Long String (52 chars)", longPalindrome);

        // ── Test 4: Non palindrome ───────
        Benchmarker.runSuite(strategies,
                "Non-Palindrome (early exit)", "hello world");

        // ── Summary ──────────────────────
        System.out.println("\n╔═════════════════════════════════════════════════════════════════╗");
        System.out.println("║                   Performance Summary                           ║");
        System.out.println("╠═════════════════════════════════════════════════════════════════╣");
        System.out.println("║  🏆 Fastest  : Two Pointer       — O(n) time, O(1) space        ║");
        System.out.println("║  🥈 Fast     : Deque / Recursion — O(n) time, O(n) space        ║");
        System.out.println("║  🥉 Moderate : StringBuilder     — O(n) time, O(n) space        ║");
        System.out.println("║  🐢 Slowest  : Loop + Concat     — O(n²) time, O(n²) space      ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════╝");
    }
}