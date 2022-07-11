/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import ec.edu.espol.util.EndiabladaLinkedList;
import java.io.Serializable;


/**
 *
 * @author PC
 */
public class Album implements Serializable{
    private EndiabladaLinkedList<Fotografia> listaFotos;
    private String nombre;
    
    
    public Album(String nombre) {
        this.listaFotos = new EndiabladaLinkedList<>();
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setListaFotos(EndiabladaLinkedList listaFotos){
        this.listaFotos = listaFotos;
    }
    
    public EndiabladaLinkedList getListaFotos(){
        return this.listaFotos;
    }
    
    //método para remover uan foto del album
    public void eliminar(Fotografia foto){
        int i = listaFotos.indexOf(foto);
        listaFotos.remove(i);
    }
    
    public void agregarFoto(Fotografia foto){
        listaFotos.addLast(foto);
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(this == obj) return true;
        if(this.getClass() != obj.getClass()) return false;
        Album alb = (Album) obj;
        return alb.getNombre().equals(this.getNombre());
    }
    
}
