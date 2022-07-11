/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.util;

import java.io.Serializable;
import java.util.RandomAccess;


/**
 *
 * @author Roberto Encalada
 * @param <E>
 */
public class ListaArreglo<E> implements List<E>, Serializable, RandomAccess{

    private E[] arreglo;
    private int capacidad;
    private int tam;

    public ListaArreglo() {
        capacidad = 10;
        arreglo = (E[]) new Object[capacidad];
        tam = 0;
    }

    public ListaArreglo(int capacidad) {
        this.capacidad = capacidad;
        arreglo = (E[]) new Object[capacidad];
        tam = 0;
    }

    @Override
    public boolean addFirst(E e) {

        if (e == null) {
            return false;
        }

        if (isEmpty()) {
            arreglo[0] = e;
            tam = 1;
            return true;
        }

        if (tam == capacidad) {
            crecerArreglo();
        }

        for (int i = tam; i > 0; i--) {
            arreglo[i] = arreglo[i - 1];
        }

        arreglo[0] = e;
        tam++;
        return true;
    }

    private void crecerArreglo() {
        capacidad = capacidad * 2;
        E[] arreglo2 = (E[]) new Object[capacidad];
        for (int i = 0; i < tam; i++) {
            arreglo2[i] = arreglo[i];
        }
        arreglo = arreglo2;
    }

    private void disminuirArreglo() {
        capacidad = (int) Math.round(tam * 1.5);
        E[] arreglo2 = (E[]) new Object[capacidad];
        for (int i = 0; i < tam; i++) {
            arreglo2[i] = arreglo[i];
        }
        arreglo = arreglo2;
    }

    @Override
    public boolean addLast(E e) {

        if (isEmpty()) {
            arreglo[0] = e;
        }

        if (tam == capacidad) {
            crecerArreglo();
        }

        arreglo[tam] = e;
        tam++;
        return false;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return arreglo[0];
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        }
        return arreglo[tam - 1];
    }

    @Override
    public int indexOf(E e) {
        int pos = 0;
        for (E el : arreglo) {
            if (el.equals(e)) {
                return pos;
            }
            pos++;
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

        if (capacidad > tam * 2) {
            disminuirArreglo();
        }

        E[] arreglo2 = (E[]) new Object[capacidad];

        for (int i = 0; i < tam - 1; i++) {
            arreglo2[i] = arreglo[i];
        }

        arreglo = arreglo2;
        tam--;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        if (capacidad > tam * 2) {
            disminuirArreglo();
        }

        E[] arreglo2 = (E[]) new Object[capacidad];

        for (int i = 0; i < tam - 1; i++) {
            arreglo2[i] = arreglo[i + 1];
        }

        arreglo = arreglo2;
        tam--;
        return true;
    }

    @Override
    public boolean insert(int index, E e) {
        if (index < 0 || index > size()) {
            return false;
        }
        if (e == null) {
            return false;
        }

        if (index == 0) {
            return addFirst(e);
        }
        if (index == size()) {
            return addLast(e);
        }

        for (int i = tam; i > index; i--) {
            arreglo[i] = arreglo[i - 1];
        }
        arreglo[index] = e;
        tam++;
        return true;

    }

    @Override
    public boolean set(int index, E e) {
        if (isEmpty()) {
            return false;
        }
        if (e == null) {
            return false;
        }

        arreglo[index] = e;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return tam == 0;
    }

    @Override
    public E get(int index) {
        return arreglo[index];
    }

    @Override
    public boolean contains(E e) {
        for (E el : arreglo) {
            if (el.equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index > tam) {
            return false;
        }

        if (index == 0) {
            return removeFirst();
        }
        if (index == size()) {
            return removeLast();
        }

        arreglo[index] = null;

        for (int i = index; i < tam; i++) {
            arreglo[i] = arreglo[i + 1];
        }
        tam--;
        return true;
    }

    @Override
    public String toString() {

        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tam; i++) {
            sb.append(arreglo[i]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");

        return sb.toString();
    }
    
}
