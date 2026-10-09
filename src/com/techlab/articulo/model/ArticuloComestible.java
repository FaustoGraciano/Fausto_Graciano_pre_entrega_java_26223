package com.techlab.articulo.model;

public class ArticuloComestible extends Articulo {
    private int cantidadGr;


    public ArticuloComestible(int codigo, String nombre, double precio, Categoria categoria, int cantidadGr) {
        super(codigo, nombre, precio, categoria);
        this.cantidadGr = cantidadGr;
    }
    
    public int getCantidadGr() {
        return cantidadGr;
    }

    public void setCantidadGr(int cantidadGr) {
        this.cantidadGr = cantidadGr;
    }

    @Override
    public String getTipoArticulo() {
        return "Tipo de artículo: Comestible";
    }

    @Override
    public String getInformacion() {
        return "Cantidad en gramos: " + cantidadGr;
    }

    @Override
    public String toString() {
        return super.toString() + ", cantidadGr=" + cantidadGr + "]";
    }
}
