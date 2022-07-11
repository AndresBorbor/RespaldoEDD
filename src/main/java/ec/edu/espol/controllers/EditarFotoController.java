/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Album;
import ec.edu.espol.model.Fotografia;
import ec.edu.espol.model.Persona;
import ec.edu.espol.proyecto1p_edd_grupo3.App;
import ec.edu.espol.util.ListaArreglo;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andrés
 */
public class EditarFotoController implements Initializable {

    @FXML
    private Label lblAlbum;
    @FXML
    private Label lblDescripcion;
    @FXML
    private Label lblLugar;
    @FXML
    private Label lblPersonas;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnEditar;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtLugar;
    @FXML
    private TextField txtPersonas;
    @FXML
    private Label lblNombreAlbum;

    private Fotografia tempFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void editar(ActionEvent event) {

        String personas = txtPersonas.getText();
        String lugar = txtLugar.getText();
        String descripcion = txtDescripcion.getText();
        if (lugar.replaceAll(" ", "").length() != 0 && descripcion.replaceAll(" ", "").length() != 0 && personas.replaceAll(" ", "").length() != 0) {
            personas.replaceAll("\\[", "");
            personas.replaceAll("\\]", "");

            ListaArreglo<Persona> lPersonas = new ListaArreglo<Persona>();
            if (!personas.equals("")) {
                String[] arregloPersonas = personas.split(", ");
                for (String nombre : arregloPersonas) {
                    lPersonas.addLast(new Persona(nombre));
                }
            }
             
            tempFoto.setListaPersonas(lPersonas);
            tempFoto.setDescripcion(descripcion);
            tempFoto.setLugar(lugar);
        }

    }

    @FXML
    private void cancelar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("VentanaEditar.fxml"));
            Parent root = fxmlLoader.load();
            VentanaEditarController jc = fxmlLoader.getController();
            App.scene.setRoot(root);

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public void init(Fotografia fTemp, Album alb) {
        tempFoto = fTemp;
        lblAlbum.setText(alb.getNombre());
        txtPersonas.setText(tempFoto.getListaPersonas().toString());
        txtDescripcion.setText(tempFoto.getDescripcion());
        txtLugar.setText(tempFoto.getLugar());
    }
}
