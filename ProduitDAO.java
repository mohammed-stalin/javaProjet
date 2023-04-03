package ma.fstt.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit> {

    public ProduitDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Produit produit) throws SQLException {
        String sql = "INSERT INTO produit (libele, designation, prix) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, produit.getLibele());
        preparedStatement.setString(2, produit.getDesignation());
        preparedStatement.setString(3, produit.getPrix());
        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                produit.setId_Produit(rs.getLong(1));
            }
        }
    }

    @Override
    public void update(Produit produit) throws SQLException {
        String sql = "UPDATE produit SET libele = ?, designation = ?, prix = ? WHERE id_Produit = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, produit.getLibele());
        preparedStatement.setString(2, produit.getDesignation());
        preparedStatement.setString(3, produit.getPrix());
        preparedStatement.setLong(4, produit.getId_Produit());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Produit produit) throws SQLException {
        String sql = "DELETE FROM produit WHERE id_Produit = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, produit.getId_Produit());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Produit> getAll() throws SQLException {
        String sql = "SELECT * FROM produit";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        List<Produit> produits = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id_Produit");
            String libele = resultSet.getString("libele");
            String designation = resultSet.getString("designation");
            String prix = resultSet.getString("prix");
            Produit produit = new Produit(id, libele, designation, prix);
            produits.add(produit);
        }
        return produits;
    }

    @Override
    public Produit getOne(Long id) throws SQLException {
        String sql = "SELECT * FROM produit WHERE id_Produit = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Long produitId = resultSet.getLong("id_Produit");
            String libele = resultSet.getString("libele");
            String designation = resultSet.getString("designation");
            String prix = resultSet.getString("prix");
            Produit produit = new Produit(produitId, libele, designation, prix);
            return produit;
        }
        return null;
    }
}
