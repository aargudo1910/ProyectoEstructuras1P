/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import DoubleCircularLinkedList.DoubleCircularLinkedList;
import Objetos.Persona;
import Objetos.Silla;
import Sonido.Musica;
import java.applet.AudioClip;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Desarrollo
 */
public class FXMLDocumentController2 extends Thread implements Initializable {

    @FXML
    private Label labelPersonas;
    @FXML
    private TextField TextPersonas;
    @FXML
    private Label LabelDireccion;
    @FXML
    private Label lblMusica;
    @FXML
    private Button btnEject;
    @FXML
    private Pane PrincipalPane;
    @FXML
    private Button btnSigNivel;
    @FXML
    private ComboBox direcc;
    @FXML
    private ImageView imgFinal;
    @FXML
    private Button btnFinal;
    @FXML
    private ComboBox velocP;
    @FXML
    private Label LabelVelocidad;
    @FXML
    private Label labelNota;
    
    String direccion;
    int numPersonas;
    Integer[] imgs = new Integer[20];
    Persona p = new Persona();
    Silla sil = new Silla();
    Musica mus = new Musica();
    DoubleCircularLinkedList<Persona> Personas;
    DoubleCircularLinkedList<Silla> Sillas;
    @FXML
    private Button btnValidacion;
    
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnEject.setVisible(false);
        btnSigNivel.setVisible(false);
        ObservableList<String> list = FXCollections.observableArrayList("Horario", "AntiHorario");
        ObservableList<String> listv = FXCollections.observableArrayList("1", "2");
        direcc.setItems(list);
        direcc.setValue("Horario");
        velocP.setItems(listv);
        velocP.setValue("1");
        TextPersonas.setText(null);
        imgFinal.setVisible(false);
        btnFinal.setVisible(false);
        btnValidacion.setVisible(false);
        
    }

    @FXML
    private void btnEjecutarAccion(ActionEvent event) {
        labelNota.setVisible(false);
        btnEject.setVisible(false);
        numPersonas = Integer.parseInt(TextPersonas.getText());
        if(numPersonas<=1 || numPersonas>=9){
            btnValidacion.setVisible(true);
            return;
        }
        
        direccion = direcc.getSelectionModel().getSelectedItem().toString();
        String s = "Dirección: " + direccion;
        LabelDireccion.setText(s);
        direcc.setVisible(false);
        
        String a = "Numero de personas: " + TextPersonas.getText();
        labelPersonas.setText(a);
        TextPersonas.setVisible(false);
        
        String velocidad = velocP.getSelectionModel().getSelectedItem().toString();
        String b = "Velocidad: "+ velocidad;
        LabelVelocidad.setText(b);
        velocP.setVisible(false);
        
        Sillas = sil.ubicar(numPersonas);
        
        int veloc;
        if (velocidad.equals("1")) {
            veloc = 1;
        } else {
            veloc = 2;
        }
        
        Personas = p.ubicar(null, numPersonas, direccion, veloc, Sillas);
        for (int i = 0; i < numPersonas; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController2.class.getName()).log(Level.SEVERE, null, ex);
            }
            PrincipalPane.getChildren().add(Personas.get(i).getImage());
        }

        for (int i = 0; i < numPersonas - 1; i++) {
            PrincipalPane.getChildren().add(Sillas.get(i).getImage());
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(19), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < numPersonas; i++) {
                    PrincipalPane.getChildren().remove(Personas.get(i).getImage());
                    imgs[i] = Personas.get(i).getNumeroImagen();
                }
                for (int i = 0; i < numPersonas - 1; i++) {
                    PrincipalPane.getChildren().remove(Sillas.get(i).getImage());
                }
                
                btnSigNivel.setVisible(true);
            }
        }));
        timeline.setCycleCount(numPersonas - 1);
        timeline.play();

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(9), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mus.getSound().stop();
                lblMusica.setText("OFF");
            }
        }));
        timeline2.setCycleCount(1);
        timeline2.play();

        mus.getSound().play();
        lblMusica.setText("ON");
        System.out.println("ejecutar final");

    }

    @FXML
    private void btnSiguiente(ActionEvent event) {
        String velocidad = velocP.getSelectionModel().getSelectedItem().toString();
        numPersonas = numPersonas - 1;
        btnSigNivel.setVisible(false);
        String a = "Numero de personas: " + numPersonas;
        labelPersonas.setText(a);
       
        if(numPersonas==1){
            AudioClip Sound;
            Sound = java.applet.Applet.newAudioClip(getClass().getResource("/Sonido/aplausos.wav"));
            mus.setSound(Sound);
            mus.getSound().play();
            imgFinal.setVisible(true);
            btnFinal.setVisible(true);
            lblMusica.setText("ON");
            return;
        }
    
        if (direccion.equals("Horario")) {
            direccion = "AntiHorario";
            LabelDireccion.setText("Dirección: AntiHorario");
        } else {
            direccion = "Horario";
            LabelDireccion.setText("Dirección: Horario");
        }
        
        int veloc;
        if (velocidad.equals("1")) {
            veloc = 1;
        } else {
            veloc = 2;
        }
        
        Sillas = sil.ubicar(numPersonas);
        System.out.println("imgs : " + imgs[0] + "  " + imgs[1] + "  " + imgs[2]);
        Personas = p.ubicar(imgs, numPersonas, direccion, veloc, Sillas);
        for (int i = 0; i < numPersonas; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController2.class.getName()).log(Level.SEVERE, null, ex);
            }
            PrincipalPane.getChildren().add(Personas.get(i).getImage());
        }

        for (int i = 0; i < numPersonas - 1; i++) {
            PrincipalPane.getChildren().add(Sillas.get(i).getImage());
        }
        mus.getSound().play();
        lblMusica.setText("ON");
        
        Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(9), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mus.getSound().stop();
                lblMusica.setText("OFF");
            }
        }));
        timeline3.setCycleCount(1);
        timeline3.play();
        
        
    }

    @FXML
    public void obtDir(ActionEvent event) {
        if (TextPersonas.getText() != null && direcc.getValue() != null) {
            btnEject.setVisible(true);
        }
        direcc.setUserData(direcc.getValue());

    }

    @FXML
    private void confirmar(ActionEvent event) {
        if (TextPersonas.getText() != null && direcc.getValue() != null) {
            btnEject.setVisible(true);
        }
    }

    @FXML
    private void btnFinal(ActionEvent event) {
        mus.getSound().stop();
        btnFinal.setVisible(false);
        
    }

    @FXML
    private void btnValidar(ActionEvent event) {
        btnEject.setVisible(false);
        direcc.setVisible(true);
        TextPersonas.setVisible(true);
        velocP.setVisible(true);
        btnValidacion.setVisible(false);
    }
    
}
