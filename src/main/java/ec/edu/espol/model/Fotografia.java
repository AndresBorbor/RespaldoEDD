/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import ec.edu.espol.util.ListaArreglo;
import java.io.Serializable;
import java.time.LocalDate;


/**
 *
 * @author ROBERTO
 */


public class Fotografia implements Serializable{
    private String ubicacion;
    private ListaArreglo<Persona> listaPersonas;
    private String RUTA;
    private String Descripcion;
    private LocalDate Fecha;
    private String Alb;
    
    
    //crear variable private Lista  Personas
    
    
    public Fotografia(String ub, ListaArreglo<Persona> lp, String capt, LocalDate fecha){
        this.ubicacion = ub;
        this.listaPersonas = lp;
        this.Fecha = fecha;
        this.Descripcion = capt;
        
        
    }
    
    public Fotografia(String ub, String capt, LocalDate fecha, String alb){
        this.ubicacion = ub;
        this.listaPersonas = new ListaArreglo<Persona>();
        this.Fecha = fecha;
        this.Descripcion = capt;
        this.Alb = Alb;
    }
    
    //Constructor temporal
    
    //agregar personas a las fotos
    public void agregarPersonas(Persona p){
        listaPersonas.addLast(p);
    }
    
    //Eliminar personas de las fotos
    public void eliminarPersonas(Persona p){
        int i = listaPersonas.indexOf(p);
        listaPersonas.remove(i);
    }
    
    
    public void setRuta(String ruta){
        this.RUTA = ruta;
    }
    
    public String getRuta(){
        return this.RUTA;
    }
    
    public String getDescripcion(){
        return this.Descripcion;
    }
    
    public void setDescripcion(String desc){
        this.Descripcion = desc;
    }
    
    public String getLugar(){
        return this.ubicacion;
    }
    
    public void setLugar(String ubicacion){
        this.ubicacion = ubicacion;
    }
    
    public LocalDate getFecha(){
        return this.Fecha;
    }
    
    public void setFecha(LocalDate fecha){
        this.Fecha = fecha;
    }
    
    public ListaArreglo<Persona> getListaPersonas(){
        return this.listaPersonas;
    }
    
    public void setListaPersonas(ListaArreglo<Persona> listaPersonas){
        this.listaPersonas = listaPersonas;
    }
    
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(this == obj) return true;
        if(this.getClass() != obj.getClass()) return false;
        Fotografia f = (Fotografia) obj;
        return this.getRuta().equals(f.getRuta());
    }
    
    public String toString(){
        return this.Descripcion;
    }
    //Editar la información de las personas
    /*Este método se debe usar para el photo controller para que al dar click en alguna foto, se muestre la lista
    de personas y luego al darle click a una persona, editar su información, en este caso, su nombre
    
    public void editarPersonas(MouseEvent click){
        listaPersonas.get().setNombre();
    }*/
}
