package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField nom ;
    @FXML
    private TextField tele ;
    @FXML
    private TableView<Livreur> mytable ;
    @FXML
    private TableColumn<Livreur ,Long> col_id ;
    @FXML
    private TableColumn <Livreur ,String> col_nom ;
    @FXML
    private TableColumn <Livreur ,String> col_tele ;
    @FXML
    protected void onSaveButtonClick() {
        // accees a la bdd
        try {
            LivreurDAO livreurDAO = new LivreurDAO();
            Livreur liv = new Livreur(0l , nom.getText() , tele.getText());
            livreurDAO.save(liv);
            UpdateTable();
        } catch (SQLException e) {throw new RuntimeException(e);}
    }

    @FXML
    protected void onUpdateButtonClick() {
        // access to database
        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            // get the selected row from the table
            Livreur selectedLivreur = mytable.getSelectionModel().getSelectedItem();
            // update the object with the new values from the text fields
            selectedLivreur.setNom(nom.getText());
            selectedLivreur.setTelephone(tele.getText());
            // update the object in the database
            livreurDAO.update(selectedLivreur);
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
            LivreurDAO livreurDAO = new LivreurDAO();

            // get the selected row from the table
            Livreur selectedLivreur = mytable.getSelectionModel().getSelectedItem();

            // delete the object from the database
            livreurDAO.delete(selectedLivreur);

            // update the table with the new data
            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void switchToSecondInterface() {
        Scene scene = mytable.getScene();
        scene.setRoot(secondInterface);
    }




    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Livreur,Long>("id_livreur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Livreur,String>("nom"));

        col_tele.setCellValueFactory(new PropertyValueFactory<Livreur,String>("telephone"));



        mytable.setItems(this.getDataLivreurs());
    }

    public static ObservableList<Livreur> getDataLivreurs(){

        LivreurDAO livreurDAO = null;

        ObservableList<Livreur> listfx = FXCollections.observableArrayList();

        try {
            livreurDAO = new LivreurDAO();
            for (Livreur ettemp : livreurDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("second_interface.fxml"));
        try {
            secondInterface = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    private Parent secondInterface;
}