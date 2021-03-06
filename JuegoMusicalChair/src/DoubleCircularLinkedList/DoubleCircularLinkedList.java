/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoubleCircularLinkedList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Desarrollo
 * @param <E>
 */
public class DoubleCircularLinkedList<E> implements List<E> {
    private int tam;
    private NodeList<E> last;
    
    public DoubleCircularLinkedList() {
        this.last = null;
        tam=0;
    }
    
    private boolean addInEmpty(NodeList<E> node) {
        if(node==null)return false;
        
        last = node;
        last.setNext(last);
        last.setBef(last);
        tam++;
        return true;
    }
    
    @Override
    public boolean addFirst(E e) {
        if(e==null)return false;
        NodeList<E> node = new NodeList<>();
        node.setContent(e);
        if (isEmpty()) {
            addInEmpty(node);
        } else if(size()==1){
            last.setNext(node);
            last.setBef(node);
            node.setNext(last);
            node.setBef(last);
            tam++;
        }
        else {
            NodeList<E> aux = last.getNext();//guarda el actual primer nodo 
            node.setNext(aux);
            node.setBef(last);
            last.setNext(node);
            aux.setBef(node);
            tam++;
        }
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if (e == null) return false;
        
        NodeList<E> node = new NodeList<>();
        node.setContent(e);
        if (isEmpty()) {
            addInEmpty(node);
        }
        else if(size()==1){
            NodeList<E> aux = last;
            aux.setNext(node);
            aux.setBef(node);
            node.setNext(aux);
            node.setBef(aux);
            last=node;
            tam++;
        } 
        else {
            
            NodeList<E> aux = last; //guarda el actual last
            node.setNext(aux.getNext());
            node.getNext().setBef(node);
            aux.setNext(node);
            node.setBef(aux);
            last = node;
           
            tam++;
        }
        return true;
    }
    
    private E removeUnique(){
        if (isEmpty())return null;
        E unique=last.getContent();
        last.setContent(null);
        last.setBef(null);
        last.setNext(null);
        tam--;
        return unique;

    } 

    @Override
    public E removeFirst() {
        if (isEmpty())return null;
        else if(size()==1)removeUnique();
        else if(size()==2){
            NodeList<E> nextLast = last.getNext();
            E content=nextLast.getContent();
            nextLast.setBef(null);
            nextLast.setNext(null);
            nextLast.setContent(null);
            tam--;
            return content;
        }
        else {
            NodeList<E> nextLast = last.getNext();
            E content=nextLast.getContent();
            last.setNext(nextLast.getNext());
            nextLast.getNext().setBef(last);
            nextLast.setBef(null);
            nextLast.setNext(null);
            nextLast.setContent(null);
            tam--;
            return content;
        }
        return null;
    }

    @Override
    public E removeLast() {
        if (isEmpty())return null;
        else if(size()==1)removeUnique();
        else if(size()==2){
            NodeList<E> newLast = last;
            E content=newLast.getContent();
            last=newLast.getNext();
            newLast.setBef(null);
            newLast.setContent(null);
            newLast.setNext(null);
            tam--;
            return content;
        }
        else {
            NodeList<E> newLast = last;
            E content=newLast.getContent();
            last = newLast.getBef();
            
            newLast.getNext().setBef(newLast.getBef()); 
            newLast.getBef().setNext(newLast.getNext());
            newLast.setBef(null);
            newLast.setNext(null);
            newLast.setContent(null);
            
            tam--;
            return content;
        }
        return null;
    }
    
    public E remove(int index) {
        if (isEmpty()&&index>tam) return null;
        else if(index==0)removeFirst();
        else if(index==size()-1) removeLast();
        
        else {
            NodeList<E> n=null;
            try{
                n = nodeIndex(index);
                if(n!=null){
                    E content = n.getContent();
                    n.getBef().setNext(n.getNext());
                    n.getNext().setBef(n.getBef());
                    n.setNext(null);
                    n.setBef(null);
                    n.setContent(null);
                    tam--;
                    return content;
                }
            }catch(NullPointerException ex){
                Logger.getLogger(DoubleCircularLinkedList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public int size() { //
        return tam;
    }
    
    @Override
    public boolean isEmpty() { //
        return tam==0;
    }

    @Override
    public void clear() { //
        NodeList<E> n;// nodo viajero
        for (n = last.getNext(); n != null; n = n.getNext()) {
            n.setContent(null);
        }
        last.setNext(null);
        last=null;
        tam=0;
    }
    
    public E getNext(int index) { // índice donde estoy
        if(isEmpty()&&index > size())return null;
        E content;
        if(index==0) {
            content = last.getNext().getNext().getContent();
        }else{
            NodeList<E> n = last.getNext();
            for (int i = 0; i < index; i++) {
                n = n.getNext().getNext();
            }
            content = n.getContent();
        }
        return content;
    }
    
    public E getPrevius(int index) { // índice donde estoy
        if(isEmpty()&&index > size())return null;
        E content;
        if(index==0) {
            content = last.getContent();
        }else{
            NodeList<E> n = last.getNext();
            for (int i = size(); i > index; i--) {
                n = n.getBef().getBef();
            }
            content = n.getContent();
        }
        return content;
    }
    
    public E get(int index) { //
        if(isEmpty()&&index > size())return null;
        E content;
        if(index==0) {
            content = last.getNext().getContent();
        }else{
            NodeList<E> n = last.getNext();
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            content = n.getContent();
        }
        return content;
    }

    public E set(int index, E element) { //
        E content;
        if (index > size()) return null;
        else if(element==null)return null;
        else if(isEmpty())return null;
        else if(index==0) {
            content = last.getNext().getContent();
            last.getNext().setContent(element);
        }else{
            NodeList<E> n = last.getNext();
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }

            content = n.getContent();
            n.setContent(element);
        }
        return content;
    }
    
    @Override
    public String toString() {
        if(isEmpty()) return "[]";
        StringBuilder bld = new StringBuilder ("[");
        NodeList<E> n;// nodo viajero
        for (n = last.getNext(); n != null; n = n.getNext()) {
            bld.append (n.getContent().toString() + ", ");
        }
        String str=bld.toString();
        String subStr = str.substring(0, str.length() - 2);
        subStr += subStr + "]";
        return subStr;
    }
    
     private NodeList<E> nodeIndex(int index){
        if(index>tam) return null;
        else{
            NodeList<E> q = last.getNext();
            int cont = 0;
            while(index != cont){
                q = q.getNext();
                cont++;
            }
            return q;
        }
    }


}

