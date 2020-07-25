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
        image.setX(364+150);
        image.setY(192);
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
        trans.setDelay(Duration.seconds(2));
        
        trans.play();
        
    }
    
    public Path createPath(){
        Path path = new Path();
        MoveTo moveTo;
        LineTo line;
                
        int x=0; int y=0; 
        int a = 450;int b = 350;
        
        ArrayList<Integer> Lx = new ArrayList<>();
        ArrayList<Integer> Ly = new ArrayList<>();
   
        for(x=200;x<700;x++){
            for(y=100;y<600;y++){
                int x2=(x-a)*(x-a); int y2=(y-b)*(y-b);
                if(x2+y2==62500){
                    Lx.add(x);
                    Ly.add(y);
                }
            }
        }
        
        moveTo = new MoveTo(Lx.get(0),Ly.get(0)); // X=250 Y=350
        path.getElements().add(moveTo);
        int tam = Lx.size();
        System.out.println(" 1NI\n "+Lx.get(0)+" "+Ly.get(0)+ " "+ Lx.size());
        
        for(int i=1;i<tam/2;i++){ 
            if(Lx.get(i)<a && Ly.get(i)>b){
                line = new LineTo(Lx.get(i),Ly.get(i));
                path.getElements().addAll(line);
                System.out.println(" 1\n "+Lx.get(i)+" "+Ly.get(i));
            }
        }
        
        line = new LineTo(Lx.get(tam-1),Ly.get(tam-1));
        path.getElements().addAll(line);
        System.out.println(" 1mit\n "+Lx.get(tam-1)+" "+Ly.get(tam-1));
        
        for(int i=tam-1;i>tam/2;i--){ 
            if(Lx.get(i)>a && Ly.get(i)>b){
                line = new LineTo(Lx.get(i),Ly.get(i));
                path.getElements().addAll(line);
                System.out.println(" 2\n "+Lx.get(i)+" "+Ly.get(i));
            }
             
        }
        
        for(int i=tam-1;i>tam/2;i--){ 
            if(Lx.get(i)>a && Ly.get(i)<b){
                line = new LineTo(Lx.get(i),Ly.get(i));
                path.getElements().addAll(line);
                System.out.println(" 3\n "+Lx.get(i)+" "+Ly.get(i));
            }
             
        }
        
        for(int i=(tam/2)-1;i>0;i--){ 
            if(Lx.get(i)<a && Ly.get(i)<b){
                line = new LineTo(Lx.get(i),Ly.get(i));
                path.getElements().addAll(line);
                System.out.println(" 4\n "+Lx.get(i)+" "+Ly.get(i));
            }
        }
        
        path.setOpacity(0.0);
          
        
        return path;
    }
    
    
    
}
