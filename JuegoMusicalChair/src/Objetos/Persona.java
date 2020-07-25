/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import DoubleCircularLinkedList.DoubleCircularLinkedList;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author Desarrollo
 */
public class Persona extends Objeto {
    
    
    Silla s = new Silla();
    public Persona(){
        
    }
    public Persona(double posX, double posY, ImageView image) {
        super(posX, posY, image);
    }
    
    public Persona obtImageRmd (){
        ImageView image;
        int num = generarNumero(10);
        if(num==0){
            image = (new ImageView(new Image("/Imagenes/Persona2.png")));
        }else if(num==1){
            image = (new ImageView(new Image("/Imagenes/Persona3.png")));  
        }else{
            image = (new ImageView(new Image("/Imagenes/Persona1.png")));
        }
            
        // cambiar posición
        image.setX(250);
        image.setY(350);
        MoverP(image);
        image.setFitWidth(100);
        image.setFitHeight(100);
        
        Persona per = new Persona(image.getX(),image.getY(),image);
        return per;
    }
    
    public int generarNumero(int valor){
        Random rmd = new Random();
        return rmd.nextInt(valor);
    }
    
    public void MoverP(ImageView persona){

        Path p = this.createPath();
        PathTransition trans = new PathTransition(javafx.util.Duration.millis(10000),p,persona);
        trans.setOrientation(PathTransition.OrientationType.NONE);
        trans.setCycleCount(FadeTransition.INDEFINITE);
        trans.setDelay(Duration.seconds(10));
        
        trans.play();
        
    }
    
    public Path createPath(){
        Path path = new Path();
        MoveTo moveTo;
        LineTo line;
        
        
        path.setOpacity(0.0);
          
        
        return path;
    }
    
    @Override
    public double createRadius(int numeroPersonas){
        double radio;
        radio = s.createRadius(numeroPersonas)+100;
        return radio;
    }
    
}
