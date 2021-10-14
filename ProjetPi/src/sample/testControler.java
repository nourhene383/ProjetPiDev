package sample;



import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;

import java.util.ArrayList;

import java.util.List;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;



import javafx.scene.input.MouseEvent;








import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.WritableImage;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import org.controlsfx.control.WorldMapView;
import sample.Maps.MapController;
import sample.Maps.WebViewCaptureMap;
import sample.connection.DataBase;
import sample.models.Clients;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import java.sql.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.*;


public class testControler implements Initializable {

    @FXML
    private ImageView QRcode;

    @FXML
    private Text Location;

    public TextField filterfield;
    @FXML
    private TextField textlastN;

    @FXML
    private TextField textfirstN;

    @FXML
    private TextField fieldnbr;

    @FXML
    private TextField fieldcin;

    @FXML
    private DatePicker fielddate;

    @FXML
    private Button insertButton;

    @FXML
    private Label connlabel;

    @FXML
    private TableView<Clients> TableView;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField txtID;

    @FXML
    private Button quitbtn;


    @FXML
    private Button btnlocation;

    @FXML
    private TableColumn<Clients, String> idColumn;

    @FXML
    private TableColumn<Clients, String> lastColumn;

    @FXML
    private TableColumn<Clients, String> firstColumn;

    @FXML
    private TableColumn<Clients, Integer> phoneColumn;

    @FXML
    private TableColumn<Clients, Integer> cinColumn;
    @FXML
    private TableColumn<Clients, Date> dateColumn;


    @FXML
    private ComboBox comboP;


    @FXML
    void btnquit(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) quitbtn.getScene().getWindow();
        // do what you have to do
        stage.close();

    }



    @FXML
    void comboboxx(ActionEvent event) {

    pays = comboP.getSelectionModel().getSelectedItem().toString();

/* comboboxx test mahdi*/
    }


    public testControler() {
       connection = (Connection) DataBase.conDB();
    }

    /**
     * Initializes the controller class.
     */
    String pathImage,pays;

    PreparedStatement preparedStatement;
    Connection connection;

    private static String img_path;
    static String map_path;

    ObservableList<Clients> listC;
    ObservableList<Clients> dataList;

    List<String> file;

    public String random_id() {

        Random random = new Random();

        String generatedString = random.ints(6,65,90)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return generatedString;
    }



    @FXML
    private String insertButton() {

      //  String query = " insert into clients values('"+random_id()+"','"+pathImage+"','"+pays+"',"+fieldnbr.getText()+","+fieldcin.getText()+",'"+java.sql.Date.valueOf(fielddate.getValue())+"')";
       // executeQuery(query);
       // showClients();
      //  search_clients();




        try {
            String st = "INSERT INTO clients ( idCli, firstname, lastname,phoneNumbre, Cin,dateNaissance,pos_map) VALUES (?,?,?,?,?,?,?)";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, random_id());
            preparedStatement.setString(2, pathImage);
            preparedStatement.setString(3, pays);
            preparedStatement.setString(4, fieldnbr.getText());
            preparedStatement.setString(5, fieldcin.getText());
            preparedStatement.setDate(6, java.sql.Date.valueOf(fielddate.getValue()));
            preparedStatement.setString(7, img_path);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            connlabel.setTextFill(Color.GREEN);
            connlabel.setText("Added Successfully");
            showClients();
            search_clients();
            return "Success";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            connlabel.setTextFill(Color.TOMATO);
            connlabel.setText(ex.getMessage());
            return "Exception";
        }

      //  executeQuery(st);
       // showClients();
      //  search_clients();


    }


    @FXML
    private String updateButton() {

        Clients clients = TableView.getSelectionModel().getSelectedItem();

     //   String query = "UPDATE clients SET  firstname='"+textfirstN.getText()+"',lastname='"+textlastN.getText()+"',phoneNumbre="+fieldnbr.getText()+",Cin="+fieldcin.getText()+",dateNaissance='"+java.sql.Date.valueOf(fielddate.getValue())+"' WHERE idCli='"+clients.getIdC()+"' ";
      //  executeQuery(query);
      //  showClients();
      //  search_clients();


        try {
            String st = "UPDATE clients SET firstname = ? , lastname = ? , phoneNumbre = ? , Cin = ? , dateNaissance = ?   WHERE idCli = ? ";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, textfirstN.getText());
            preparedStatement.setString(2, textlastN.getText());
            preparedStatement.setString(3, fieldnbr.getText());
            preparedStatement.setString(4, fieldcin.getText());
            preparedStatement.setDate(5, java.sql.Date.valueOf(fielddate.getValue()));
            preparedStatement.setString(6, clients.getIdC());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connlabel.setTextFill(Color.GREEN);
            connlabel.setText("Added Successfully");
            showClients();
            search_clients();
            return "Success";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            connlabel.setTextFill(Color.TOMATO);
            connlabel.setText(ex.getMessage());
            return "Exception";
        }



    }

    @FXML
    private void deleteButton() {

        Clients clients = TableView.getSelectionModel().getSelectedItem();


        String query = "DELETE FROM clients WHERE idCli='"+clients.getIdC()+"'";
        executeQuery(query);
        showClients();
        search_clients();

    }

    public void executeQuery(String query) {

        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        file = new ArrayList<>();
        file.add("*.doc");
        file.add("*.png");
        file.add("*.jpeg");
        file.add("*.jpg");

    ObservableList<String> listP = FXCollections.observableArrayList("Paris","Tunis","Tripoli","Dubai","Madrid");

    comboP.getSelectionModel().select("Pays");
    comboP.setItems(listP);



        showClients();
        search_clients();

        if (connection == null) {
            connlabel.setTextFill(Color.TOMATO);
            connlabel.setText("Server Error : Check");
        } else {
            connlabel.setTextFill(Color.GREEN);
            connlabel.setText("Server is up : Good to go");
        }
    }

    public ObservableList<Clients> getClientList(){
        ObservableList<Clients> clientList = FXCollections.observableArrayList();


        String query = "SELECT * FROM clients ";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Clients clients;

            while(rs.next()) {
                clients = new Clients (rs.getString("idCli"),rs.getString("lastname"),rs.getString("firstname"),rs.getInt("phoneNumbre"),rs.getInt("Cin"),rs.getDate("dateNaissance") );

                clientList.add(clients);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showClients() {



        ObservableList<Clients> list = getClientList();


        idColumn.setCellValueFactory(new PropertyValueFactory<Clients,String>("idC"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<Clients,String>("Firstname"));
        firstColumn.setCellValueFactory(new PropertyValueFactory<Clients,String>("Lastname"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<Clients,Integer>("Cin"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Clients,Integer>("numtel"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Clients, Date>("Date"));


      //  TableView.getItems().stream().filter(item -> item.getCin()==1).findAny().ifPresent(item -> {TableView.getSelectionModel().select(item);
      //  TableView.scrollTo(item);});
        TableView.setItems(list);


    }

    @FXML
    void handleMouseClick(MouseEvent event) {



Clients clients = TableView.getSelectionModel().getSelectedItem();



      //  txtID.setText(""+clients.getIdC());
        textlastN.setText(clients.getLastname());
        textfirstN.setText(clients.getFirstname());
        fieldcin.setText(""+clients.getCin());
        fieldnbr.setText(""+clients.getNumtel());


       qrcode();


    }

    void search_clients()
    {


        idColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("idC"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<Clients,String>("Firstname"));
        firstColumn.setCellValueFactory(new PropertyValueFactory<Clients,String>("Lastname"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<Clients,Integer>("Cin"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Clients,Integer>("numtel"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Clients, Date>("Date"));

        dataList = getClientList();
        TableView.setItems(dataList);

        FilteredList<Clients> filteredList = new FilteredList<>(dataList ,b -> true );
        filterfield.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filteredList.setPredicate(person ->
            {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;

                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(person.getFirstname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                }else if (person.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }else if(person.getIdC().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }else if (String.valueOf(person.getCin()).indexOf(lowerCaseFilter) !=-1){
                    return true;

                }else if (String.valueOf(person.getNumtel()).indexOf(lowerCaseFilter) !=-1) {
                return true;

                }else if(String.valueOf(person.getDate()).indexOf(lowerCaseFilter) !=-1)
                    return true;

                        else
                        return false;


            });
        }));

        SortedList<Clients> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(TableView.comparatorProperty());
        TableView.setItems(sortedList);


    }



    @FXML
    protected void qrcode()
    {

        Clients clients = TableView.getSelectionModel().getSelectedItem();

        if (clients.getFirstname().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Empty URL");
            alert.setContentText("Please input URL");
            alert.showAndWait();
            return;
        }


        try {
            final BufferedImage bi = generateData(clients.getFirstname());
            WritableImage img = SwingFXUtils.toFXImage(bi, null);
            QRcode.setFitWidth(img.getWidth());
            QRcode.setFitHeight(img.getHeight());
            QRcode.setImage(img);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void handleSaveAs(ActionEvent event)
    {

        Clients clients = TableView.getSelectionModel().getSelectedItem();
        if (QRcode.getImage() == null) {
            if (clients.getIdC().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("No QR code generated");
                alert.setContentText("Make sure you have generated a QR code before saving");
                alert.showAndWait();
                return;
            }
            return;
        }

        Button btn = (Button) event.getSource();

        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image","*.png","*.jpg"));

        File file = chooser.showSaveDialog(btn.getScene().getWindow());
        if (file == null) {
            return;
        }

        Alert alert;
        try {
            Image img = QRcode.getImage();
            BufferedImage bi = SwingFXUtils.fromFXImage(img, null);
            if (file.getName().toLowerCase().endsWith("png")) {
                ImageIO.write(bi, "png", file);
            } else {
                ImageIO.write(bi, "jpg", file);
            }
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("File saved!");
            alert.showAndWait();
        } catch (Exception ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error saving image");
            alert.showAndWait();
        }
    }


    private BufferedImage generateData(final String url) throws IOException, WriterException {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 153, 124);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            BufferedImage bi = MatrixToImageWriter.toBufferedImage(bitMatrix);
            return bi;
        } catch (Exception ex)
        {
            throw ex;
        }
    }

    @FXML
    void btnFile(ActionEvent event) {
FileChooser fc = new FileChooser();
fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", file));
File f = fc.showOpenDialog(null);

if(f != null)
{
    connlabel.setText("Selected File ::"+ f.getAbsolutePath());
    System.out.println(f.getAbsolutePath());
    pathImage = f.getAbsolutePath();
    System.out.println(pathImage);
}

    }

    @FXML
    void Localiser(ActionEvent event) throws Exception{
//try {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("map.fxml"));
            Parent root = loader.load();
            MapController cr = loader.getController();*/


        WebViewCaptureMap w = new WebViewCaptureMap();
        Stage stage = new Stage();
        w.start(stage);
        /*stage.setScene(new Scene(root));
            MainGUICategorie m = new MainGUICategorie();
            m.Map(stage);
            stage.show();*/

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            public void handle(WindowEvent we) {

                System.out.println("Stage is closing");
                MapController mp = new MapController();
                Location.setText(mp.getMap_value());
                System.out.println(mp.getMap_value());
                map_path=mp.getMap_value();
                if (!"".equals(Location.getText())) {
                    btnlocation.setDisable(true);
                    WebViewCaptureMap w = new WebViewCaptureMap();
                    String m = "http://" + w.getDb_path();
                    img_path = m;
                    Image img = new Image("file:///"+w.getPath()+"");
                    System.out.println(w.getPath());
                    QRcode.setImage(img);
                    QRcode.setFitHeight(200);
                    QRcode.setFitWidth(220);
                    QRcode.setX(-40);
                    QRcode.setY(-30);
                    System.out.println("done");
                }

            }
        });

        /*} catch (IOException ex) {
            Logger.getLogger(Version_3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }*/



    }




}
