package dsa;

public class Node<T>{
    public T value;
    public Node<T> next;
    public Node<T> prev;

    // doubly node 
    public Node(T value, Node<T> next, Node<T> prev){
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
    // singly node
    public Node(T value,Node<T> prev){
        this.value = value;
        this.prev = prev;
    }
}
