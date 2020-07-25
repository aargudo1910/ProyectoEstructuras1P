/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import DoubleCircularLinkedList.DoubleCircularLinkedList;
import Juego.FXMLDocumentController2;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Desarrollo
 */
public class Silla extends Objeto{
    double orbitalPeriod ;
        double portion;
        double angle;
        double totx;
        double toty;
        
    public Silla(){
        
    }
    
    public Silla(double posX, double posY, ImageView image) {
        super(posX, posY, image);
    }
 
    public Silla obtImgS(){
        ImageView image = (new ImageView(new Image("/Imagenes/silla.png")));
      
        image.setFitWidth(100);
        image.setFitHeight(100);
        Silla silla = new Silla(image.getX(),image.getY(),image);
        
        return silla;
    }
    
    public void ubicar(ImageView persona){
                                                                                    //// crear un path con esto y hacer mover personas
        double x=366;
        double y = 193;
        
        double radius = 75;
        int time=5;
        
        orbitalPeriod = 2000.0;
        portion = (time%orbitalPeriod)/orbitalPeriod;
        angle=portion*2*Math.PI;
        totx+=x+radius*Math.cos(angle);
        toty+=y+radius*Math.sin(angle);
        
        persona.setX(totx);
        persona.setY(toty);
        
        
    }
}
