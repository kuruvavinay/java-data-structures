

public class E_Queues {
    public static class Queue_with_linkedlist {
        public class Node {
            int val;
            Node next;

            Node(int v) {
                this.val = v;
                this.next = null;
            }
        }

        public static Node head = null;
        public static Node tail = null;
        public boolean isEmpty() {
            return head == null && head == tail;
        }
        public void add(int val) {
            Node n = new Node(val);
            if (head == null) {
                head = tail = n;
                return;
            }
            tail.next = n;
            tail = n;
        }
        public int remove() {
            int val = head.val;
            if(isEmpty()){
                System.out.println("The Queue is empty");
                return -1;
            }
            if(head == tail)
                tail = head = null;
            else
                head = head.next;
            return val;
        }
        public int peek(){
            if(isEmpty()){
                System.out.println("The queue is empty");
                return -1;
            }
            return head.val;
        }
        public void display(){
            Node n = head;
            while (n != null){
                System.out.println(n.val);
                n = n.next;
            }
        }
    }
    public static int[] a;
    public static int size;
    public static int rear = -1;
    E_Queues(int s){
        a = new int[s];
        size = s;
        rear = -1;
    }

    public boolean isEmpty(){
        return rear == -1;
    }
    public boolean isFull(){
        return size - 1 == rear;
    }
    public void add(int val){
        if(isFull()){
            System.out.println("queue is full");
            return;
        }
        a[++rear] = val;
    }
    public int remove(){
        if(isEmpty()){
            System.out.println("queue is empty");
            return -1;
        }
        int front = a[0];
        for (int i = 0; i < rear; i++){
            a[i] = a[i+1];
        }
        rear--;
        return front;
    }
    public int peek(){
        if(isEmpty()){
            System.out.println("queue is empty");
            return -1;
        }
        return a[0];
    }
    public void display(){
        for(int i = 0; i <= rear; i++){
            System.out.println(a[i]);
        }
    }
    public static void main(String[] args) {
        Queue_with_linkedlist q = new Queue_with_linkedlist();
        q.add(12);
        q.add(13);
        q.add(14);
        q.display();
    }
}
