/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionOffre.services;

import GestionOffre.models.Offre;
import GestionOffre.tools.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 *
 * @author asus
 */
public class OffreService implements IService<Offre>{
   Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Offre t) {
        try {
            String requete = "INSERT INTO offre (titre,date,description) VALUES ('" + t.getTitre() + "','" + t.getDate() + "',' " + t.getDescription() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println(" offre ajoutée! ");
        } catch (SQLException ex) {
            System.out.println(" erreur d'ajout! ");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Offre t) {
        try {
            String requete = "UPDATE offre SET titre=?, date=?, description=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, t.getId());
            pst.setString(1, t.getTitre());
            pst.setDate(2, (Date) t.getDate());
            pst.setString(3, t.getDescription());
            pst.executeUpdate();
            System.out.println(" Offre Modifiée! ");
        } catch (SQLException ex) {
            System.out.println(" erreur de modification! ");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int t) {
        try {
            String requete = "DELETE FROM offre WHERE   id=?";

            PreparedStatement pst = cnx.prepareStatement(requete);
            System.out.print("t : "+t);
            pst.setInt(1, t);
            pst.executeUpdate();
            System.out.println(" offre supprimé! ");
        } catch (SQLException ex) {
            System.out.println(" erreur de suppression! ");
            System.err.println(ex.getMessage());
        }
    }

    

    @Override
    public List<Offre> afficher() {
        List<Offre> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM offre";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Offre(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }

    public List<Offre> rechercherParDate(String date) throws SQLException {
        List<Offre> list = new ArrayList<>();

        try {

            String requete = "SELECT * FROM offre where date='" + date + "'";

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Offre(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }

    public List<Offre> rechercherParTitre(String titre) throws SQLException {
        List<Offre> list = new ArrayList<>();

        try {

            String requete = "SELECT * FROM offre where titre='" + titre + "'";

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Offre(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }

    

    public List<Offre> trie() {
        List<Offre> list = new ArrayList<>();
        list = afficher();
        Comparator c = Collections.reverseOrder(new Offre());
        Collections.sort(list, c);
        return list;

    }

    public List<String> fetch(String s) {

        List<String> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Offre where  id =?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, s);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(0, (Integer.toString(rs.getInt(1))));
                list.add(1, ( rs.getString(2)));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                list.add(2, ( dateFormat.format(rs.getDate(3))));
               list.add(3, ( rs.getString(4)));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }   
}
