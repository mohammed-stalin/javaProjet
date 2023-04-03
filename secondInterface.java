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
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;
import ma.fstt.model.Produit;
import ma.fstt.model.ProduitDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class secondInterface implements Initializable {

    @FXML
    private TextField libele ;


    @FXML
    private TextField designation ;
    @FXML
    private TextField prix ;


    @FXML
    private TableView<Produit> mytable ;


    @FXML
    private TableColumn<Produit ,Long> col_id ;

    @FXML
    private TableColumn <Produit ,String> col_libele ;

    @FXML
    private TableColumn <Produit ,String> col_des ;
    @FXML
    private TableColumn <Produit ,String> col_prix ;

    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            ProduitDAO produitDAO = new ProduitDAO();

            Produit prod = new Produit(0l , libele.getText() , designation.getText(),prix.getText());

            produitDAO.save(prod);


            UpdateTable();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    protected void onUpdateButtonClick() {

        // access to database
        try {
            ProduitDAO produitDAO = new ProduitDAO();

            // get the selected row from the table
            Produit selectedProduit = mytable.getSelectionModel().getSelectedItem();

            // update the object with the new values from the text fields
            selectedProduit.setLibele(libele.getText());
            selectedProduit.setDesignation(designation.getText());
            selectedProduit.setPrix(prix.getText());
            // update the object in the database
            produitDAO.update(selectedProduit);

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
            ProduitDAO produitDAO = new ProduitDAO();

            // get the selected row from the table
            Produit selectedProduit = mytable.getSelectionModel().getSelectedItem();

            // delete the object from the database
            produitDAO.delete(selectedProduit);

            // update the table with the new data
            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Produit,Long>("id_Produit"));
        col_libele.setCellValueFactory(new PropertyValueFactory<Produit,String>("libele"));
        col_des.setCellValueFactory(new PropertyValueFactory<Produit,String>("designation"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,String>("prix"));



        mytable.setItems(this.getDataProduits());
    }

    public static ObservableList<Produit> getDataProduits(){

        ProduitDAO produitDAO = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            produitDAO = new ProduitDAO();
            for (Produit ettemp : produitDAO.getAll())
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

    @FXML
    private Button thirdInterface;
    @FXML
    protected void switchToThirdInterface() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("third_interface.fxml"));
                Parent root;
                root = loader.load();
                Scene thirdScene = new Scene(root);
                Stage stage = (Stage) thirdInterface.getScene().getWindow();
                stage.setScene(thirdScene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
