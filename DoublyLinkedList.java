import java.util.NoSuchElementException;

// node class
class Node<T>{
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T value, Node<T> next, Node<T> prev){
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}

public class DoublyLinkedList<T> {
    int size ;
    Node<T> head;
    Node<T> tail;

    public DoublyLinkedList(){
        size = 0;
        head = null;
        tail = null;
    }
    // insertion methods + array insertion
    public void add(T value){
        addLast(value);
    }
    public void add(T[] arr){
        addLast(arr);
    }
    public void addLast(T value){
        if(size==0)
            head = tail = new Node<T>(value,null,null);
        else{
            tail.next = new Node<T>(value,null,tail);
            tail = tail.next;
        }
        size++;
    }
    public void addLast(T[] arr){
        for(int i=0;i<arr.length;i++){
            addLast(arr[i]);
        }
    }
    public void addFirst(T value){
        if(size==0)
            head = tail = new Node<T>(value,null,null);
        else{
            head.prev = new Node<T>(value,head,null);
            head = head.prev;
        }
        size++;
        
    }
    public void addFirst(T[] arr){
        for(int i=arr.length-1;i>=0;i--){
            addFirst(arr[i]);
        }
    }
    public void addAt(int index,T value){
        if(index>size) throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        if(index==0){
            addFirst(value);
            return;
        } 
        if(index==size){
            addLast(value);
            return;
        } 
        Node<T> node;
        if(index<=size/2){
            node=head;
            for(int i=0;i<index;i++) node = node.next;
        }else{
            node=tail;
            for(int i=size-1;i>index;i--) node = node.prev;
        }
        node.prev.next = new Node<T>(value,node,node.prev);
        node.prev = node.prev.next;
        size++;
    }

    // deletion methods
    private void remove(Node<T> node){
        if(node.prev == null){
            removeFirst();
            return;
        }
        if(node.next == null){
            removeLast();
            return;
        }

        node.prev.next=node.next;
        node.next.prev=node.prev;

        node.value = null;
        node.next = null;
        node.prev = null;

        size--;
    }
    public void removeFirst(){
        if(isEmpty()) throw new IllegalArgumentException();
        head = head.next;
        size--;
        if(isEmpty()) tail = null;
        else head.prev = null;
    }
    public void removeLast(){
        if(isEmpty()) throw new IllegalArgumentException();
        tail = tail.prev;
        size--;
        if(isEmpty()) head = null;
        else tail.next = null;
    }
    public void removeAt(int index){
        if(index < 0 || index >= size) throw new IllegalArgumentException();
        if(index==0) {
            removeFirst();            
            return;
        }
        if(index==size-1){
            removeLast();
            return;
        }
        Node<T> node;
        if(index<size/2){
            node=head;
            for(int i=0;i<index;i++) node=node.next;
        }else{
            node=tail;
            for(int i=size-1;i>index;i--) node=node.prev;
        }
        remove(node);
    }

    // utility methods
    public void clear(){
        head = tail = null;
        size=0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    // access methods
    public T getFirst(){
        if(isEmpty()) throw new NoSuchElementException("List is empty");
        return head.value;
    }
    public T getLast(){
        if(isEmpty()) throw new NoSuchElementException("List is empty");
        return tail.value;
    }
    public T getAt(int index){
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        Node<T> node;
        if(index<=size/2){
            node=head;
            for(int i=0;i<index;i++) node = node.next;
        }else{
            node=tail;
            for(int i=size-1;i>index;i--) node = node.prev;
        }
        return node.value;
    }
    
    //update methods 
    public void setAt(int index,T value){
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        Node <T> node;
        if(index<=size/2){
            node=head;
            for(int i=0;i<index;i++) node = node.next;
        }else{
            node=tail;
            for(int i=size-1;i>index;i--) node = node.prev;

        }
        node.value=value;
        
    }

    @Override
    public String toString(){
        if(size == 0) return "[]";
        Node <T> node=head;
        StringBuilder str = new StringBuilder();
        str.append("[");
        str.append(node.value);
        node = node.next;
        for(int i=1;i<size;i++){
            str.append(", ");
            str.append(node.value);
            node = node.next;
        }
        str.append("]");
        return str.toString();
    } 
    public static void main(String[] args){ // TODO : make test cases 
        // creating the list 
        DoublyLinkedList<Integer> list =  new DoublyLinkedList<>();
        // adding the to list 
        list.add(1);
        list.add(2);
        list.add(3);
        list.addLast(4); // add or addLast are the same
        // add 0 to the first of the list 
        list.addFirst(0);

        System.out.println("List:\n"+list);
        // accessing the list
        System.out.println("First element: "+list.getFirst()); // get first element 
        System.out.println("Last element: "+list.getLast()); // get last element
        System.out.println("Element at index 2: "+list.getAt(2)); // get element at certain index

        System.out.println("List size: "+list.size()); // get list size 
        System.out.println("List is empty? "+list.isEmpty()); // check whether the list is empty or not (size == 0)

        //inserting in the list
        list.addAt(2,4); // addAt(index , value)
        System.out.println("List after adding 4 at index 2:\n"+ list);// print the list 

        // removing from the list
        list.removeFirst(); // remove first element
        System.out.println("List after removing first element:\n"+ list);
        list.removeLast(); // remove last element
        System.out.println("List after removing last element:\n"+ list);

        // adding to the list but with arrays 
        Integer[] arr1 = {6, 7, 8};
        list.addLast(arr1); // adding an array to the end of the list 
        System.out.println("List after adding {6, 7, 8} to the list:\n"+list);

        Integer[] arr2 = {-2, -1};
        list.addFirst(arr2); // adding an array to the beginning of the list 
        System.out.println("List after adding {6, 7, 8} to the list:\n"+list);
        
        // updating at certain index 
        list.setAt(3, 10); // update element at index 3 to 10
        System.out.println("List after setting element at index 3 to 10:\n"+list);

        // clearing the list 
        list.clear(); // clear the list from all the elment within O(1)
        System.out.println("List after clearing:\n"+list);
        
        System.out.println("List is empty? "+list.isEmpty());



    } 
    
}
