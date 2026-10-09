package com.techlab.articulo.model;

public class ArticuloBebible extends Articulo {

    private int cantidadMl;
    
    
    public ArticuloBebible(int codigo, String nombre, double precio, Categoria categoria, int cantidadMl) {
        super(codigo, nombre, precio, categoria);
        this.cantidadMl = cantidadMl;
    }
    
    public int getCantidadMl() {
        return cantidadMl;
    } 

    public void setCantidadMl(int cantidadMl) {
        this.cantidadMl = cantidadMl;
    } 

    @Override
    public String getTipoArticulo() {
        return "Tipo de artículo: Bebible";
    }

    @Override
    public String getInformacion() {
        return "Cantidad en mililitros: " + cantidadMl;
    }

    @Override
    public String toString() {
        return super.toString() + ", cantidadMl=" + cantidadMl + "]";
    }

}
