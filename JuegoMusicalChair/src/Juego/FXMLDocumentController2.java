/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Sonido.Musica;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Desarrollo
 */
public class FXMLDocumentController2 implements Initializable {

    @FXML
    private Label labelPersonas;
    @FXML
    private ChoiceBox<?> comboBox;
    @FXML
    private TextField TextPersonas;
    @FXML
    private Label LabelDireccion;
    @FXML
    private Label lblMusica;
    @FXML
    private Button btnEject;
    Musica mus = new Musica();
    @FXML
    private Button btnMusica;
    @FXML
    private ImageView imgP1;
    @FXML
    private ImageView imgP2;
    @FXML
    private ImageView imgP3;
    @FXML
    private ImageView imgS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnEject.setVisible(false);
        
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
    }

    
}
