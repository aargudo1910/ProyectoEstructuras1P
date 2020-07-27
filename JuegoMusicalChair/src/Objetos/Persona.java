/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import DoubleCircularLinkedList.DoubleCircularLinkedList;
import Sonido.Musica;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    PathTransition trans = new PathTransition();
    Persona per;
    Musica mus = new Musica();
    public Persona(){
        
    }
    public Persona(double posX, double posY, ImageView image) {
        super(posX, posY, image);
    }
    
    public Persona obtImageRmd (){
        ImageView image = null;
        int num = generarNumero(8);
        System.out.println("se imprime "+num);
        switch (num) {
            case 0:
                image = (new ImageView(new Image("/Imagenes/Persona1.png")));
                break;
            case 1:
                image = (new ImageView(new Image("/Imagenes/Persona2.png")));
                break;
            case 2:
                image = (new ImageView(new Image("/Imagenes/Persona3.png")));
                break;
            case 3:
                image = (new ImageView(new Image("/Imagenes/Persona4.png")));
                break;
            case 4:
                image = (new ImageView(new Image("/Imagenes/Persona5.png")));
                break;
            case 5:
                image = (new ImageView(new Image("/Imagenes/Persona6.png")));
                break;
            case 6:
                image = (new ImageView(new Image("/Imagenes/Persona7.png")));
                break;
            case 7:
                image = (new ImageView(new Image("/Imagenes/Persona8.png")));
                break;
        }
        
        image.setFitWidth(100);
        image.setFitHeight(100);
        
        
        per = new Persona(image.getX(),image.getY(),image);
        return per;
    }
    
    public int generarNumero(int valor){
        Random rmd = new Random();
        return rmd.nextInt(valor);
    }
    
    public void MoverPHor(Persona per, int numPersonas){
     
        Path p = this.createPath(per,numPersonas);
        trans = new PathTransition(javafx.util.Duration.millis(10000),p,per.getImage());
        trans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        trans.setCycleCount(10);
        
        trans.setDelay(Duration.seconds(2)); 
        trans.setRate(1);
        trans.playFrom(Duration.seconds(2));
    }
    
    public void MoverPAntiHor(Persona per, int numPersonas){
     
        Path p = this.createPathAntiHor(per,numPersonas);
        trans = new PathTransition(javafx.util.Duration.millis(10000),p,per.getImage());
        trans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        trans.setCycleCount(10);
        
        trans.setDelay(Duration.seconds(2)); 
        trans.setRate(1);
        trans.playFrom(Duration.seconds(2));
    }
    
    public Path createPath(Persona per, int numeroPersonas){

        double angulo = Math.atan2(per.getImage().getY(),per.getImage().getX()); //rad
        double angulof = angulo + Math.toRadians(360);
        double anguloInc = Math.toRadians(40);
        
        double posx; double posy;
        Path path = new Path();
        
        MoveTo m = new MoveTo(per.getImage().getX(),per.getImage().getY());      
        path.getElements().add(m);
        
        // HACER UN WHILE
        
        while(angulo<=angulof){ 
            angulo+=anguloInc;
            posx = createRadius(numeroPersonas)*Math.cos(angulo);
            posy = createRadius(numeroPersonas)*Math.sin(angulo);
            
            LineTo line = new LineTo(377+posx,329+posy); 
            path.getElements().add(line);
        }
        LineTo m1 = new LineTo(per.getImage().getX(),per.getImage().getY());      
        path.getElements().add(m1);
        return path;
        
    }
    
    public Path createPathAntiHor(Persona per, int numeroPersonas){
//        trans.stop();
        double angulof = Math.atan2(per.getImage().getY(),per.getImage().getX()); //rad
        double angulo = angulof + Math.toRadians(360);
        double anguloInc = Math.toRadians(40);
        
        double posx; double posy;
        Path path = new Path();
        
        MoveTo m = new MoveTo(per.getImage().getX()+50,per.getImage().getY());      
        path.getElements().add(m);
        
        // HACER UN WHILE
        
        while(angulo>=angulof){ 
            angulo-=anguloInc;
            posx = createRadius(numeroPersonas)*Math.cos(angulo);
            posy = createRadius(numeroPersonas)*Math.sin(angulo);
            
            LineTo line = new LineTo(377+posx,329+posy); 
            path.getElements().add(line);
        }
        LineTo m1 = new LineTo(per.getImage().getX()+30,per.getImage().getY()+30);      
        path.getElements().add(m1);
        return path;
        
    }
    
    public DoubleCircularLinkedList<Persona> ubicar(int numeroPersonas, String direccion){
        double angulo = 0;
        double posx; double posy;
        
        DoubleCircularLinkedList<Persona> Personas = new DoubleCircularLinkedList<>();
        for(int i=0;i<numeroPersonas;i++){
            posx = createRadius(numeroPersonas)*Math.cos(Math.toRadians(270))+371;
            posy = createRadius(numeroPersonas)*Math.sin(Math.toRadians(angulo))+340;
            Personas.addLast(obtImageRmd());
            
            
            
            if(direccion.equals("Horario")){
                cambiarPosicion(Personas.get(i),371,329+createRadius(numeroPersonas));
                Personas.get(i).MoverPHor(Personas.get(i),numeroPersonas);
            }else
            if(direccion.equals("AntiHorario")){
                cambiarPosicion(Personas.get(i),450,350+s.createRadius(numeroPersonas));
                Personas.get(i).MoverPAntiHor(Personas.get(i), numeroPersonas);
            }
            
        }
       
        return Personas;
        
    }
   
    @Override
    public double createRadius(int numeroPersonas){
        return s.createRadius(numeroPersonas)+125;
    }
    
    public void cambiarPosicion(Persona p, double x, double y){
        p.getImage().setX(x); p.getImage().setY(y);
    }
    public PathTransition getTrans() {
        return trans;
    }

    public void setTrans(PathTransition trans) {
        this.trans = trans;
    }

    
    
    
}
