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
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
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
    private Button btnMusica;

    @FXML
    private Pane PrincipalPane;
    @FXML
    private Button btnSigNivel;
    @FXML
    private ComboBox direcc;
    @FXML
    private Button btnDirec;
    
    String direccion;
    int numPersonas;
    Persona p = new Persona();
    Silla sil = new Silla();
    Musica mus = new Musica();
    DoubleCircularLinkedList<Persona> Personas;
    DoubleCircularLinkedList<Silla> Sillas;
    HiloMovimiento hilo = new HiloMovimiento();
           
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
        direcc.setItems(list);
        direcc.setValue("Horario");
        TextPersonas.setText(null);
        btnDirec.setVisible(false);
    }

    @FXML
    private void musicaBtnAccion(ActionEvent event) {
            if(lblMusica.getText().equals("ON")){
                mus.getSound().stop();
                lblMusica.setText("OFF");
            }else{
                mus.getSound().play();
                lblMusica.setText("ON");
            }  
    }

    @FXML
    private void btnEjecutarAccion(ActionEvent event) {
        btnEject.setVisible(false);
        direccion = direcc.getSelectionModel().getSelectedItem().toString();
        String s = "Dirección: " + direcc.getSelectionModel().getSelectedItem().toString();
        LabelDireccion.setText(s);
        direcc.setVisible(false);
        String a = "Numero de personas: " + TextPersonas.getText();
        labelPersonas.setText(a);
        TextPersonas.setVisible(false);
        btnDirec.setVisible(true);
        numPersonas = Integer.parseInt(TextPersonas.getText());
        Sillas = sil.ubicar(numPersonas);
        
        Personas = p.ubicar(null, numPersonas, direccion, Sillas);
        for (int i = 0; i < numPersonas; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController2.class.getName()).log(Level.SEVERE, null, ex);
            }
            PrincipalPane.getChildren().add(Personas.get(i).getImage());
        }
    
        for (int i = 0; i < numPersonas - 1; i++) {
            PrincipalPane.getChildren().add(Sillas.get(i).getImage());
        }
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(17), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Integer[] imgs = new Integer[20];
                for (int i = 0; i < numPersonas; i++) {
                    PrincipalPane.getChildren().remove(Personas.get(i).getImage());
                    imgs[i] = Personas.get(i).getNumeroImagen();
                }
                for (int i = 0; i < numPersonas - 1; i++) {
                    PrincipalPane.getChildren().remove(Sillas.get(i).getImage());
                }

                if (direccion.equals("Horario")) {
                    direccion = "AntiHorario";
                } else {
                    direccion = "Horario";
                }

                numPersonas = numPersonas - 1;
                Sillas = sil.ubicar(numPersonas);
                System.out.println("imgs : " + imgs[0] + "  " + imgs[1] + "  " + imgs[2]);
                Personas = p.ubicar(imgs, numPersonas, direccion, Sillas);
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

            }
        }));
        timeline.setCycleCount(numPersonas - 2);
        timeline.play();
        mus.getSound().play();
        lblMusica.setText("ON");
        
        
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
    private void btnCambiarDir(ActionEvent event) {
        
    }
    
    private class HiloMovimiento extends Thread {

        Persona per;
        int numeroPersonas;

        @Override
        public void run() {
            System.out.println("runn hilo");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException | IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
            Platform.runLater(() -> {
                try {
                    Path p = per.createPath(per, numeroPersonas);
                    PathTransition trans = new PathTransition(javafx.util.Duration.millis(10000), p, per.getImage());
                    trans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                    trans.setCycleCount(5);
                    trans.setDelay(Duration.seconds(2));
                    trans.setRate(1);
                    trans.playFrom(Duration.seconds(4));
                    System.out.println("ejecutando");
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                }
            });

        }

        public void obtenerPersona(Persona per, int numeroPersonas) {
            this.per = per;
            this.numeroPersonas = numeroPersonas;
        }
    }
}
