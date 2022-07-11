/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Album;
import ec.edu.espol.model.Fotografia;
import ec.edu.espol.proyecto1p_edd_grupo3.App;
import ec.edu.espol.util.EndiabladaLinkedList;
import ec.edu.espol.util.ListaArreglo;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
// aqui se pondran los controladores 

/**
 * FXML Controller class
 *
 * @author Yoser
 */
public class PrimeraVistaController implements Initializable {

    @FXML
    private ComboBox<Album> folders;
    @FXML
    private AnchorPane anchorPaneFotos;
    @FXML
    private HBox hBoxFotos;

    private EndiabladaLinkedList<Fotografia> listaFotos;
    @FXML
    private ImageView imgViewFoto;

    private Fotografia f;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListaArreglo<Album> list = App.leerLista();
        
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                folders.getItems().add(list.get(i));
            }
        }

    }

    @FXML
    private void mostrarFotos(ActionEvent event) {
        hBoxFotos.getChildren().clear();
        Object evt = event.getSource();
        if (evt.equals(folders)) {
            Album a = (Album) folders.getSelectionModel().getSelectedItem();
            listaFotos = a.getListaFotos();

            f = listaFotos.getFirst();
            Image img = new Image("file:" + f.getRuta());

            imgViewFoto.setImage(img);
            imgViewFoto.setLayoutX(5);
            imgViewFoto.setLayoutY(5);
            hBoxFotos.getChildren().add(imgViewFoto);

        }
    }

    @FXML
    public void avanzar(ActionEvent event) {
        try {
            f = listaFotos.getSiguiente(listaFotos.getNodo(f));
            Image img = new Image("file:" + f.getRuta());

            imgViewFoto.setImage(img);
        } catch (NullPointerException ex) {

        }
    }

    @FXML
    public void anterior(ActionEvent event) {
        try {
            f = listaFotos.getAnterior(listaFotos.getNodo(f));
            Image img = new Image("file:" + f.getRuta());
            imgViewFoto.setImage(img);
        } catch (NullPointerException ex) {

        }
    }

    @FXML
    private void cambiarV(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("VentanaFolder.fxml"));
            Parent root = fxmlLoader.load();
            VentanaFolderController jc = fxmlLoader.getController();
            App.scene.setRoot(root);

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void cambiarVE(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("VentanaEditar.fxml"));
            Parent root = fxmlLoader.load();
            VentanaEditarController jc = fxmlLoader.getController();
            App.scene.setRoot(root);

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

}
