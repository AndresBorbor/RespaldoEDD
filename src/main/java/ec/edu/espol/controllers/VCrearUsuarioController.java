/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.proyecto1p_edd_grupo3.App;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yoser
 */
public class VCrearUsuarioController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPasword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnIngresar(ActionEvent event) {
        String user = txtUser.getText();
        String passWord = txtPasword.getText();
        VCrearUsuarioController.escribirUser(user, passWord);
        
    }
    
    public static void escribirUser(String User, String password){
        try(BufferedWriter br = new BufferedWriter(new FileWriter("src/archivos/Usuarios.txt"))){
            br.write(User+"|");
            br.write(password);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
