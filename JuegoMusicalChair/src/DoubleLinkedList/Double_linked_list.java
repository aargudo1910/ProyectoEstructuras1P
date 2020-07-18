/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoubleLinkedList;

/**
 *
 * @author Mint
 */
public class Double_linked_list<E> {

    private static class Node<E> {

        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public Double_linked_list() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        } else {
            return header.getNext().getElement();
        }
    }

    public E last() {
        if (isEmpty()) {
            return null;
        } else {
            return trailer.getPrev().getElement();
        }
    }

    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }

    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            return remove(header.getNext());
        }
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            return remove(trailer.getPrev());
        }
    }

    private void addBetween(E e, Node<E> predecesor, Node<E> sucesor) {
        Node<E> nuevo = new Node<>(e, predecesor, sucesor);
        predecesor.setNext(nuevo);
        sucesor.setPrev(nuevo);
        size++;
    }

    private E remove(Node<E> node) {
        Node<E> predecesor = node.getPrev();
        Node<E> sucesor = node.getNext();
        predecesor.setNext(sucesor);
        sucesor.setPrev(predecesor);
        size--;
        return node.getElement();
    }

}
