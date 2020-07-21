/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sonido;

import java.applet.AudioClip;

/**
 *
 * @author Desarrollo
 */
public class Musica {
    private AudioClip Sound;

    public Musica() {
        this.Sound = java.applet.Applet.newAudioClip(getClass().getResource("/Sonido/Cancion.wav"));
    }

    public AudioClip getSound() {
        return Sound;
    }

    public void setSound(AudioClip Sound) {
        this.Sound = Sound;
    }
    
    
    
    
    
}
