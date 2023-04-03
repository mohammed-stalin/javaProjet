package ma.fstt.trackingl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.fstt.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class thirdInterface implements Initializable {

    @FXML
    private TextField libele ;

    @FXML
    private TextField code ;
    @FXML
    private TextField date ;


    @FXML
    private TableView<Commande> mytable ;


    @FXML
    private TableColumn<Commande ,Long> col_id ;

    @FXML
    private TableColumn <Commande ,String> col_libele ;

    @FXML
    private TableColumn <Commande ,String> col_code ;
    @FXML
    private TableColumn <Commande ,String> col_date ;

    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            CommandeDAO commandeDAO = new CommandeDAO();

            Commande comm = new Commande(0l , libele.getText() , code.getText(),date.getText());

            commandeDAO.save(comm);


            UpdateTable();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    protected void onUpdateButtonClick() {

        // access to database
        try {
            CommandeDAO commandeDAO = new CommandeDAO();

            // get the selected row from the table
            Commande selectedCommande = mytable.getSelectionModel().getSelectedItem();

            // update the object with the new values from the text fields
            selectedCommande.setLibele(libele.getText());
            selectedCommande.setCode(code.getText());
            selectedCommande.setDate(date.getText());
            // update the object in the database
            commandeDAO.update(selectedCommande);

            // update the table with the new data
            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onDeleteButtonClick() {

        // access to database
        try {
            CommandeDAO commandeDAO = new CommandeDAO();


            // get the selected row from the table
            Commande selectedCommande = mytable.getSelectionModel().getSelectedItem();

            // delete the object from the database
            commandeDAO.delete(selectedCommande);

            // update the table with the new data
            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Commande,Long>("id_commande"));
        col_libele.setCellValueFactory(new PropertyValueFactory<Commande,String>("libele"));
        col_code.setCellValueFactory(new PropertyValueFactory<Commande,String>("code"));
        col_date.setCellValueFactory(new PropertyValueFactory<Commande,String>("date"));



        mytable.setItems(this.getDataCommandes());
    }

    public static ObservableList<Commande> getDataCommandes(){

        CommandeDAO commandeDAO = null;

        ObservableList<Commande> listfx = FXCollections.observableArrayList();

        try {
            commandeDAO = new CommandeDAO();
            for (Commande ettemp : commandeDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }


    @FXML
    private Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    /*@FXML
    CLOSE
    private void handleBackButtonAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }*/
    @FXML
    protected void handleBackButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("second_interface.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
