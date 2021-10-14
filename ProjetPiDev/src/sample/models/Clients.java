package sample.models;

import java.util.Date;

public class Clients {
    private String idC;
    private String lastname;
    private String firstname;
    private int numtel;
    private int cin;
    private Date date;

    public Clients(String idC, String lastname, String firstname, int numtel, int cin, Date date) {
        this.idC = idC;
        this.lastname = lastname;
        this.firstname = firstname;
        this.numtel = numtel;
        this.cin = cin;
        this.date = date;
    }

    public String getIdC() {
        return idC;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getNumtel() {
        return numtel;
    }

    public int getCin() {
        return cin;
    }

    public Date getDate() {
        return date;
    }
}
