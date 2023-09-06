/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.models;

/**
 *
 * @author kadri
 */
public class Purchase {

    private int id;
    private int idFacture;
    private int idProduct;
    private String Product;
    private String code;
    private double quantity;
    private double quantityRest;
    private double PricePurchase;
    private String PricePurchaseS;
    private double PriceTotal;
    private String PriceTotalS;
    private double priceSale;
    private String priceSaleS;

    @Override
    public String toString() {
        return this.getPricePurchaseS();
    }
    
    public Purchase() {
    }

    public Purchase(int id) {
        this.id = id;
    }

    public Purchase(int id, int idFacture, int idProduct, String Product, String code, double quantity, double quantityRest, double PricePurchase, String PricePurchaseS, double PriceTotal, String PriceTotalS, double priceSale, String priceSaleS) {
        this.id = id;
        this.idFacture = idFacture;
        this.idProduct = idProduct;
        this.Product = Product;
        this.code = code;
        this.quantity = quantity;
        this.quantityRest = quantityRest;
        this.PricePurchase = PricePurchase;
        this.PricePurchaseS = PricePurchaseS;
        this.PriceTotal = PriceTotal;
        this.PriceTotalS = PriceTotalS;
        this.priceSale = priceSale;
        this.priceSaleS = priceSaleS;
    }
   
    public void setId(int id) {
        this.id = id;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setProduct(String Product) {
        this.Product = Product;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setPricePurchase(double PricePurchase) {
        this.PricePurchase = PricePurchase;
    }

    public void setPricePurchaseS(String PricePurchaseS) {
        this.PricePurchaseS = PricePurchaseS;
    }

    public void setPriceTotal(double PriceTotal) {
        this.PriceTotal = PriceTotal;
    }

    public void setPriceTotalS(String PriceTotalS) {
        this.PriceTotalS = PriceTotalS;
    }

    public int getId() {
        return id;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getProduct() {
        return Product;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPricePurchase() {
        return PricePurchase;
    }

    public String getPricePurchaseS() {
        return PricePurchaseS;
    }

    public double getPriceTotal() {
        return PriceTotal;
    }

    public String getPriceTotalS() {
        return PriceTotalS;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getQuantityRest() {
        return quantityRest;
    }

    public void setQuantityRest(double quantityRest) {
        this.quantityRest = quantityRest;
    }

    public double getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(double priceSale) {
        this.priceSale = priceSale;
    }

    public String getPriceSaleS() {
        return priceSaleS;
    }

    public void setPriceSaleS(String priceSaleS) {
        this.priceSaleS = priceSaleS;
    }
    
    
    
     public String getObject() {
        return " Purchase { " + this.id + " " + " " + this.Product + " " + this.PricePurchaseS  + " "
                + " " + this.quantity + " " + this.quantityRest+ " " + this.priceSaleS  + " " + this.PriceTotalS +" } ";
    }

    
    

}
