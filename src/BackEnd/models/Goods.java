/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.models;

public class Goods {

    private int id;
    private int fav;

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }
    private String name;
    private String imgProduct;

    public Goods(int id,int fav, String name, String imgProduct, double quantity, int quantityAlert, double countPurchase, double countSale) {
        this.id = id;
        this.fav = fav;
        this.name = name;
        this.imgProduct = imgProduct;
        this.quantity = quantity;
        this.quantityAlert = quantityAlert;
        this.countPurchase = countPurchase;
        this.countSale = countSale;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }
    private double quantity;
    private int quantityAlert;
    private double countPurchase;
    private double countSale;

    public Goods() {
    }

    public Goods(int id) {
        this.id = id;
    }

    public int getQuantityAlert() {
        return quantityAlert;
    }

    public void setQuantityAlert(int quantityAlert) {
        this.quantityAlert = quantityAlert;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getCountPurchase() {
        return countPurchase;
    }

    public double getCountSale() {
        return countSale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setCountPurchase(double countPurchase) {
        this.countPurchase = countPurchase;
    }

    public void setCountSale(double countSale) {
        this.countSale = countSale;
    }

    public String getObject() {
        return "Product { " + this.id + " " + this.name + " " + this.quantity + " " + this.quantityAlert + " "
                + this.countPurchase + " " + this.countSale + " } ";
    }

}
