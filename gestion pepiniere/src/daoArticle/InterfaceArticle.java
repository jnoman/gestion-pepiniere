package daoArticle;

import java.util.List;
import beans.Article;
import beans.BeanException;

public interface InterfaceArticle {
	int ajouter(Article article) throws DaoException,BeanException;
	int modifier(Article article) throws DaoException,BeanException;
	int supprimer(int id) throws DaoException,BeanException; 
	List<Article> affichier()  throws DaoException,BeanException;
	List<Article> rechercher(String nom)  throws DaoException,BeanException;
	Article rechercherById(int id)  throws DaoException,BeanException;
}
