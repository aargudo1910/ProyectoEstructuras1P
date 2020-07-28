/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import javafx.scene.image.ImageView;

/**
 *
 * @author Desarrollo
 */
abstract class Objeto {
    private double posX;
    private double posY;
    private ImageView image;
    
    public Objeto(){
        
    }
    public Objeto(double posX, double posY, ImageView image) {
        this.posX = posX;
        this.posY = posY;
        this.image = image;
        
    }

    public final double getPosX() {
        return posX;
    }

    public final void setPosX(double posX) {
        this.posX = posX;
    }

    public final double getPosY() {
        return posY;
    }

    public final void setPosY(double posY) {
        this.posY = posY;
    }

    public final ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
        
    }
   
}
