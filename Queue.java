class Node<T>{
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T value, Node<T> next,Node<T> prev){
        this.value =  value;
        this.next =  next;
        this.prev =  prev;
    }
}
public class Queue<T>{
    private Node<T> head;
    private Node<T> tail;

    public Queue(){
        this.head = null;
        this.tail = null; 
    }
    // insertion 
    public void enqueue(T value){
        Node<T> node = new Node<T>(value,head,null);
        if(head != null) head.prev = node;
        else tail = node;
        head = node;
    }
    public T dequeue(){
        if(tail == null) throw new java.util.NoSuchElementException();
        T value = tail.value;
        tail = tail.prev ;

        if(tail != null) tail.next = null;
        else head = null;

        return value;
    }
    public T peek(){
        if(tail == null) throw new java.util.NoSuchElementException();
        return tail.value;
    }
    public boolean isEmpty(){
        return head == null && tail == null;
    }
    public static void main(String[] args){
        System.out.println("Queue implemantation");

        Queue<Integer> queue = new Queue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.dequeue()); // Should print 1
        System.out.println(queue.peek()); // Should print 2
        System.out.println(queue.isEmpty()); // Should print false
    }



}
