/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionOffre.gui;

import GestionOffre.models.Offre;
import GestionOffre.services.OffreService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutOffreController implements Initializable {

   @FXML
    private TextField tfTitre;
    @FXML
    private DatePicker tfDate;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button Btn;
    @FXML
    private Button btn_annuler;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Annuler_offre(ActionEvent event) {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();

        stage.close();
    }
    
    
    @FXML
    private void back(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficherOffre.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void AjouterOffre(ActionEvent event) throws ParseException {
        OffreService sp = new OffreService();
        LocalDate value1 = tfDate.getValue();

        if (tfTitre.getText().length() == 0) {
            tfTitre.setStyle("-fx-border-color: red ; -fx-border-width:2px;");
            new animatefx.animation.Shake(tfTitre).play();
        } else {
            tfTitre.setStyle(null);
        }

        if (tfDate.getValue() == null) {
            tfDate.setStyle("-fx-border-color: red ; -fx-border-width:2px;");
            new animatefx.animation.Shake(tfDate).play();
        } else {
            tfDate.setStyle(null);
        }
        if (tfDescription.getText().length() == 0) {
            tfDescription.setStyle("-fx-border-color: red ; -fx-border-width:2px;");
            new animatefx.animation.Shake(tfDescription).play();
        } else {
            tfDescription.setStyle(null);
        }

        String s = "";
        if (tfTitre.getText().trim().isEmpty() || tfDate.getValue() == null || tfDescription.getText().trim().isEmpty()) {
          Image img = new Image("images/canc.png");
            Notifications notificationBuilder =Notifications.create()
                    .title("Ajout")
                    .text("l'attribut ne doit pas etre vide ")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        } else {

            sp.ajouter(new Offre(tfTitre.getText(), Date.valueOf(value1), tfDescription.getText()));
            Image img = new Image("images/images.png");
            Notifications notificationBuilder =Notifications.create()
                    .title("Ajout offre")
                    .text("offre ajoutée")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            //JOptionPane.showMessageDialog(null, "offre ajoutée");
        }
    }

    public void SetTextField1(String a) {
        this.tfTitre.setText(a);
    }

    public void SetTextField2(String a) {
        this.tfDescription.setText(a);
    }

    public void DatePicker1(LocalDate d) {
        this.tfDate.setValue(d);
    }
    
}
