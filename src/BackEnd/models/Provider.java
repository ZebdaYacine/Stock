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
public class Provider {

    private int id;
    private String name;
    private String address;
    private String phone;
    private double debt;
    private String debtS;

    public Provider() {
    }

    public Provider(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getName() + "  " + this.address;
    }

    public Provider(String name, String address, String phone, double debt) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.debt = debt;
    }

    public Provider(int id, String name, String address, String phone, double debt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.debt = debt;
        this.debtS = debtS;
    }

    public Provider(int id, String name, String address, String phone, double debt, String debtS) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.debt = debt;
        this.debtS = debtS;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public void setDebtS(String debtS) {
        this.debtS = debtS;
    }

    public double getDebt() {
        return debt;
    }

    public String getDebtS() {
        return debtS;
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

    public String getObject() {
        return "Provider { " + this.id + " " + this.name + " " + this.phone + " " + this.address + " " + this.debtS + "  } ";
    }

}
