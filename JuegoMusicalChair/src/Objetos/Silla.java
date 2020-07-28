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
        Silla silla = new Silla(image.getX(),image.getY(),image);
        
        return silla;
    }
    
    public DoubleCircularLinkedList<Silla> ubicar(int numeroPersonas){
        double angulo = 0;
        double anguloInc = 360/(numeroPersonas-1);
        double posx; double posy;
        
        DoubleCircularLinkedList<Silla> Sillas = new DoubleCircularLinkedList<>();
        for(int i=0;i<numeroPersonas-1;i++){
            
            posx = createRadius(numeroPersonas)*Math.cos(Math.toRadians(angulo));
            posy = createRadius(numeroPersonas)*Math.sin(Math.toRadians(angulo));
            
            angulo+=anguloInc;        
            
            Sillas.addLast(obtImgS());
            Sillas.get(i).getImage().setX(posx+377);
            Sillas.get(i).getImage().setY(posy+329);
            
        }
        
        return Sillas;
        
    }
    
    @Override
    public double createRadius(int numeroPersonas){
        return (numeroPersonas-1)*20;
    }
}