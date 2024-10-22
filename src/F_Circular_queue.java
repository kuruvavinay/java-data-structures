public class F_Circular_queue {
    public static int[] a;
    public static int size;
    public static int front;
    public static int rear;
    F_Circular_queue(int s){
        a = new int[s];
        size = s;
        rear = -1;
        front = -1;
    }

    public boolean isEmpty(){
        return rear == front && rear == -1;
    }
    public boolean isFull(){
        return (rear+1) % size == front;
    }
    public void add(int val){
        if(isFull()){
            System.out.println("queue is full");
            return;
        }
        if(front == -1)
            front = 0;
        rear = (rear + 1) % size;
        a[rear] = val;
    }
    public int remove(){
        if(isEmpty()){
            System.out.println("queue is empty");
            return -1;
        }
        int res = a[front];
        if(rear == front){
            rear = front = -1;
        }else{
            front = (front + 1) % size;
        }
        return res;
    }
    public int peek(){
        if(isEmpty()){
            System.out.println("queue is empty");
            return -1;
        }
        return a[front];
    }
    public static void main(String[] args) {
        F_Circular_queue c = new F_Circular_queue(3);
        c.add(12);
        c.add(13);
        c.add(14);
        c.remove();
        c.remove();
        c.add(15);
        c.add(16);
        while (!c.isEmpty()){
            System.out.println(c.peek());
            c.remove();
        }
    }
}
