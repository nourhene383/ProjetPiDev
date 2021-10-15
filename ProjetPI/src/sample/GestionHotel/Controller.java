package sample.GestionHotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class Controller implements Initializable {

    List<String> file;
    List<String> fileVid;
    String pathImg;
    String pathVideo;
    String chambre ;

    @FXML
    private TextField filtrage_bad;
    @FXML
    private TextField Nom_hotel_bad;

    @FXML
    private TextField Etoilet_hotel_bad;

    @FXML
    private TableView<Hotel> tvhHotel_bad;

    @FXML
    private TableColumn<Hotel, Integer> col_Id_bad;

    @FXML
    private TableColumn<Hotel, String> col_nom_bad;

    @FXML
    private TableColumn<Hotel, String> col_chambre_bad;

    @FXML
    private TableColumn<Hotel, String> col_heb_bad;

    @FXML
    private TableColumn<Hotel, String> col_lieu_bad;

    @FXML
    private TableColumn<Hotel, Integer> col_etoile_bad;

    @FXML
    private Button btnupdate_bad;

    @FXML
    private Button btndelete_bad;

    @FXML
    private TextField Lieu_Hotel_bad;

    @FXML
    private TextField Id_hotel_bad;

    @FXML
    private TextArea Heb_hotel_bad;

    @FXML
    private Button btninsert_bad;

    @FXML
    private TableColumn<Hotel, String> col_img;

    @FXML
    private TableColumn<Hotel, String> col_vid;


    @FXML
    private CheckBox cb1;

    @FXML
    private CheckBox cb2;

    @FXML
    private CheckBox cb3;

    @FXML
    private CheckBox cb4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {showhotel();

        file = new ArrayList<>();
        file.add("*.doc");
        file.add ("*.png");
        file.add ("*.jpeg");
        file.add ("*.jpg");

        fileVid = new ArrayList <>();
        fileVid.add("*.mp3");
        fileVid.add("*.mp4");
        fileVid.add("*.wmv");
        fileVid.add("*.avi");
        fileVid.add("*.mkv");
        fileVid.add("*.flv");
        search_hotel();


    }


    private void insertRecord(){
        String query ="INSERT INTO hotel VALUES("+ Id_hotel_bad.getText()+",'"+ Nom_hotel_bad.getText() +"',"+Etoilet_hotel_bad.getText()+",'"+Heb_hotel_bad.getText()+"','"+Lieu_Hotel_bad.getText()+"','"+pathImg+"','"+pathVideo+"','"+chambre+"')";
        executeQuery(query);
        showhotel();
        search_hotel();
    }



    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st =conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }




    @FXML
    void ajouter(ActionEvent event) {
        if(event.getSource()== btninsert_bad){
            insertRecord();
        } else if (event.getSource()==btnupdate_bad){
            update();
        }
        else if (event.getSource()==btndelete_bad){
            delete();
        }

    }






    public Connection getConnection(){
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aero_space","root","");
            return conn;
        }catch (Exception ex ){
            System.out.println("Error :"+ex.getMessage());
            return null;
        }
    }


    public ObservableList<Hotel> getHotelList(){
        ObservableList<Hotel> hotelList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM hotel";
        Statement st;
        ResultSet rs;
        try {
            st= conn.createStatement();
            rs =st.executeQuery(query);
            Hotel hotel;
            while (rs.next()){
                hotel =new Hotel(rs.getInt("id"), rs.getString("nom"),rs.getInt("etoile"), rs.getString("lieu"), rs.getString("hebergement"), rs.getString("Path_image"),rs.getString("Path_video"),rs.getString("chambre"));
                hotelList.add(hotel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hotelList;
    }



    public void showhotel(){
        ObservableList<Hotel> list =getHotelList();
        col_Id_bad.setCellValueFactory(new PropertyValueFactory<Hotel,Integer>("id"));
        col_nom_bad.setCellValueFactory(new PropertyValueFactory<Hotel,String>("nom"));
        col_etoile_bad.setCellValueFactory(new PropertyValueFactory<Hotel,Integer>("etoile"));
        col_heb_bad.setCellValueFactory(new PropertyValueFactory<Hotel,String>("hebergement"));
        col_lieu_bad.setCellValueFactory(new PropertyValueFactory<Hotel,String>("lieu"));
        col_img.setCellValueFactory(new PropertyValueFactory<Hotel,String>("Path_image"));
        col_vid.setCellValueFactory(new PropertyValueFactory<Hotel,String>("Path_video"));
        col_chambre_bad.setCellValueFactory(new PropertyValueFactory<Hotel,String>("chambre"));



        tvhHotel_bad.setItems(list);
    }


    private void update () {
        String query = "UPDATE hotel SET id="+ Id_hotel_bad.getText()+",nom='"+ Nom_hotel_bad.getText() +"',etoile="+Etoilet_hotel_bad.getText()+",hebergement='"+Heb_hotel_bad.getText()+"',lieu='"+Lieu_Hotel_bad.getText()+"'   WHERE id = " + Id_hotel_bad.getText() + " ";
        executeQuery(query);
        showhotel();
        search_hotel();
    }


    private void delete(){
        String query ="DELETE FROM hotel WHERE id ="+Id_hotel_bad.getText()+"";
        executeQuery(query);
        showhotel();
    }



    @FXML
    void ajouterImg(ActionEvent event) {
        FileChooser fc = new FileChooser ();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",file));
        File f = fc.showOpenDialog(null);
        if(f != null){
            pathImg = f.toString() ;
        }
    }




    @FXML
    void ajouterVid(ActionEvent event) {

        FileChooser fc = new FileChooser ();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Vido Files",fileVid));
        File f = fc.showOpenDialog(null);
        if(f != null){
            pathVideo = f.toString() ;
        }
    }




    @FXML
    void checkbox(ActionEvent event) {


        if (cb1.isSelected()) {

            chambre = cb1.getText() + " ";

        }
        if (cb2.isSelected()) {

            chambre = cb2.getText() + " ";

        }
        if (cb3.isSelected()) {

            chambre = cb3.getText() + " ";

        }
        if (cb4.isSelected()) {

            chambre += cb4.getText() + " ";
        }
    }


    ObservableList<Hotel> dataList;
    void search_hotel(){
        col_Id_bad.setCellValueFactory(new PropertyValueFactory<Hotel,Integer>("id"));
        col_nom_bad.setCellValueFactory(new PropertyValueFactory<Hotel,String>("nom"));
        col_etoile_bad.setCellValueFactory(new PropertyValueFactory<Hotel,Integer>("etoile"));
        col_heb_bad.setCellValueFactory(new PropertyValueFactory<Hotel,String>("hebergement"));
        col_lieu_bad.setCellValueFactory(new PropertyValueFactory<Hotel,String>("lieu"));
        col_img.setCellValueFactory(new PropertyValueFactory<Hotel,String>("Path_image"));
        col_vid.setCellValueFactory(new PropertyValueFactory<Hotel,String>("Path_video"));
        col_chambre_bad.setCellValueFactory(new PropertyValueFactory<Hotel,String>("chambre"));

        dataList = getHotelList();
        tvhHotel_bad.setItems(dataList);

        FilteredList<Hotel> filteredList = new FilteredList<>(dataList , b -> true );
        filtrage_bad.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filteredList.setPredicate(hotel ->
            {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(String.valueOf(hotel.getId()).indexOf(lowerCaseFilter) !=-1) {
                    return true;

                }else if (hotel.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }
                if(String.valueOf(hotel.getEtoile()).indexOf(lowerCaseFilter) !=-1) {
                    return true;
                }else if(hotel.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }else if (String.valueOf(hotel.getPath_image()).indexOf(lowerCaseFilter) !=-1){
                    return true;

                }else if(String.valueOf(hotel.getPath_video()).indexOf(lowerCaseFilter) !=-1) {
                    return true;

                }else if (hotel.getChambre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                }
                else
                    return false;


            });
        }));

        SortedList<Hotel> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tvhHotel_bad.comparatorProperty());
        tvhHotel_bad.setItems(sortedList);


    }

    @FXML
    private void mousebtn (MouseEvent event) {
        Hotel hotel = tvhHotel_bad.getSelectionModel().getSelectedItem();
        Id_hotel_bad.setText(""+hotel.getId());
        Nom_hotel_bad.setText(hotel.getNom());
        Heb_hotel_bad.setText(""+hotel.getHebergement());
        Lieu_Hotel_bad.setText(""+hotel.getLieu());
        Etoilet_hotel_bad.setText(""+hotel.getEtoile());

    }


}
