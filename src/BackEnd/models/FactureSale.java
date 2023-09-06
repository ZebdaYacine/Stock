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
public class FactureSale {

    private int id;
    private String code;
    private double TotalAmount;
    private String TotalAmountS;
    private double payment;
    private String paymentS;
    private double remise;
    private String remiseS;
    private int idClient;
    private int idUser;
    private String user;
    private String Client;
    private String date;

    public FactureSale() {
    }

    public FactureSale(int id, String code, double TotalAmount, String TotalAmountS, double payment, String paymentS, double remise, String remiseS, int idClient, int idUser, String user, String Client, String date) {
        this.id = id;
        this.code = code;
        this.TotalAmount = TotalAmount;
        this.TotalAmountS = TotalAmountS;
        this.payment = payment;
        this.paymentS = paymentS;
        this.remise = remise;
        this.remiseS = remiseS;
        this.idClient = idClient;
        this.idUser = idUser;
        this.user = user;
        this.Client = Client;
        this.date = date;
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

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setClient(String Client) {
        this.Client = Client;
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

    public int getIdClient() {
        return idClient;
    }

    public String getClient() {
        return Client;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
        return "Facture Sale { " + this.id + " " + " " + this.code + " " + this.TotalAmountS + " " + this.paymentS + " "
                + this.remiseS + " "
                + this.Client + " " + this.user + " " + this.date + " } ";
    }

}
