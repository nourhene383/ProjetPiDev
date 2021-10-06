/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCompteGui;

import GestionCompte.entities.Client;
import GestionCompte.entities.Compte;
import GestionCompte.entities.EncapsulationComtpe;
import GestionCompte.services.ClientCRUD;
import GestionCompte.services.CompteCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author masso
 */
public class SinscrireClientController implements Initializable {

    @FXML
    private TextField in_userName;
    @FXML
    private TextField in_nom;
    @FXML
    private TextField in_prenom;
    @FXML
    private TextField in_password;
    @FXML
    private TextField in_verif;
    @FXML
    private TextField in_age;
    @FXML
    private TextField in_email;
    @FXML
    private TextField in_numTel;
    @FXML
    private PasswordField in_secret;
    @FXML
    private PasswordField in_vsecret;
    @FXML
    private ImageView icon_show;
    @FXML
    private ImageView icon_hide;
    @FXML
    private ImageView icon_vshow;
    @FXML
    private ImageView icon_vhide;
    @FXML
    private Button btn_ajout;
    @FXML
    private Label l_userName;
    @FXML
    private Label l_password;
    @FXML
    private Label l_email;
    @FXML
    private Label l_numTel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        in_userName.setPromptText("Nom d'utilisateur");
        in_nom.setPromptText("Nom");
        in_prenom.setPromptText("Prenom");
        in_password.setPromptText("Mot de passe");
        in_secret.setPromptText("Mot de passe");
        in_verif.setPromptText("Mot de passe");
        in_vsecret.setPromptText("Mot de passe");
        in_age.setPromptText("Age");
        in_email.setPromptText("Adresse email");
        in_numTel.setPromptText("Numero de téléphone");
        
        in_secret.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

               in_password.setText(in_secret.getText());
            }
        });
        
        in_password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

               in_secret.setText(in_password.getText());
            }
        });
        
        in_vsecret.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

               in_verif.setText(in_vsecret.getText());
            }
        });
        
        in_verif.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

               in_vsecret.setText(in_verif.getText());
            }
        });
        
    }

    @FXML
    private void ajouterCompte(ActionEvent event) {
        l_userName.setText("");
        l_password.setText("");
        l_numTel.setText("");
        l_email.setText("");
        if (CompteCRUD.existe(in_userName.getText())) {
            l_userName.setText("Ce nom d'utilisateur exixte déja.");
            l_userName.setTextFill(Color.rgb(210, 39, 30));
        } 
        if (!CompteCRUD.mailIsValid(in_email.getText())) {
            l_email.setText("Cette adresse mail est invalide.");
            l_email.setTextFill(Color.rgb(210, 39, 30));
        } 
        if (!in_password.getText().equals(in_verif.getText())) {
            l_password.setText("Les deux mot de passes ne sont pas identiques.");
            l_password.setTextFill(Color.rgb(210, 39, 30));
        } 
        if(!CompteCRUD.telIsValid(in_numTel.getText())) {
            l_numTel.setText("Ce numéro de téléphone est invalide.");
            l_numTel.setTextFill(Color.rgb(210, 39, 30));
        }
        if(!CompteCRUD.existe(in_userName.getText())&& CompteCRUD.mailIsValid(in_email.getText()) && in_password.getText().equals(in_verif.getText()) && CompteCRUD.telIsValid(in_numTel.getText()))
        {
            ClientCRUD cc = new ClientCRUD();
            Client c = new Client(in_userName.getText(), in_nom.getText(), in_prenom.getText(), Integer.parseInt(in_age.getText()), in_email.getText(), CompteCRUD.encrypt(in_password.getText()), Integer.parseInt(in_numTel.getText()));
            cc.ajouterClient(c);
            JOptionPane.showMessageDialog(null, "Compte ajouté!");
            Compte com=CompteCRUD.rechercherCompte(in_userName.getText());
            EncapsulationComtpe encapsulationCompte= new EncapsulationComtpe(com.getId(), in_userName.getText(), c.getNom(), c.getPrenom(), c.getAge(), c.getAdresseMail(), c.getMotDePasse(),c.getNumTel());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AccueilClient.fxml"));
            try {
                Parent root = loader.load();
                in_userName.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void hide(MouseEvent event) {
        in_secret.setVisible(true);
        in_password.setVisible(false);
        icon_hide.setVisible(false);
        icon_show.setVisible(true);
    }

    @FXML
    private void show(MouseEvent event) {
        in_secret.setVisible(false);
        in_password.setVisible(true);
        icon_hide.setVisible(true);
        icon_show.setVisible(false);
    }

    @FXML
    private void vshow(MouseEvent event) {
        in_vsecret.setVisible(false);
        in_verif.setVisible(true);
        icon_vhide.setVisible(true);
        icon_vshow.setVisible(false);
    }

    @FXML
    private void vhide(MouseEvent event) {
        in_vsecret.setVisible(true);
        in_verif.setVisible(false);
        icon_vhide.setVisible(false);
        icon_vshow.setVisible(true);
    }

}
