/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoubleLinkedList;

/**
 *
 * @author Desarrollo
 * @param <E>
 */
public class LinkedList<E> implements List<E> {
    private int tam = 0;
    private NodeList<E> last;

    public LinkedList() {
        this.last = null;
    }
    
    
    public void mostrar_elementos(){
        if(!isEmpty()){
            NodeList node = last.getNext();
            for(int i=0;i<tam;i++){
                System.out.print(node.getContent()+" ");
                node = node.getNext();
            }
        }else{
            System.out.println("¡Lista vacía!");
        }
    }
    
    public void addInEmpty(NodeList<E> node) {
        last = node;
        last.setNext(last);
        last.setBef(last);
        tam++;
    }
    
    @Override
    public boolean addFirst(E e) { 
        NodeList<E> node = new NodeList(e);
        if (e == null) {
            return false;
        }
        if (isEmpty()) {
            addInEmpty(node);
        } else {
            NodeList<E> aux = last.getNext();//guarda el actual primer nodo 
            node.setNext(aux);
            aux.setBef(node);
            last.setNext(node);
            node.setBef(last);
            
            tam++;
        }
        return true;
    }

    @Override
    public boolean addLast(E e) { //
        NodeList node = new NodeList(e);
        if (e == null) {
            return false;
        }
        if (isEmpty()) {
            addInEmpty(node);
        } else {
            
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

    @Override
    public E removeFirst() {
        E content=null;
       
        return content;
    }

    @Override
    public E removeLast() {
        E content = null;
        
        return content;
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
    }
    
    
    public void add(int index, E element) {
//        NodeList<E> n;// nodo viajero
//        header = last.getNext();
//        for (n = header; n != null; n = n.getNext()) {
//            n.setContent(null);
//        }
        
    }

//    public E remove(int index) {
////        header = last.getNext();
////        NodeList<E> n = header;
////        for(int i=0;i<index-1;i++){
////            n=n.getNext();
////        }
//        NodeList<E> nextN=n.getNext();
////        n.setNext(nextN.getNext());
//        E content=nextN.getContent();
////        nextN=null;
//        return content;
//    }

    public E get(int index) { //
        E content;
        if (index > size()) {
            return null;
        } else if(index==0) {
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
        if (index > size()) {
            return null;
        } else if(index==0) {
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
        String result = "";
        NodeList<E> n;// nodo viajero
        for (n = last.getNext(); n != null; n = n.getNext()) {
            result += n.getContent().toString() + ",";
        }
        return result;
    }


}

