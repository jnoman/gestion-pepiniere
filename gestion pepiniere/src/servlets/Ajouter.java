package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import beans.Article;
import beans.BeanException;
import daoArticle.DaoException;
import daoArticle.Database;
import daoArticle.InterfaceArticle;

@WebServlet("/Ajouter")
@MultipartConfig(maxFileSize = 16177215)
public class Ajouter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InterfaceArticle interfaceArticle;

	@Override
	public void init() throws ServletException {
		Database database = Database.getInstance();
		this.interfaceArticle = database.getInterfaceArticle();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/ajouter.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		
		InputStream inputStream = null;
        Part filePart = request.getPart("image");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        } 
        try {
			int ret = interfaceArticle.ajouter(new Article(nom, description, inputStream, quantite));
			if(ret == 1) {
				request.setAttribute("succes", "ajouter l'article terminé avec succès");
			}
		} catch (DaoException e) {
			request.setAttribute("erreur", e.getMessage());
		} catch (BeanException e) {
			request.setAttribute("erreur", e.getMessage());
		}
        doGet(request, response);
	}

}
