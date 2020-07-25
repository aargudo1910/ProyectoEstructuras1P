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
        
    }
    
    public DoubleCircularLinkedList<Persona> ubicar(int numeroPersonas){
        double angulo = 0;
        double anguloInc = 360/(numeroPersonas);
        double posx; double posy;
        
        DoubleCircularLinkedList<Persona> Personas = new DoubleCircularLinkedList<>();
        for(int i=0;i<numeroPersonas;i++){
            
            posx = createRadius(numeroPersonas)*Math.cos(Math.toRadians(angulo));
            posy = createRadius(numeroPersonas)*Math.sin(Math.toRadians(angulo));
            
            angulo+=anguloInc;        
            
            Personas.addLast(obtImageRmd());
            Personas.get(i).getImage().setX(posx+377);
            Personas.get(i).getImage().setY(posy+329);
            
        }
        
        return Personas;
        
    }
    
    @Override
    public double createRadius(int numeroPersonas){
        double radio;
        radio = s.createRadius(numeroPersonas)+100;
        return radio;
    }
    
}
