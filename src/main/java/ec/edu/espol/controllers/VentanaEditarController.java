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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ROBERTO
 */
public class VentanaEditarController implements Initializable {

    @FXML
    private Button btnEditar;
    @FXML
    private ComboBox<Album> cmbBoxAlbum;
    @FXML
    private Label lblSeleccionarAlbum;
    private ListaArreglo<Album> listaAlbumes;
    private EndiabladaLinkedList<Fotografia> listaFotos;
    @FXML
    private ScrollPane scrollPaneFotos;
    @FXML
    private Label lblDescFoto;
    @FXML
    private Button btnEliminarFoto;
    @FXML
    private Button btnEliminarAlbum;
    
    private Fotografia fTemp;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listaAlbumes = App.leerLista();
        String filename = "src/archivos/listaAlbum.dat";
        Path p = Paths.get(filename);
        for (int i = 0; i < listaAlbumes.size(); i++) {
            cmbBoxAlbum.getItems().add(listaAlbumes.get(i));
        }
        
        
        
    }

    @FXML
    private void mostrarFotos(ActionEvent event){
        
        TilePane tilePaneResponsive = new TilePane();
        
        tilePaneResponsive.setPrefColumns(3);
        
        tilePaneResponsive.setMaxWidth(360);
        tilePaneResponsive.setHgap(20);
        tilePaneResponsive.setVgap(10);
        Object evt = event.getSource();
        if(evt.equals(cmbBoxAlbum)){
            Album a = (Album)cmbBoxAlbum.getSelectionModel().getSelectedItem();
            
            listaFotos = a.getListaFotos();
            for(int i = 0; i < listaFotos.size(); i++){
                Fotografia f = listaFotos.get(i);
                Image img = new Image("file:"+f.getRuta());
                ImageView imV = new ImageView(img);
                imV.setFitWidth(100);
                imV.setFitHeight(100);
                imV.setOnMouseClicked(ev -> {
                    fTemp = f;
                    lblDescFoto.setText(fTemp.toString());
                    });
                tilePaneResponsive.getChildren().add(imV);
                scrollPaneFotos.setContent(tilePaneResponsive);
            }
        }
    }
    
    
    
    
    @FXML
    private void eliminarAlbum(ActionEvent event){
        listaAlbumes = App.leerLista();
        Album a = (Album)cmbBoxAlbum.getSelectionModel().getSelectedItem();
        if(a!=null ){    
            int indice = listaAlbumes.indexOf(a);
            listaAlbumes.remove(indice);
            App.escribirLista(listaAlbumes);
            App.mostrarAlerta(Alert.AlertType.WARNING, "Remover Album", "El album ha sido eliminado");
            
            btnBack(event);
        }else{
            App.mostrarAlerta(Alert.AlertType.WARNING, "Remover Album", "Elige un album a remover");
        }
        
    }
    
    @FXML
    private void eliminarFoto(ActionEvent event){
        
        listaAlbumes = App.leerLista();
        Album alb = (Album)cmbBoxAlbum.getSelectionModel().getSelectedItem();
        listaFotos = alb.getListaFotos();
        int index = listaAlbumes.indexOf(alb);
        Album a = listaAlbumes.get(index);
        
        if(a!=null && fTemp != null){    
            listaFotos.remove(listaFotos.indexOf(fTemp));
            File f = new File(fTemp.getRuta());
            if(f.exists()){
                a.setListaFotos(listaFotos);
                App.escribirLista(listaAlbumes);
                App.mostrarAlerta(Alert.AlertType.WARNING, "Remover Foto", "La foto ha sido eliminada");
                f.delete();
            }else{
                App.mostrarAlerta(Alert.AlertType.INFORMATION, "Remover Foto", "La foto ya ha sido eliminada, recargue la ventana para observar los cambios");
            }
            
            
            
        }else{
            App.mostrarAlerta(Alert.AlertType.WARNING, "Remover Foto", "Elige una foto a remover");
        }
        
    }
    @FXML
    private void editarFoto(ActionEvent event){
        listaAlbumes = App.leerLista();
        Album alb = (Album)cmbBoxAlbum.getSelectionModel().getSelectedItem();
        int index = listaAlbumes.indexOf(alb);
        Album a = listaAlbumes.get(index);
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditarFoto.fxml"));
            Parent root = fxmlLoader.load();
            EditarFotoController jc = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            jc.init(fTemp, a);
            stage.show();
            stage.close();
            
        }catch (IOException ex) {
            ex.getMessage();
        }catch(Exception ex){
             App.mostrarAlerta(Alert.AlertType.ERROR, "FOTO SIN SELECCIONAR", "Eliga una foto primero");
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
