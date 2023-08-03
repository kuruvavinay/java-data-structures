import java.util.ArrayList;
import java.util.Stack;

/*
stack is implemented in the LAST IN FIRST OUT OR FIRST IN LAST OUT
STACKS CAN BE CREATED IN FOUR WAYS
1. ARRAYLIST
2. ARRAY
3. LINKED LIST
4. USING FRAMEWORK
*/
public class Stacks {
    public static class Stack_using_arraylist{
        public static ArrayList<Integer> list= new ArrayList<Integer>();
        public int get_size(){
            return list.size();
        }
        public void push(int val){
            list.add(val);
        }
        public int pop(){
            if(isEmpty())
                return -1;
            int val = list.get(get_size() - 1);
            list.remove(get_size() - 1);
            return val;
        }
        public int peek(){
            if(isEmpty())
                return -1;
            return list.get(get_size() - 1);
        }
        public boolean isEmpty(){
            return list.size() == 0;
        }
    }
    public static class Stack_using_array{
        public static int[] array;
        public static final int DEFAULT = 10;
        Stack_using_array(){
            this.array = new int[DEFAULT];
        }
        Stack_using_array(int length){
            this.array = new int[length];
        }
        public static int size = -1;
        public boolean isFull(){
            return size >= array.length;
        }
        public boolean isEmpty(){
            return size == -1;
        }
        public void push(int val){
            if(isFull()) {
                System.out.println("stack is full");
                return;
            }
            array[++size] = val;
        }
        public int pop(){
            if(isEmpty()){
                System.out.println("stack is empty");
                return -1;
            }
            return array[size--];
        }
        public int peek(){
            if(isEmpty()){
                System.out.println("stack is empty");
                return -1;
            }
            return array[size];
        }
        public void display(){
            for(int i = 0; i <= size; i++){
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }
    public static class Stack_using_LinkedList{
        public static class Node{
            int val;
            Node next;
            Node(int v){
                this.val = v;
            }
        }
        public static Node head;
        public boolean isEmpty(){
            return head == null;
        }
        public void push(int val){
            Node n = new Node(val);
            if(isEmpty()){
                head = n;
                return;
            }
            n.next = head;
            head = n;
        }
        public int pop(){
            if(isEmpty()){
                System.out.println("the stack is empty");
                return -1;
            }
            int v = head.val;
            head = head.next;
            return v;
        }
        public int peek(){
            if(isEmpty()){
                System.out.println("the stack is empty");
                return -1;
            }
            Node x = head;
            while(x.next != null){
                x = x.next;
            }
            return x.val;
        }
        public void display(){
            Node x = head;
            while(x != null){
                System.out.print(x.val + " ");
                x = x.next;
            }
            System.out.println();
        }
    }
        public static void push_at_bottom(Stack<Integer> s,int i){
            if(s.isEmpty()){
                s.push(i);
                return;
            }
            int top = s.pop();
            push_at_bottom(s,i);
            s.push(top);
        }
        public static String reverse_String(String s){
        Stack<Character> c = new Stack<>();
        for(int i= 0; i < s.length(); i++){
            c.push(s.charAt(i));
        }
            System.out.println(c);
        StringBuilder rs = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            rs.append(c.pop());
        }
            return rs.toString();
        }
    public static void reverse_stack(Stack<Integer> s){
        if(s.isEmpty()){
            return;
        }
        int x = s.pop();
        reverse_stack(s);
        push_at_bottom(s,x);
    }
    public static boolean isValidParanthesis(String str){
        Stack<Character> s = new Stack<>();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '(' || ch == '{' || ch == '['){
                s.push(ch);
            }else{
                if(s.isEmpty()){
                    return false;
                }
                if((s.peek() == '(' && ch == ')') || (s.peek() == '{' && ch == '}') || (s.peek() == '[' && ch == ']')){
                    s.pop();
                }else {
                    return false;
                }
            }
        }
        if(s.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    public static void display(Stack<Integer> s){
        while(!s.isEmpty()){
            System.out.print(s.pop() + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println(isValidParanthesis("{}{}{}"));
    }
}
