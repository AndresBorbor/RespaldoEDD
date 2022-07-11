/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Album;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yoser
 */
public class VentanaFolderController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private Button btnIngresar;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    @FXML
    private void btnPhoto(ActionEvent event){
        try {
            String nombreAlbum = txtName.getText();
            ListaArreglo<Album> lista = App.leerLista();
            boolean muletilla = false;
            int i = 0;
            while(i<lista.size()){
                if( nombreAlbum.equals(lista.get(i).getNombre())){
                    muletilla = true;
                    break;
                }
                i++;
            }
            
            if(muletilla){
                App.mostrarAlerta(Alert.AlertType.WARNING, "NOMBRE DE ALBUM YA EXISTENTE", "Ingrese otro nombre para el album");
            }else{
                if(nombreAlbum.replaceAll(" ", "").length() != 0){
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Photo.fxml"));
                    Parent root = fxmlLoader.load();
                    PhotoController jc = fxmlLoader.getController();
                    jc.setNombreLabel(nombreAlbum);
                    App.scene.setRoot(root); 
                }else{
                    App.mostrarAlerta(Alert.AlertType.WARNING, "NOMBRE DE ALBUM NO VALIDO", "Ingrese un nombre para el album");
                }
            }   
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btnBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PrimeraVista.fxml"));
            Parent root = fxmlLoader.load();
            PrimeraVistaController jc = fxmlLoader.getController();
            App.scene.setRoot(root);

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

}
