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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Desarrollo
 */
public class FXMLDocumentController2 implements Initializable {

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
     
    Persona p = new Persona();
    Silla sil = new Silla();
    Musica mus = new Musica();
    @FXML
    private Circle circulo;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnEject.setVisible(false);
        btnSigNivel.setVisible(false);
        ObservableList<String> list = FXCollections.observableArrayList("Horario","AntiHorario");
        direcc.setItems(list);
        direcc.setValue("Horario");
        TextPersonas.setText(null);
    }    

    @FXML
    private void musicaBtnAccion(ActionEvent event) {
        if(lblMusica.getText().equals("OFF")){
            mus.getSound().play();
            lblMusica.setText("ON");
        }else{
            mus.getSound().stop();
            lblMusica.setText("OFF");
        }
        
    }

    @FXML
    private void btnEjecutarAccion(ActionEvent event) {
        btnEject.setVisible(false);
        String s = "Dirección: "+direcc.getSelectionModel().getSelectedItem().toString();
        LabelDireccion.setText(s);
        direcc.setVisible(false);
        String a = "Numero de personas: "+TextPersonas.getText();
        labelPersonas.setText(a);
        TextPersonas.setVisible(false);
      
        int numPersonas = Integer.parseInt(TextPersonas.getText());
        DoubleCircularLinkedList<Persona> Personas = new DoubleCircularLinkedList<>();
        for(int i=0;i<numPersonas;i++){
            Personas.addLast(p.obtImageRmd());
            PrincipalPane.getChildren().add(Personas.get(i).getImage());
        }
        
        DoubleCircularLinkedList<Silla> Sillas = new DoubleCircularLinkedList<>();
        for(int i=0;i<numPersonas-1;i++){
            Sillas.addLast(sil.obtImgS());
            sil.ubicar(Sillas.get(i).getImage());
            PrincipalPane.getChildren().add(Sillas.get(i).getImage());
        }
        
        
    }
    
    @FXML
    public void obtDir(ActionEvent event){
        if(TextPersonas.getText()!=null && direcc.getValue()!=null){
            btnEject.setVisible(true);
            circulo.setVisible(false);
        }
        direcc.setUserData(direcc.getValue());
       
    }
    @FXML
    private void confirmar(ActionEvent event) {
        if(TextPersonas.getText()!=null && direcc.getValue()!=null){
            btnEject.setVisible(true);
            circulo.setVisible(false);
        }
    }

   
}
