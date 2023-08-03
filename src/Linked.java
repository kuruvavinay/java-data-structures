public class Linked{
    public static class Node{
        int val;
        Node next;
        Node(int v){
            this.val = v;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;

    public static int size = 1;
    public void addFirst(int v){
        Node newNode = new Node(v);
        if(head == null){
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
        size++;
    }
    public void addLast(int v){
        Node n = new Node(v);
        if(head == null){
            head = tail = n;
            return;
        }
//        Node last = head;
//        while(last.next != null){
//            last = last.next;
//        }
//        last.next = n;
//        tail = last;
        // another approach
        tail.next = n;
        tail = n;
        size++;
    }
    public void display(Node head){
        Node n = head;
        while(n != null){
            System.out.print(n.val + " -> ");
            n = n.next;
        }
        System.out.println("null");
    }

    public void addInMiddle(int index, int v){
        if(index == 0){
            addFirst(v);
            return;
        }
        Node temp = head;
        Node n = new Node(v);
        int i = 0;
        while(i < index - 1){
            temp = temp.next;
            i++;
        }
        n.next = temp.next;
        temp.next = n;
        size++;
    }
    public int removeFirst(){
        if(size == 0){
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }else if(size == 1){
            int v = head.val;
            head = tail = null;
            size--;
            return v;
        }
        int v = head.val;
        head = head.next;
        size--;
        return v;
    }
    public int removeLast(){
        if(size == 0){
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }else if(size == 1){
            int v = head.val;
            head = tail = null;
            size--;
            return v;
        }
        int i = 0;
        Node dummy = head;
        while(i < size - 2){
            dummy = dummy.next;
            i++;
        }
        int value = dummy.next.val;
        dummy.next = null;
        tail = dummy;
        size--;
        return value;
    }
    public int linear_search(int t){
        Node x = head;
        int i = 0;
        while(x != null){
            if(x.val == t){
                return i;
            }
            x = x.next;
            i++;
        }
        return -1;
    }
    public int recursive_search(Node head,int t){
        if(head == null)
            return -1;
        if(head.val == t)
            return 0;
        int ind = recursive_search(head.next,t);
        if(ind == -1)
            return -1;
        return ind+1;
    }
    public void reverse(Node head){
        Node prev = null;
        Node cur = tail = head;
        Node next;
        while(cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;
    }
    public int remove_nth_node_from_end(int n){
        Node x;
        int start = 1;
        int end = size - n; // important formula
        Node temp = head;
        if(size == n){ // to remove last element of the list
            x = head;
            x = x.next;
            return x.val;
        }
        while(start < end){
            temp = temp.next;
            start++;
        }
        x = temp.next;
        temp.next = temp.next.next;
        return x.val;
    }
    public Node midFinder(Node h){
        Node slow = h;
        Node fast = h;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public boolean palindrome(){
        // edge cases
        if(head == null || head.next == null){
            return true;
        }
        Node midNode = midFinder(head); // to find the mid-node for reversing the 2nd half list
        Node curr = midNode;
        Node prev = null;
        Node next;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev;
        Node left = head;
        while(right != null){
            if(left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }
    // floyd's cyclic finding algorithm
    public boolean cyclic_detection(){
        Node fast = head;
        Node slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
    public void cycle_removal(){
        Node slow = head;
        Node fast = head;
        boolean cycle = false;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                cycle = true;
                break;
            }
        }
        if(cycle == false)
            return;
        slow = head;
        Node prev = null;
        while (slow != fast){
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = null;
    }
    public Node midMerge(Node head){
        Node slow = head;
        Node fast = head.next;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    public Node mergeSort(Node head){
        if(head == null || head.next == null)
            return head;
        Node mid = midMerge(head);
        Node right = mid.next;
        mid.next = null;
        Node newleft = mergeSort(head);
        Node newright = mergeSort(right);
        return merge(newleft,newright);
    }
    public Node merge(Node left, Node right){
        Node mergeLl = new Node(-1);
        Node temp = mergeLl;
        while (left != null && right != null){
            if(left.val < right.val){
                temp.next = left;
                left = left.next;
                temp = temp.next;
            }else{
                temp.next = right;
                right = right.next;
                temp = temp.next;
            }
        }
        while(left != null){
            temp.next = left;
            left = left.next;
            temp = temp.next;
        }
        while(right != null){
            temp.next = right;
            right = right.next;
            temp = temp.next;
        }
        return mergeLl.next;
    }

    public Node reorder(Node head){
        // leetcode 143
        Node slow = head;
        Node fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Node shead= slow.next;
        Node prev =slow.next = null;
        Node next;
        while (shead != null){
            next = shead.next;
            shead.next = prev;
            prev = shead;
            shead = next;
        }
        shead = prev;
        Node d = head;
        Node first = new Node(-1);
        Node sec = first;
        while (shead != null){
            first.next = d;
            d = d.next;
            first = first.next;
            first.next = shead;
            shead = shead.next;
            first = first.next;
        }
        return sec.next;
    }


    public static void main(String[] args) {
        Linked l = new Linked();
        l.addFirst(12);
        l.addFirst(16);
        l.addFirst(19);
        l.addFirst(11);
        l.display(head);
        l.display(l.reorder(head));


    }
}
