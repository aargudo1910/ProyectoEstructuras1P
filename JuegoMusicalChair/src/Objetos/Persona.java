/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import DoubleCircularLinkedList.DoubleCircularLinkedList;
import Sonido.Musica;
import java.util.Random;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
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
    SequentialTransition trans = new SequentialTransition();
    Persona per;
    Musica mus = new Musica();
    int numeroImagen;
    
    public Persona(){
        
    }
    public Persona(double posX, double posY, ImageView image) {
        super(posX, posY, image);
    }
    
    public Persona obtImageRmd (Integer numeroImg){
        ImageView image = null;
        int num;
        if(numeroImg == null){
            num = generarNumero(8);   
        } else {
            num = numeroImg;
        }
        
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
        
        
        per = new Persona(image.getX(), image.getY(), image);
        per.numeroImagen = num;
        return per;
    }
    
    public int generarNumero(int valor){
        Random rmd = new Random();
        return rmd.nextInt(valor);
    }
    
    public void MoverPHor(Persona per, int numPersonas, Double sillaX, Double sillaY) {
        
        Path p = this.createPathHorario(550, 650, 150, 150, 1);
        trans.getChildren().add(new PathTransition(javafx.util.Duration.millis(10000), p, per.getImage()));
        if (sillaX != null && sillaY != null) {
            Path pSentar = this.createPathSentar(550, 650, 150, 150, sillaX, sillaY);
            trans.getChildren().add(new PathTransition(javafx.util.Duration.millis(5000), pSentar, per.getImage()));
        }

        trans.setCycleCount(1);

        trans.setDelay(Duration.seconds(2));
        trans.setRate(1);
        trans.playFrom(Duration.seconds(2));
    }
    
    public void MoverPAntiHor(Persona per, int numPersonas, Double sillaX, Double sillaY) {

        Path p = this.createPathAntiHorario(550, 350, 150, 150, 1);
        trans.getChildren().add(new PathTransition(javafx.util.Duration.millis(10000), p, per.getImage()));
        if (sillaX != null && sillaY != null) {
            Path pSentar = this.createPathSentar(550, 350, 150, 150, sillaX, sillaY);
            trans.getChildren().add(new PathTransition(javafx.util.Duration.millis(5000), pSentar, per.getImage()));
        }
        trans.setCycleCount(1);

        trans.setDelay(Duration.seconds(2));
        trans.setRate(1);
        trans.playFrom(Duration.seconds(2));
        
    }
    
    public Path createPath(Persona per, int numeroPersonas) {

        double angulo = Math.atan2(per.getImage().getY(), per.getImage().getX()); //rad
        double angulof = angulo + Math.toRadians(360);
        double anguloInc = Math.toRadians(40);

        double posx;
        double posy;
        Path path = new Path();

        MoveTo m = new MoveTo(per.getImage().getX(), per.getImage().getY());
        path.getElements().add(m);

        // HACER UN WHILE
        while (angulo <= angulof) {
            angulo += anguloInc;
            posx = createRadius(numeroPersonas) * Math.cos(angulo);
            posy = createRadius(numeroPersonas) * Math.sin(angulo);

            LineTo line = new LineTo(377 + posx, 329 + posy);
            path.getElements().add(line);
        }
        LineTo m1 = new LineTo(per.getImage().getX(), per.getImage().getY());
        path.getElements().add(m1);
        return path;

    }
    
    private Path createPathHorario(double centerX, double centerY, double radiusX, double radiusY, double rotate) {

        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radiusX + 1); // simula 360 grados
        arcTo.setY(centerY - radiusY);
        arcTo.setSweepFlag(true);
        arcTo.setLargeArcFlag(true);
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        arcTo.setXAxisRotation(rotate);

        Path pathCircle = new Path();
        pathCircle.getElements().addAll(new MoveTo(centerX - radiusX, centerY - radiusY), arcTo);
        pathCircle.getElements().add(new ClosePath());

        // Styling
        pathCircle.setStroke(Color.RED);
        pathCircle.getStrokeDashArray().setAll(5d, 5d);

        return pathCircle;

    }
    
    private Path createPathAntiHorario(double centerX, double centerY, double radiusX, double radiusY, double rotate) {

        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radiusX + 1); // simular 360 grados
        arcTo.setY(centerY - radiusY);
        arcTo.setSweepFlag(false); 
        arcTo.setLargeArcFlag(true);
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        arcTo.setXAxisRotation(rotate); 

        Path pathCircle = new Path();
        pathCircle.getElements().addAll(new MoveTo(centerX - radiusX, centerY - radiusY), arcTo);
        pathCircle.getElements().add(new ClosePath());

        // Styling
        pathCircle.setStroke(Color.RED);
        pathCircle.getStrokeDashArray().setAll(5d, 5d);

        return pathCircle;
    }
    
    public Path createPathSentar(double centerX, double centerY, double radiusX, double radiusY, double sillaX, double sillaY) {

        MoveTo moveTo = new MoveTo();
        moveTo.setX(centerX - radiusX);
        moveTo.setY(centerY - radiusY);
        LineTo lineTo = new LineTo();
        lineTo.setX(sillaX);
        lineTo.setY(sillaY);

        Path pathCircle = new Path();
        pathCircle.getElements().add(moveTo);
        pathCircle.getElements().add(lineTo);

        return pathCircle;

    }

    public DoubleCircularLinkedList<Persona> ubicar(Integer[] imgs, int numeroPersonas, String direccion, DoubleCircularLinkedList<Silla> sillas) {
        double angulo = 0;
        double posx;
        double posy;

        DoubleCircularLinkedList<Persona> Personas = new DoubleCircularLinkedList<>();
        for (int i = 0; i < numeroPersonas; i++) {
            posx = createRadius(numeroPersonas) * Math.cos(Math.toRadians(270)) + 371;
            posy = createRadius(numeroPersonas) * Math.sin(Math.toRadians(angulo)) + 340;
            if(imgs != null && imgs[i] != null){
                Personas.addLast(obtImageRmd(imgs[i]));
            } else {
                Personas.addLast(obtImageRmd(null));
            }
            

            if (direccion.equals("Horario")) {
                cambiarPosicion(Personas.get(i), 371, 329 + createRadius(numeroPersonas));
                if (i == (numeroPersonas - 1)) {
                    Personas.get(i).MoverPHor(Personas.get(i), numeroPersonas, null, null);
                } else {
                    Personas.get(i).MoverPHor(Personas.get(i), numeroPersonas, sillas.get(i).getImage().getX(), sillas.get(i).getImage().getY());
                }

            } else if (direccion.equals("AntiHorario")) {
                cambiarPosicion(Personas.get(i), 450, 350 + s.createRadius(numeroPersonas));
                if (i == (numeroPersonas - 1)) {
                    Personas.get(i).MoverPAntiHor(Personas.get(i), numeroPersonas, null, null);
                } else {
                    Personas.get(i).MoverPAntiHor(Personas.get(i), numeroPersonas, sillas.get(i).getImage().getX(), sillas.get(i).getImage().getY());
                }
                
            }

        }

        return Personas;

    }
    public double createRadius(int numeroPersonas) {
        return s.createRadius(numeroPersonas) + 150;
    }

    public void cambiarPosicion(Persona p, double x, double y) {
        p.getImage().setX(x);
        p.getImage().setY(y);
    }

    public SequentialTransition getTrans() {
        return trans;
    }

    public void setTrans(SequentialTransition trans) {
        this.trans = trans;
    }

    public int getNumeroImagen() {
        return numeroImagen;
    }

    public void setNumeroImagen(int numeroImagen) {
        this.numeroImagen = numeroImagen;
    }

    
}
