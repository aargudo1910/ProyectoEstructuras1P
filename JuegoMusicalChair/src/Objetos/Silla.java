/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import DoubleCircularLinkedList.DoubleCircularLinkedList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Desarrollo
 */
public class Silla extends Objeto{
   
        
    public Silla(){
        
    }
    
    public Silla(double posX, double posY, ImageView image) {
        super(posX, posY, image);
    }
 
    public Silla obtImgS(){
        ImageView image = (new ImageView(new Image("/Imagenes/silla.png")));
      
        image.setFitWidth(50);
        image.setFitHeight(50);
        
        return new Silla(image.getX(),image.getY(),image);
    }
    
    public DoubleCircularLinkedList<Silla> ubicar(int numeroPersonas){
        double angulo = 0;
        double anguloInc;
        if (numeroPersonas<0) anguloInc=0;
        else{
            anguloInc = 360f/(numeroPersonas-1);
        }
        double posX; 
        double posY;
        
        DoubleCircularLinkedList<Silla> sillas = new DoubleCircularLinkedList<>();
        for(int i=0;i<numeroPersonas-1;i++){
            
            posX = createRadius(numeroPersonas)*Math.cos(Math.toRadians(angulo));
            posY = createRadius(numeroPersonas)*Math.sin(Math.toRadians(angulo));
            
            angulo+=anguloInc;        
            
            sillas.addLast(obtImgS());
            sillas.get(i).getImage().setX(posX+377);
            sillas.get(i).getImage().setY(posY+329);
            
        }
        
        return sillas;
        
    }
    public double createRadius(int numeroPersonas){
        return (numeroPersonas-1)*20;
    }
}