package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

@WebServlet("/Rechercher")
public class Rechercher extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private InterfaceArticle interfaceArticle;

	@Override
	public void init() throws ServletException {
		Database database = Database.getInstance();
		this.interfaceArticle = database.getInterfaceArticle();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Article> list = interfaceArticle.rechercher(request.getParameter("recherche"));
			request.setAttribute("articles", list);
			List<String> listImage = new ArrayList<String>();
			for (Article article : list) {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				 
				while ((bytesRead = article.getImage().read(buffer)) != -1) {
				    outputStream.write(buffer, 0, bytesRead);
				}
				 
				byte[] imageBytes = outputStream.toByteArray();
				 
				listImage.add(Base64.getEncoder().encodeToString(imageBytes));
			}
			
			request.setAttribute("images", listImage);
		}
		catch (DaoException e) {
			request.setAttribute("erreur", e.getMessage());
		} catch (BeanException e) {
			request.setAttribute("erreur", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(request, response);
	}

}
