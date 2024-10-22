public class C_Double_Linked_List {
    public static class Node{
        Node prev;
        int val;
        Node next;
        Node(int v){
            this.val = v;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;
    public void addFirst(int data){
        Node n = new Node(data);
        if(head == null){
            head = tail = n;
            return;
        }
        n.next = head;
        head.prev = n;
        head = n;
    }
    public void display(){
        Node n = head;
        while(n != null){
            System.out.print(n.val + " <=> ");
            n = n.next;
        }
        System.out.println("null");
    }
    public void addLast(int data){
        Node n = new Node(data);
        if(head == null){
            head = tail = n;
            return;
        }
        tail.next = n;
        n.prev = tail;
        tail = n;
    }
    public int removeFirst(){
        if(head == null){
            System.out.println("the linkedlist is empty");
            return Integer.MIN_VALUE;
        }
        int data = head.val;
        head = head.next;
        head.prev = null;
        return data;
    }
    public int removeLast(){
        int x = 0;
        if(head == null){
            System.out.println("empty");
            return Integer.MIN_VALUE;
        }
        if(head == tail){
            x = head.val;
            head = tail = null;
            return x;
        }else{
            Node curr = tail;
            x = curr.val;
            tail = curr.prev;
            tail.next = null;
            curr.prev = null;
        }
        return x;
    }
    public void reverse(){
        Node curr = head;
        Node prev = null;
        Node next;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        head = prev;
    }
    public static void main(String[] args) {
        C_Double_Linked_List d = new C_Double_Linked_List();
        d.addFirst(1);
        d.addLast(2);
        d.addFirst(3);
        d.addFirst(11);
        d.display();
        d.removeLast();
        d.display();
    }
}
