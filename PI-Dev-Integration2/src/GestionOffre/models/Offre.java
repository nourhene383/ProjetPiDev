/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionOffre.models;

import java.sql.Date;
import java.util.Comparator;

/**
 *
 * @author asus
 */
public class Offre implements Comparator<Offre> {
    private int id;
    private String titre;
    private Date date;
    private String description;

    public Offre(int id) {
        this.id = id;
    }

    public Offre(String titre) {
        this.titre = titre;
    }

    public Offre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Offre(int id, String titre, Date date, String description) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.description = description;
    }

    public Offre(String titre, Date date, String description) {
        this.titre = titre;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "titre=" + titre+ ", date=" + date + ", description=" + description+ ", id=" +id  ;
    }
   
 
    public int compare(Offre a, Offre b) {
        return a.getDate().compareTo(b.getDate());
    } 
}
