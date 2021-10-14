package sample.GestionOffre.models;


import java.sql.Date;

public class Offre {
    private int idoffre;
    private int id_reservation;
    private Date date_validite;
    private int taux_de_remise;
    private String description;
    private String Path_image;
    private String Path_video;
    private String Titre;


    public Offre(int idoffre, int id_reservation, Date date_validite, int taux_de_remise, String description, String path_image, String path_video, String titre) {
        this.idoffre = idoffre;
        this.id_reservation = id_reservation;
        this.date_validite = date_validite;
        this.taux_de_remise = taux_de_remise;
        this.description = description;
        Path_image = path_image;
        Path_video = path_video;
        Titre = titre;
    }

    public int getIdoffre() {
        return idoffre;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public Date getDate_validite() {
        return date_validite;
    }


    public int getTaux_de_remise() {
        return taux_de_remise;
    }



    public String getDescription() {
        return description;
    }


    public String getTitre() {
        return Titre;
    }

    public String getPath_image() {
        return Path_image;
    }



    public String getPath_video() {
        return Path_video;
    }



    @Override
    public String toString() {
        return "Offre{" +
                "idoffre=" + idoffre +
                ", id_reservation=" + id_reservation +
                ", date_validite=" + date_validite +
                ", taux_de_remise=" + taux_de_remise +
                ", description='" + description + '\'' +
                ", Path_image='" + Path_image + '\'' +
                ", Path_video='" + Path_video + '\'' +
                '}';
    }
}
