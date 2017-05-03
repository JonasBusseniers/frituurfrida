package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import be.vdab.enteties.Saus;

public class SausRepository extends AbstractRepository {


	private static final String FIND_ALL = "select sausId, sausNaam from sauzen order by sausNaam";
	private static final String FIND_SAUS = "select sausId, sausNaam from sauzen where sausId=?";
	private static final String FIND_SAUS_BY_INGREDIENT = "select sausId, sausNaam from sauzen where sausId in (select sausId from sausingredient where ingredientId=(select ingredientId from ingredienten where ingredientNaam=?)) order by sausNaam";
	private static final String FIND_INGREDIENTEN = "select ingredientNaam from ingredienten where ingredientId in (select ingredientId from sausIngredient where sausId = ?)";
	private static final String DELETE_SAUS = "delete from sauzen where sausId=?";

	public List<Saus> findAll() {
		try (Connection connectie = dataSource.getConnection();) {
			Statement statement = connectie.createStatement();
			ResultSet resultset = statement.executeQuery(FIND_ALL);

			List<Saus> sauzen = new ArrayList<>();
			while (resultset.next()) {

				sauzen.add(maakSausVanResultSet(resultset));

			}
			return sauzen;
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}

	}

	private Saus maakSausVanResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("sausId");

		try (Connection connectie = dataSource.getConnection();
				PreparedStatement statement = connectie.prepareStatement(FIND_INGREDIENTEN);) {
			statement.setLong(1, id);
			Set<String> ingredienten = new HashSet<>();

			try (ResultSet resultSetIngredienten = statement.executeQuery()) {

				while (resultSetIngredienten.next()) {

					ingredienten.add(resultSetIngredienten.getString("ingredientNaam"));
				}
			}
			return new Saus(id, resultSet.getString("sausNaam"), ingredienten);
		}
	}

	public Optional<List<Saus>> find(String teVindenIngredient) {
		List<Saus> gevondenSauzen = new ArrayList<Saus>();
		try (Connection connectie = dataSource.getConnection();
				PreparedStatement statement = connectie.prepareStatement(FIND_SAUS_BY_INGREDIENT);) {
			statement.setString(1, teVindenIngredient);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					gevondenSauzen.add(maakSausVanResultSet(resultSet));
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
		return gevondenSauzen.isEmpty() ? Optional.empty() : Optional.of(gevondenSauzen);
	}

	public Saus lees(Long nummer) {
		try (Connection connectie = dataSource.getConnection();
				PreparedStatement statement = connectie.prepareStatement(FIND_SAUS);) {
			statement.setLong(1, nummer);
			return maakSausVanResultSet(statement.executeQuery());			
			
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	public void verwijder(Long nummer) {
		try (Connection connectie = dataSource.getConnection();
				PreparedStatement statement = connectie.prepareStatement(DELETE_SAUS);) {
			statement.setLong(1, nummer);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}
}
