/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.util;

/**
 *
 * @author PC
 * @param <E>
 */
public class EndiabladaLinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;
    private int tam = 0;

    private static class Node<E> {

        E data;
        Node tail;
        Node next;

        public Node(E data) {
            this.data = data;
            this.tail = next;
            this.next = tail;
        }

        public Node(E data, Node next, Node tail) {
            this.data = data;
            this.tail = next;
            this.next = tail;
        }

        public Node() {
        }

    }

    @Override
    public boolean addFirst(E data) {

        if (data == null) {
            return false;
        }

        Node<E> noden = new Node(data);
        if (isEmpty()) {
            first = noden;
            last = noden;
            tam++;
            return true;
        }

        noden.next = first;
        first.tail = noden;
        if (tam == 1) {
            first.next = noden;
            noden.tail = first;
            last = first;
            first = noden;
            tam++;
            return true;
        }

        last.next = noden;
        noden.tail = last;
        first = noden;
        tam++;
        return true;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return first.data;
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.data;
    }

    @Override
    public int indexOf(E e) {
        if (isEmpty()) {
            return -1;
        }
        Node<E> n = first;
        for (int i = 0; i < size(); i++) {
            if (n.data.equals(e)) {
                return i;
            }
            n = n.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return tam;
    }

    @Override
    public boolean removeLast() {
        if (isEmpty()) {
            return false;
        }
        first.tail = last.tail;
        last.tail.next = first;
        last = last.tail;
        tam--;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        last.next = first.next;
        first.next.tail = last;
        first = first.next;
        tam--;
        return true;
    }

    @Override
    public boolean insert(int index, E e) {
        Node<E> tmp = new Node(e);
        if(isEmpty())   return false;
        if(index<0 || index > size()) return false;
        if(index == size()){
            addLast(e);
        }else if(index == 0) {
            addFirst(e);
        }else{
            for(int i = 1; i< size(); i++){
                if(index == i){
                    Node ant = getNodo(get(i-1));
                    tmp.next = ant.next;
                    ant.next = tmp;
                    tmp.tail = tmp;
                    
                    
                    
                    tam++;
                }
            }
            
        }
        return false;
        
        
    }

    @Override
    public boolean set(int index, E e) {
        if (isEmpty()) {
            return false;
        }
        
        if (index < 0 || index >= size()) {
            return false;
        }
        Node<E> rem = getNodo(e);
        E objeto = get(index);
        
        rem.data = objeto;
        
        
        return true; 

    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }

        Node<E> tmp = first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    @Override
    public boolean contains(E e) {
        if (e.equals(null)) {
            return false;
        }
        Node<E> tmp = first;
        for (int i = 0; i < size(); i++) {
            if (tmp.data.equals(e)) {
                return true;
            }
            tmp = tmp.next;

        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index > size()) {
            return false;
        }

        if (index == 0) {
            return removeFirst();
        } else if (index == size() - 1) {
            return removeLast();
        }
        Node<E> tmp = first.next;
        for (int i = 1; i < size() - 1; i++) {
            if (i == index) {
                tmp.tail.next = tmp.next;
                tmp.next.tail = tmp.tail;
                tam--;
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    @Override
    public boolean addLast(E data) {
        if (data == null) {
            return false;
        }
        Node<E> noden = new Node(data);
        if (isEmpty()) {
            first = noden;
            tam++;
            return true;
        }
        noden.next = first;
        first.tail = noden;
        if (tam == 1) {
            first.next = noden;
            noden.tail = first;
            last = noden;
            tam++;
            return true;
        }
        last.next = noden;
        noden.tail = last;
        last = noden;
        tam++;
        return true;
    }
    // metodo add que implementa el addLast para hacerlo mas eficiente

    public boolean add(E data) {
        return addLast(data);
    }

    //Metodos para navegar hacia atras y adelante del album
    public E getSiguiente(Node<E> n) {
        Node<E> sig = n.next;
        return sig.data;
    }

    public E getAnterior(Node<E> n) {
        Node<E> ant = n.tail;
        return ant.data;
    }

    public Node<E> getNodo(E e) {
        Node<E> tmp = first;
        if (e != null) {
            for (int i = 0; i < size(); i++) {
                if (e == tmp.data) {
                    return tmp;
                }
                tmp = tmp.next;
            }
        }
        return null;
    }

}
