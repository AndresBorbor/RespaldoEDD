package ec.edu.espol.proyecto1p_edd_grupo3;

import ec.edu.espol.model.Album;
import ec.edu.espol.model.Fotografia;
import ec.edu.espol.model.Persona;
import ec.edu.espol.util.EndiabladaLinkedList;
import ec.edu.espol.util.ListaArreglo;
import ec.edu.espol.util.ListaEnlazada;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.control.Alert;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * JavaFX App
 */
public class App extends Application implements Serializable {

    public static Scene scene;
    //RUTA DE PRUEBA, DEBE CAMBIARSE
    public final static String RUTA = "src/archivos/listaAlbumes.txt";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("PrimeraVista"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void mostrarAlerta(Alert.AlertType tipo, String header, String contenido) {
        Alert al = new Alert(tipo);
        al.setHeaderText(header);
        al.setContentText(contenido);
        al.showAndWait();
    }

    public static ListaArreglo<Album> leerLista() {

        ListaArreglo<Album> listaAlbumes = new ListaArreglo<Album>();
        

        try ( BufferedReader bf = new BufferedReader(new FileReader(RUTA))) {

            String linea;
            Album a = new Album(null);
            while ((linea = bf.readLine()) != null) {
                //formatter para la fecha
                
                String[] datos = linea.split("\\|");
                if(!datos[0].equals(a.getNombre())){
                    a = new Album(datos[0]);
                }
                //nuevo arreglo para datos de las fotos
                
                
                String[] fotoInfo = datos[1].split("\\&");
                
                for (String s : fotoInfo) {
                    String [] datosFotos = s.split(";");
                    
                    //obtencion cada celda de datos de las fotos
                    String ruta = datosFotos[0];
                    String descripcion = datosFotos[1];
                    String lugar = datosFotos[2];
                    String fe = datosFotos[3];
                    String personas = datosFotos[4];
                    personas.replaceAll("\\[", "");
                    personas.replaceAll("\\]", "");
                    ListaArreglo<Persona> lPersonas = new ListaArreglo<Persona>();
                    if(!personas.equals("")){
                        String [] arregloPersonas = personas.split(", ");
                        for(String nombre: arregloPersonas){
                            lPersonas.addLast(new Persona(nombre));
                        }
                    }
                    //convertir string a fecha
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate fecha = LocalDate.parse(fe);
                    //creamos la foto y la agregamos
                    
                    Fotografia f = new Fotografia(lugar, lPersonas, descripcion, fecha);
                    f.setRuta(ruta);
                    a.agregarFoto(f);
                    
                }
                listaAlbumes.addLast(a);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("No pudo leer la lista");
            e.printStackTrace();
        }finally{
            
            return listaAlbumes;
        }
    }

    public static void escribirLista(ListaArreglo<Album> lista) {

        try ( BufferedWriter bWriter = new BufferedWriter(new FileWriter(RUTA))) {
            for (int i = 0; i < lista.size(); i++) {
                Album al = lista.get(i);
                bWriter.write(al.getNombre() + "|");
                for (int j = 0; j < al.getListaFotos().size(); j++) {
                    Fotografia fo = (Fotografia) al.getListaFotos().get(j);

                    bWriter.write(fo.getRuta() + ";");
                    bWriter.write(fo.getDescripcion() + ";");
                    bWriter.write(fo.getLugar() + ";");
                    bWriter.write(fo.getFecha().toString() + ";");
                    bWriter.write(fo.getListaPersonas().toString() + "&");
                }
                bWriter.write("\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se puedo escribir el archivo");
        } catch (IOException e) {
            System.out.println("No se puedo escribir el archivo");
        }
    }

    public static void main(String[] args) {
        launch();

    }

}
