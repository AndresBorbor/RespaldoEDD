/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto1p_edd_grupo3.App;
import ec.edu.espol.util.ListaArreglo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yoser
 */
public class VentanaLoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnIngresar(ActionEvent event) {
        ListaArreglo<Usuario> lUser = VentanaLoginController.leerLista();

        try {
            for (int i = 0; i < lUser.size(); i++) {
                Usuario un = lUser.get(i);
                if ((un.getUser().equals(txtUsuario.getText()) && un.getPassWord().equals(txtPassword.getText()))) {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PrimeraVista.fxml"));
                    Parent root = fxmlLoader.load();
                    VCrearUsuarioController jc = fxmlLoader.getController();
                    App.scene.setRoot(root);
                    
                }
            }

        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    @FXML
    private void btnCrear(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("VCrearUsuario.fxml"));
            Parent root = fxmlLoader.load();
            VCrearUsuarioController jc = fxmlLoader.getController();
            App.scene.setRoot(root);

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public static ListaArreglo<Usuario> leerLista() {
        ListaArreglo<Usuario> lReturn = new ListaArreglo<>();
        try ( BufferedReader br = new BufferedReader(new FileReader("src/archivos/Usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                String user = datos[0];
                String passWord = datos[1];
                Usuario us = new Usuario(user, passWord);
                lReturn.addLast(us);

            }
        } catch (IOException e) {
            e.getMessage();
        }

        return lReturn;
    }

}
