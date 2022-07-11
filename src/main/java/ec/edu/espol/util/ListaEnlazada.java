/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.util;

/**
 *
 * @author Roberto Encalada
 */
public class ListaEnlazada<E> implements List<E> {
    
    private Nodo<E> first;

    
    public ListaEnlazada(){
        first = null;

    }
    
    public ListaEnlazada(Nodo<E> first){
        this.first = first;
    }
    
    @Override
    public boolean addFirst(E e) {
        if(e == null) return false;
        
        Nodo<E> n = new Nodo(e);
        
        if(isEmpty()){
            first = n;
            return true;
        }
        
        n.sig = first;
        first = n;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if(e == null) return false;
        
        Nodo<E> f = first;
        Nodo<E> n = new Nodo(e);
        
        if(isEmpty()){
            first = n;
            return true;
        }
        while(f.sig != null){
            f = f.sig;
        }

        n.sig = f.sig;
        f.sig = n;
        return true;
        
    }

    @Override
    public E getFirst() {
        return (E) first.contenido;
    }

    @Override
    public E getLast() {
        Nodo<E> n = first;
        while(n.sig!=null){
            n=n.sig;
        }
        return (E) n.contenido;
    }

    @Override
    public int indexOf(E e) {
        if(isEmpty()) return -1;
        
        Nodo<E> n = first;
        for(int i = 0; i < size() ; i++){
            
            if(n.contenido == e){
                return i;
            }
            
            n = n.sig;
            
        }
        
        return -1;
    }

    @Override
    public int size() {
        int count = 0;
        Nodo<E> i = first;
        while (i != null) {
            count++;
            i = i.sig; 
        }
        return count;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()) return false;
        Nodo<E> n = first;
        
        while(n.sig.sig!=null){
            n = n.sig;
        }
        n.sig = null;
        
        return false;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false; 
        first = first.sig; 
        
        return true;
    }

    @Override
    public boolean insert(int index, E e) {
        if(index>size() || index<0) return false;
        if(e == null) return false;
        
        if(index == 0) return addFirst(e);
        if(index == size()) return addLast(e);
        
       
        Nodo<E> n = new Nodo(e);
        Nodo<E> f = first;
        
        for(int i = 0; i<index-1;i++){
            f = f.sig;
        }
        
        n.sig = f.sig;
        f.sig = n;
        
        return true;
    }

    @Override
    public boolean set(int index, E e) {
        if(index>size() || index<0) return false;
        if(e == null) return false;
        
        Nodo<E> n = new Nodo(e);
        Nodo<E> f = first;

        for(int i = 0; i<index;i++){
            f = f.sig;
        }
        
        f.contenido = n.contenido;
        
        
        return true;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size()) return null;
        Nodo<E> n = first;
        for(int i = 0; i<index; i++){
            n = n.sig;
            
        }
        return n.contenido;
    }

    @Override
    public boolean contains(E e) {
        Nodo<E> n = first;
        for(int i = 0; i<size(); i++){
            if(n.contenido.equals(e)) return true;
            n = n.sig;
            
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if(index < 0 || index >= size()) return false;
        Nodo<E> n = first;
        
        if(index == 0) return removeFirst();
        if(index == size()-1) return removeLast();
        
        for(int i = 0; i<index-1 ; i++){
            n = n.sig;
        }
        
        n.sig = n.sig.sig;
       return true;
    }
    
    @Override
    public String toString() {
        if(isEmpty()) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        
        Nodo<E> n = first;
        
        for(int i = 0; i<size();i++){
            sb.append(n.contenido).append(", ");
            n = n.sig;
        }
        sb.setLength(sb.length()-2);
        sb.append("]");
        return sb.toString();
    }
    
    private static class Nodo<E>{
        E contenido;
        Nodo sig;
        
        public Nodo(E e){
            contenido = e;
            sig = null;
        }
        
    }
    
}
