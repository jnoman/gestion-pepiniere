package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Article;
import beans.BeanException;
import daoArticle.DaoException;
import daoArticle.Database;
import daoArticle.InterfaceArticle;

/**
 * Servlet implementation class Modifier
 */
@WebServlet("/Modifier")
public class Modifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InterfaceArticle interfaceArticle;

	@Override
	public void init() throws ServletException {
		Database database = Database.getInstance();
		this.interfaceArticle = database.getInterfaceArticle();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if (request.getParameter("modifier") != null) {
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			int quantite = Integer.parseInt(request.getParameter("quantite"));
			try {
				int ret = interfaceArticle.modifier(new Article(id, nom, description, null, quantite));
				if(ret == 1) {
					request.setAttribute("succes", "la modification d'article terminé avec succès");
				}
			} catch (DaoException e) {
				request.setAttribute("erreur", e.getMessage());
			} catch (BeanException e) {
				request.setAttribute("erreur", e.getMessage());
			}
			RequestDispatcher rd = request.getRequestDispatcher("detail?id=" + id);
			rd.forward(request, response);
	    } else if (request.getParameter("supprimer") != null) {
			try {
				int ret = interfaceArticle.supprimer(id);
				if(ret == 1) {
					request.setAttribute("succes", "la suppression d'article terminé avec succès");
				}
			} catch (DaoException e) {
				request.setAttribute("erreur", e.getMessage());
			} catch (BeanException e) {
				request.setAttribute("erreur", e.getMessage());
			}
			RequestDispatcher rd = request.getRequestDispatcher("accueil");
			rd.forward(request, response);
	    }
	}

}
