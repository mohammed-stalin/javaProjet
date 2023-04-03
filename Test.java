package ma.fstt.model;
import java.sql.SQLException;
import java.util.List;
public class Test {
    public static void main(String[] args) {
// trait bloc try catch
        try {

            ProduitDAO produitDAO = new ProduitDAO();

            Produit prod = new Produit(0l , "prodtest" ,
                    "200000000","4dh");

            produitDAO.save(prod);

            List<Produit> livlist =  produitDAO.getAll();

            for (Produit liv :livlist) {

                System.out.println(liv.toString());

            }
        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }
}
