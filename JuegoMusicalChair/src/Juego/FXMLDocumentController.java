/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Sonido.Musica;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Desarrollo
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Button button;
    @FXML
    private Label label;
    Musica mus = new Musica();
    private Stage stage;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root2 = FXMLLoader.load(getClass().getResource("Juego.fxml"));
        stage = new Stage();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.setTitle("Juego de las sillas");
        stage.show();
        
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    

    
}
