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
public class Exp {

    private int id;
    private String type;
    private double montent;
    private String montentS;
    private String date;

    public Exp(int id, String type, double montent, String montentS, String date) {
        this.id = id;
        this.type = type;
        this.montent = montent;
        this.montentS = montentS;
        this.date = date;
    }

    

    public Exp(int id) {
        this.id = id;
    }

    public Exp() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMontent() {
        return montent;
    }

    public void setMontent(double montent) {
        this.montent = montent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMontentS() {
        return montentS;
    }

    public void setMontentS(String montentS) {
        this.montentS = montentS;
    }

    

}
