package es.fplumara.dam.ad.p05.model;
/**
 * Una zapatilla del inventario: un modelo en una talla concreta.
 */
public class Zapatilla {
    private int codigo;
    private String modelo;
    private String marca;
    private String categoria;
    private double talla;
    private double precio;
    private int stock;
    public Zapatilla() {
    }
    public Zapatilla(int codigo, String modelo, String marca, String categoria,
                     double talla, double precio, int stock) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.marca = marca;
        this.categoria = categoria;
        this.talla = talla;
        this.precio = precio;
        this.stock = stock;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public double getTalla() {
        return talla;
    }
    public void setTalla(double talla) {
        this.talla = talla;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    @Override
    public String toString() {
        return codigo + " - " + modelo + " (" + marca + ", " + categoria + ") talla "
                + talla
                + " - " + precio + " EUR - stock " + stock;
    }
}
