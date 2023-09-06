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
public class Client {

    private int id;
    private String name;
    private String address;
    private String phone;
    private double totaleDebt;
    private String totaleDebtS;
    private double maxDebt;
    private String maxDebtS;
    
    

    public Client() {
    }

    public Client(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getName() + "  " + this.getAddress();
    }
    
    public Client(int id, String name, String address, String phone, double totaleDebt, String totaleDebtS, double maxDebt, String maxDebtS) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.totaleDebt = totaleDebt;
        this.totaleDebtS = totaleDebtS;
        this.maxDebt = maxDebt;
        this.maxDebtS = maxDebtS;
    }

    public double getMaxDebt() {
        return maxDebt;
    }

    public String getMaxDebtS() {
        return maxDebtS;
    }

    public void setMaxDebt(double maxDebt) {
        this.maxDebt = maxDebt;
    }

    public void setMaxDebtS(String maxDebtS) {
        this.maxDebtS = maxDebtS;
    }

    public void setTotaleDebtS(String totaleDebtS) {
        this.totaleDebtS = totaleDebtS;
    }

    public String getTotaleDebtS() {
        return totaleDebtS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTotaleDebt() {
        return totaleDebt;
    }

    public void setTotaleDebt(double totaleDebt) {
        this.totaleDebt = totaleDebt;
    }

    public String getObject() {
        return "Client { " + this.id + " " + this.name + " " + this.phone + " " + this.address + " " + this.totaleDebtS + "  } ";
    }

}
