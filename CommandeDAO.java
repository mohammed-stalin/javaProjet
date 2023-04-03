package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO extends BaseDAO<Commande> {

    private static final String TABLE_NAME = "commande";

    public CommandeDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Commande commande) throws SQLException {
        String query = String.format("INSERT INTO %s (libele, code, date) VALUES (?, ?, ?)", TABLE_NAME);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, commande.getLibele());
        preparedStatement.setString(2, commande.getCode());
        preparedStatement.setString(3, commande.getDate());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Commande commande) throws SQLException {
        String query = String.format("UPDATE %s SET libele=?, code=?, date=? WHERE id_commande=?", TABLE_NAME);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, commande.getLibele());
        preparedStatement.setString(2, commande.getCode());
        preparedStatement.setString(3, commande.getDate());
        preparedStatement.setLong(4, commande.getId_commande());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Commande commande) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE id_commande=?", TABLE_NAME);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, commande.getId_commande());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Commande> getAll() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Commande commande = new Commande();
            commande.setId_commande(resultSet.getLong("id_commande"));
            commande.setLibele(resultSet.getString("libele"));
            commande.setCode(resultSet.getString("code"));
            commande.setDate(resultSet.getString("date"));
            commandes.add(commande);
        }
        return commandes;
    }

    @Override
    public Commande getOne(Long id) throws SQLException {
        Commande commande = null;
        String query = String.format("SELECT * FROM %s WHERE id_commande=?", TABLE_NAME);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            commande = new Commande();
            commande.setId_commande(resultSet.getLong("id_commande"));
            commande.setLibele(resultSet.getString("libele"));
            commande.setCode(resultSet.getString("code"));
            commande.setDate(resultSet.getString("date"));
        }
        return commande;
    }
}
