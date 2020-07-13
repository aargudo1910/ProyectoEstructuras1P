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
public class NodeList<E> {
    private E content; 
    private NodeList<E> next;
    private NodeList<E> bef;

    public NodeList(E content) {
        this.content = content;
        this.next=null;
    }

    public NodeList(E content, NodeList<E> next) {
        this.content = content;
        this.next = next;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public NodeList<E> getNext() {
        return next;
    }

    public void setNext(NodeList<E> next) {
        this.next = next;
    }

    public NodeList<E> getBef() {
        return bef;
    }

    public void setBef(NodeList<E> bef) {
        this.bef = bef;
    }
    
    
    
    

    
    
}
