/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Album;
import ec.edu.espol.model.Fotografia;
import ec.edu.espol.model.Persona;
import ec.edu.espol.proyecto1p_edd_grupo3.App;
import ec.edu.espol.util.EndiabladaLinkedList;
import ec.edu.espol.util.ListaArreglo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Yoser
 */
public class PhotoController implements Initializable {

    @FXML
    private TextField txtLugar;
    @FXML
    private TextField txtPersonas;
    @FXML
    private Label lblLugar;
    @FXML
    private Label lblPersonas;
    @FXML
    private Label lblAlbum;
    @FXML
    private Label lblNombreAlbum;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnCrearAlbum;
    @FXML
    private Button btnIngresarFoto;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Label lblDesc;
    @FXML
    private Label lblFecha;
    @FXML
    private ImageView imgPreView;

    //Variblae generadora de codigos distintos para cada foto
    private static int codigoFoto;
    private EndiabladaLinkedList<Fotografia> ListaTempFotos;
    private ListaArreglo<ListaArreglo<Path>> listaTempPaths;
    //Variables path para copiar la imagen
    private Path from;
    private Path to;
    private String imageCod;
    @FXML
    private Button btnCargarFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListaTempFotos = new EndiabladaLinkedList<Fotografia>();
        listaTempPaths = new ListaArreglo<ListaArreglo<Path>>();
    }

    public void setNombreLabel(String nombreAlbum) {
        this.lblNombreAlbum.setText(nombreAlbum);
    }

    @FXML
    private void btnPhoto(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File imgFile = fileChooser.showOpenDialog(null);

        if (imgFile != null) {

            Image image = new Image("file:" + imgFile.getAbsolutePath());
            
            from = Paths.get(imgFile.toURI());
            imageCod = image.toString();
            
            imgPreView.setImage(image);
        }
    }

    @FXML
    public void cargarImagen(ActionEvent event) throws IOException {
        
        to = Paths.get("src/archivos/" + lblNombreAlbum.getText() + "/" + imageCod+codigoFoto + ".jpg");
        ListaArreglo<Path> listaTemp = new ListaArreglo<Path>();
        listaTemp.addLast(from);
        listaTemp.addLast(to);
        listaTempPaths.addLast(listaTemp);
        codigoFoto++;
        
        LocalDate fecha = datePicker.getValue();

        String descripcion = txtDescripcion.getText();
        Fotografia f;
        String lugar = txtLugar.getText();
        ListaArreglo<Persona> lPersonas = new ListaArreglo<Persona>();

        String nombresPersonas = txtPersonas.getText();
        //Validacion de todos los campos necesarios completos
        if (fecha != null && fecha.toString().replaceAll(" ", "").length() != 0 && fecha instanceof LocalDate && descripcion.replaceAll(" ", "").length() != 0 && lugar.replaceAll(" ", "").length() != 0 && imgPreView.getImage() != null) {

            if (nombresPersonas.contains(", ")) {
                String[] nPersonas = nombresPersonas.split(", ");
                for (String nombre : nPersonas) {
                    Persona p = new Persona(nombre);
                    lPersonas.addLast(p);
                }
                f = new Fotografia(lugar, lPersonas, descripcion, fecha);
            } else if (nombresPersonas.replaceAll(" ", "").length() != 0) {
                lPersonas.addLast(new Persona(nombresPersonas));
                f = new Fotografia(lugar, lPersonas, descripcion, fecha);
            } else {

                f = new Fotografia(lugar, lPersonas, descripcion, fecha);

            }
            //Añadir ruta al atributo RUTA de la fotografia
            f.setRuta(to.toString());
            ListaTempFotos.addLast(f);
            App.mostrarAlerta(Alert.AlertType.CONFIRMATION, "Resultado", "Imagen añadida con éxito");
            
        } else {
            App.mostrarAlerta(Alert.AlertType.WARNING, "Datos Incompletos", "Recuerde llenar todos los campos");
        }
    }

    @FXML
    public void mensajeFormato(ActionEvent event) {
        String header = "Formato para agregar nombres:";
        String contenido = "nombre1, nombre2, nombre3, ...";
        App.mostrarAlerta(Alert.AlertType.INFORMATION, header, contenido);
    }

    @FXML
    private void btnBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("VentanaFolder.fxml"));
            Parent root = fxmlLoader.load();
            VentanaFolderController jc = fxmlLoader.getController();
            codigoFoto = 0;
            App.scene.setRoot(root);

        } catch (IOException ex) {
            ex.getMessage();
        }

    }
    
    @FXML
    private void crearAlbum(ActionEvent event) throws IOException {

        //Creacion del album y añadir la foto a su lista de fotos
        
        if(!listaTempPaths.isEmpty() && !listaTempPaths.getLast().getLast().equals(to)){
            App.mostrarAlerta(Alert.AlertType.WARNING, "Foto inexistente", "No se ha agregado la foto");
        }else{
        
            if (!ListaTempFotos.isEmpty()) {

                Album alb = new Album(lblNombreAlbum.getText());
                File folder = new File("src/archivos/" + alb.getNombre());
                if (!folder.exists()) {
                    folder.mkdir();
                }

                for(int i = 0; i<listaTempPaths.size(); i++){
                    ListaArreglo<Path> lPath = listaTempPaths.get(i);
                    Files.copy(lPath.get(0), lPath.get(1));
                }


                
                alb.setListaFotos(ListaTempFotos);
                

                
                ListaArreglo<Album> listaTemp = App.leerLista();
                listaTemp.addLast(alb);
                App.escribirLista(listaTemp);
                App.mostrarAlerta(Alert.AlertType.INFORMATION, "Album creado", "El álbum ha sido creado con éxito");

                //Resetea el contador al pulsar "Crear Album", para comenzar desde cero cuando se cree otro album
                codigoFoto = 0;

                //Al aplastar crearAlbun lo redirecciona a la pantalla principal
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PrimeraVista.fxml"));
                    Parent root = fxmlLoader.load();
                    PrimeraVistaController jc = fxmlLoader.getController();
                    App.scene.setRoot(root);

                } catch (IOException ex) {
                    ex.getMessage();
                }

            } else {
                App.mostrarAlerta(Alert.AlertType.WARNING, "Datos Incompletos", "Recuerde llenar todos los campos con los datos correctos");
            }
        }
    }
}
