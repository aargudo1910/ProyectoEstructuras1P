/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    private Button btnSig;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void initialize(MouseEvent event) {
    }

    @FXML
    private void initialize(ActionEvent event) {
    }
    
}
