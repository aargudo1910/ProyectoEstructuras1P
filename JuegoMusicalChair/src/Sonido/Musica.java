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
    private AudioClip sound;

    public Musica() {
        this.sound = java.applet.Applet.newAudioClip(getClass().getResource("/Sonido/cancion para persecucion graciosa.wav"));
    }

    public AudioClip getSound() {
        return sound;
    }

    public void setSound(AudioClip sound) {
        this.sound = sound;
    }
    
    
    
    
    
}
