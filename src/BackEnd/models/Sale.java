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
public class Sale {

    private int id;
    private int idFacture;
    private int idProduct;
    private int idPurchase;
    private String Product;
    private double quantity;
    private double quantityRetour;
    private double PricePurchase;
    private String PricePurchaseS;
    private double PriceTotal;
    private String PriceTotalS;
    private double price_sale;
    private String price_saleS;
    private String benefitSaleS;
    private Double benefitSale;

    public Sale() {
    }

    public Sale(int id) {
        this.id = id;
    }

    public Sale(int id, int idFacture, int idProduct, int idPurchase, String Product, double quantity, int quantityRetour, double PricePurchase, String PricePurchaseS, double PriceTotal, String PriceTotalS, double price_sale, String price_saleS, String benefitSaleS, Double benefitSale) {
        this.id = id;
        this.idFacture = idFacture;
        this.idProduct = idProduct;
        this.idPurchase = idPurchase;
        this.Product = Product;
        this.quantity = quantity;
        this.quantityRetour = quantityRetour;
        this.PricePurchase = PricePurchase;
        this.PricePurchaseS = PricePurchaseS;
        this.PriceTotal = PriceTotal;
        this.PriceTotalS = PriceTotalS;
        this.price_sale = price_sale;
        this.price_saleS = price_saleS;
        this.benefitSaleS = benefitSaleS;
        this.benefitSale = benefitSale;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public double getQuantityRetour() {
        return quantityRetour;
    }

    public void setQuantityRetour(double quantityRetour) {
        this.quantityRetour = quantityRetour;
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

    public void setPrice_sale(double price_sale) {
        this.price_sale = price_sale;
    }

    public void setPrice_saleS(String price_saleS) {
        this.price_saleS = price_saleS;
    }

    public void setBenefitSaleS(String benefitSaleS) {
        this.benefitSaleS = benefitSaleS;
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

    public double getPrice_sale() {
        return price_sale;
    }

    public String getPrice_saleS() {
        return price_saleS;
    }

    public String getBenefitSaleS() {
        return benefitSaleS;
    }

    public Double getBenefitSale() {
        return benefitSale;
    }

    public void setBenefitSale(Double benefitSale) {
        this.benefitSale = benefitSale;
    }

    public String getObject() {
        return " Sale { " + this.id + " " + " " + this.Product + " " + this.PricePurchaseS + " " + this.price_saleS + " "
                + " " + this.quantity + " " + this.PriceTotalS + this.benefitSaleS + " } ";
    }
}
