/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.models;


public class Facture {

    private int id;
    private String code;
    private double TotalAmount;
    private String TotalAmountS;
    private double payment;
    private String paymentS;
    private int idProvider;
    private String provider;
    private String date;
    private double remise;
    private String remiseS;

    public Facture() {
    }

    public Facture(int id, String code, double TotalAmount, String TotalAmountS, double payment, String paymentS, int idProvider, String provider, String date, double remise, String remiseS) {
        this.id = id;
        this.code = code;
        this.TotalAmount = TotalAmount;
        this.TotalAmountS = TotalAmountS;
        this.payment = payment;
        this.paymentS = paymentS;
        this.idProvider = idProvider;
        this.provider = provider;
        this.date = date;
        this.remise = remise;
        this.remiseS = remiseS;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   
    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public void setTotalAmountS(String TotalAmountS) {
        this.TotalAmountS = TotalAmountS;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public void setPaymentS(String paymentS) {
        this.paymentS = paymentS;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }


    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public String getTotalAmountS() {
        return TotalAmountS;
    }

    public double getPayment() {
        return payment;
    }

    public String getPaymentS() {
        return paymentS;
    }

    public int getIdProvider() {
        return idProvider;
    }

    public String getProvider() {
        return provider;
    }


    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public String getRemiseS() {
        return remiseS;
    }

    public void setRemiseS(String remiseS) {
        this.remiseS = remiseS;
    }
    
     public String getObject() {
        return "Facture purchase { " + this.id + " " + " " + this.code + " " + this.TotalAmountS + " " + this.paymentS + " "
                + this.remiseS + " "
                +  " " + this.provider + " " + this.date + " } ";
    }
     
     

    
}
