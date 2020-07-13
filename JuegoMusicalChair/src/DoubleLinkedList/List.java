/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoubleLinkedList;

/**
 *
 * @author Desarrollo
 */
public interface List<E>{
    /*** Métodos que deben ser implementados en la clase ArrayList como PRÁCTICA y en la clase LinkedList como TAREA ****/

    public boolean addFirst(E e); // inserta el elemento e al inicio

    public boolean addLast(E e); // inserta el elemento e al final

    public E removeFirst(); // remueve el elemento al inicio de la lista

    public E removeLast(); // remueve el elemento al final de la lista

    public int size();

    public boolean isEmpty();

    public void clear();
    
}
