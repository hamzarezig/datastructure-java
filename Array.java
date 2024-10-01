package dsa;

import java.util.Iterator;

public class Array<T> implements Iterable<T>{ 
    private int length;
    private int capacity;
    private T array[];

    @SuppressWarnings("unchecked")
    public Array(){
        length = 0;
        capacity = 1;
        array = (T[]) new Object[capacity];
    }
    public Iterator<T> iterator(){
        return new ArrayIterator<T>(this);
    }

    public int length(){
        return length;
    }

    @SuppressWarnings("unchecked")
    public void add(T element){
        if(length ==  capacity){
            capacity *=2;
            T[] newArr = (T[]) new Object[capacity];

            for(int i=0;i<length;i++) 
                newArr[i] = array[i];

            array = newArr;
        }
        array[length++] = element;
    }

    @SuppressWarnings("unchecked")
    public void add(int index,T element){
        if(length ==  capacity){
            capacity *=2;
            T[] newArr = (T[]) new Object[capacity];

            for(int i=0;i<index;i++) 
                newArr[i] = array[i];
            for(int i=length-1;i>=index;i--)
                newArr[i+1] = array[i];

            array = newArr;
        }
        else
            for(int i=length-1;i>=index;i--)
                array[i+1] = array[i];
        array[index] = element;
        length++;
    }

    public T get(int index){
        // check bounds to avoid invalid access
        if(index >= length || index <= -1){
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }

        return array[index];
    }

    public void set(int index, T element ){
        // check bounds to avoid invalid access
        if(index >= length || index <= -1){
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }
        array[index] = element;

    }

    public void remove(int index){
        // check bounds to avoid invalid access
        if(index >= length || index <= -1){
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }

        for(int i=index;i<length-1;i++){
            array[i]=array[i+1];
        }
        length--;

    }

    @SuppressWarnings("unchecked")
    public void clear(){
        length = 0;
        capacity = 1;
        array = (T[]) new Object[capacity];

    }

    public boolean contains(T element){
        for(int i=0;i<length;i++){
            if(array[i].equals(element)) return true;
        }
        return false;
    }

    public int indexOf(T element){
        for(int i=0;i<length;i++){
            if(array[i].equals(element)) return i;
        }
        return -1;
    }
    public boolean isEmpty(){
        return length==0;
    }

    @SuppressWarnings("unchecked")
    public void shrink(){
        if(length == capacity)
            return;
        T[] newArr = (T[]) new Object[length];

        for(int i=0;i<length;i++)
            newArr[i] = array[i];
        array = newArr;
    }

    @Override
    public String toString(){
        if(length == 0) return "[]";
        String output="[";
        
        for(int i=0;i<length;i++){
            if(i!=0){
                output+=", ";
            }
            output+=""+array[i];

        }
        output+="]";
        return output;
    }


    public static void main(String[] args){
        // creating array
        Array<Integer> arr = new Array<>();
        // adding to the array
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);

        // replaces (index, value)
        arr.set(1,3);
        arr.set(3,7);

        // add with index (index,value)
        arr.add(2,3);
        arr.add(3,3);

        // shrinks the capacity of the array to match the length
        arr.shrink();

        // getting current length
        int len = arr.length();
        // looping through the array
        for(int i=0;i<len;i++){
            System.out.println(arr.get(i));
        }
        // loop with for each 
        for(Integer element : arr){
            System.out.println(element);
        }
        // show it as string 
        System.out.println(arr.toString());

        // check if the array contains a certain element and returns a boolean
        System.out.println(arr.contains(1));
        System.out.println(arr.contains(2));
        
        // return the index of the element or -1 if it doesn't exist 
        System.out.println(arr.indexOf(1));
        System.out.println(arr.indexOf(2));

        // testing out of bounds
        // System.out.println(arr.get(100)); 
        // arr.remove(100);
        // arr.set(100,1337);
        
        // checks if the array is empty : false
        System.out.println(arr.isEmpty());

        // removing from the array
        arr.remove(0);
        arr.remove(0);
        arr.remove(0);
        arr.remove(0);

        // or clear it all with clear()
        arr.clear();

        // checks if the array is empty : true
        System.out.println(arr.isEmpty());

    }
    
}
// the iterator class 
class ArrayIterator<T> implements Iterator<T>{
    private int index;
    private int length;
    private Array<T> array;

    public ArrayIterator(Array<T> array){
        index = 0;
        length = array.length();
        this.array = array;

    }
    public boolean hasNext(){
        return index != length;

    }
    public T next(){
        T data = array.get(index);
        index++;
        return data;
    }
    public void remove(){
        throw new UnsupportedOperationException();
    }


}
