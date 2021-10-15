package sample.controles;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DashFX  implements Initializable {
    @FXML
    private ToggleButton profile;

    @FXML
    private ToggleButton activity;

    @FXML
    private ToggleButton goals;

    @FXML
    private ToggleButton mentors;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void loadActivity(ActionEvent event) {

        try {
            if(activity.isSelected())
            {
                profile.setSelected(false);
                goals.setSelected(false);
                mentors.setSelected(false);
                TabPane pane= FXMLLoader.load(getClass().getResource("/sample/view/ClientsPage.fxml"));
                pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
                rootPane.getChildren().removeAll(rootPane.getChildren());
                rootPane.getChildren().add(pane);
            }

        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    @FXML
    void loadGoals(ActionEvent event) {
        try {
            if(goals.isSelected())
            {
                activity.setSelected(false);
                profile.setSelected(false);
                mentors.setSelected(false);
                TabPane pane= FXMLLoader.load(getClass().getResource("/sample/view/ClientsPage.fxml"));
                pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
                rootPane.getChildren().removeAll(rootPane.getChildren());
                rootPane.getChildren().add(pane);
            }

        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

    }

    @FXML
    void loadMentors(ActionEvent event) {
        try {
            if(mentors.isSelected())
            {
                activity.setSelected(false);
                goals.setSelected(false);
                profile.setSelected(false);
                TabPane pane= FXMLLoader.load(getClass().getResource("/sample/view/ClientsPage.fxml"));
                pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
                rootPane.getChildren().removeAll(rootPane.getChildren());
                rootPane.getChildren().add(pane);
            }

        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    @FXML
    void loadProfile(ActionEvent event) {
        try {
            if(profile.isSelected())
            {
                activity.setSelected(false);
                goals.setSelected(false);
                mentors.setSelected(false);
                TabPane pane= FXMLLoader.load(getClass().getResource("/sample/view/hotel.fxml"));
                pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
                rootPane.getChildren().removeAll(rootPane.getChildren());
                rootPane.getChildren().add(pane);
            }

        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profile.setSelected(false);
        activity.setSelected(false);
        goals.setSelected(false);
        mentors.setSelected(false);

    }
}


