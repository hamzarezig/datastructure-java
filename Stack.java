class Node<T>{
    public T value;
    public Node<T> prev;
    public Node(T value,Node<T> prev){
        this.value = value;
        this.prev = prev;
    }
}

public class Stack<T>{
    private Node<T> top;
    private int size;
    
    public Stack(){
        size = 0;
        top = null;
    }
    public void push(T value){
        Node<T> node = new Node<T>(value,top) ;
        top = node;
        size++;
    }
    public T pop(){
        if(isEmpty()) throw new java.util.EmptyStackException();
        T value = top.value;
        top = top.prev;
        size--;
        return value;

    }
    public T peek(){
        if(isEmpty()) throw new java.util.EmptyStackException();
        return top.value;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(4);
        stack.pop();
        stack.push(3);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
