package daoArticle;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Article;
import beans.BeanException;

public class DatabaseConnectionArticle implements InterfaceArticle {
	
	private Database database;

	DatabaseConnectionArticle(Database database) {
        this.database = database;
    }

	@Override
	public int ajouter(Article article) throws DaoException,BeanException {
		int ret=0;
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = database.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO article(nom, description, quantite, image) VALUES (?,?,?,?)");
            preparedStatement.setString(1, article.getNom());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setInt(3, article.getQuantite());
            preparedStatement.setBlob(4, article.getImage());
            ret = preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return ret;
	}

	@Override
	public int modifier(Article article) throws DaoException {
		int ret=0;
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = database.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE article SET nom=?,description=?,quantite=? WHERE id=?");
            preparedStatement.setString(1, article.getNom());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setInt(3, article.getQuantite());
            preparedStatement.setInt(4, article.getId());

            ret = preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return ret;
	}

	@Override
	public int supprimer(int id) throws DaoException {
		int ret=0;
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = database.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM article WHERE id=?");
            preparedStatement.setInt(1, id);

            ret = preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return ret;
	}

	@Override
	public List<Article> affichier()  throws DaoException,BeanException{
		List<Article> articles = new ArrayList<Article>();
        Connection connexion = null;
        ResultSet resultat = null;

        try {
            connexion = database.getConnection();
            PreparedStatement preparedStatement =  connexion.prepareStatement("SELECT * FROM article");
            resultat = preparedStatement.executeQuery();

            while (resultat.next()) {
            	articles.add(new Article(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getBinaryStream(5), resultat.getInt(4)));
            }
            connexion.close();
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return articles;
	}

	@Override
	public List<Article> rechercher(String nom) throws DaoException, BeanException {
		List<Article> articles = new ArrayList<Article>();
        Connection connexion = null;
        ResultSet resultat = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = database.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM article WHERE nom like ?");
            preparedStatement.setString(1, "%" + nom + "%");
            resultat = preparedStatement.executeQuery();

            while (resultat.next()) {
            	articles.add(new Article(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getBinaryStream(5), resultat.getInt(4)));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return articles;
	}

	@Override
	public Article rechercherById(int id) throws DaoException, BeanException {
		Article article = null;
        Connection connexion = null;
        ResultSet resultat = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = database.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM article WHERE id=?");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	article = new Article(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getBinaryStream(5), resultat.getInt(4));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return article;
	}
}
