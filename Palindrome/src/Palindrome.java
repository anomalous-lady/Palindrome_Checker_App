public class Palindrome {

    // Node class for Singly Linked List
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    // Step 1: Convert String to Linked List
    static Node buildList(String s) {
        Node head = null;
        Node tail = null;

        for (int i = 0; i < s.length(); i++) {
            Node newNode = new Node(s.charAt(i));
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        return head;
    }

    // Step 2: Find middle using Fast & Slow pointer
    static Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Step 3: Reverse second half in-place
    static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node nextNode = curr.next;
            curr.next    = prev;
            prev         = curr;
            curr         = nextNode;
        }
        return prev;
    }

    // Step 4: Compare both halves
    static boolean isPalindrome(Node head) {
        Node middle      = findMiddle(head);
        Node secondHalf  = reverseList(middle);
        Node firstHalf   = head;
        Node tempSecond  = secondHalf;

        boolean result = true;

        System.out.println("Comparing First Half vs Second Half:");
        System.out.println("-------------------------------------");

        while (tempSecond != null) {
            System.out.println("First: " + firstHalf.data +
                    "  |  Second: " + tempSecond.data +
                    "  |  Match: " + (firstHalf.data == tempSecond.data));

            if (firstHalf.data != tempSecond.data) {
                result = false;
                break;
            }
            firstHalf  = firstHalf.next;
            tempSecond = tempSecond.next;
        }

        // Restore list (optional best practice)
        reverseList(secondHalf);
        return result;
    }

    // Utility: Print linked list
    static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data);
            if (curr.next != null) System.out.print(" → ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String original = "madam";

        // Build linked list
        Node head = buildList(original);

        System.out.print("Linked List: ");
        printList(head);
        System.out.println("-------------------------------------");

        // Check palindrome
        if (isPalindrome(head)) {
            System.out.println("-------------------------------------");
            System.out.println("\"" + original + "\" is a Palindrome");
        } else {
            System.out.println("-------------------------------------");
            System.out.println("\"" + original + "\" is not a Palindrome");
        }
    }
}