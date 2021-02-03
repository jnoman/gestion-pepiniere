package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

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


@WebServlet("/Detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InterfaceArticle interfaceArticle;

	@Override
	public void init() throws ServletException {
		Database database = Database.getInstance();
		this.interfaceArticle = database.getInterfaceArticle();
	}
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") != null) {
			try {
				Article article = interfaceArticle.rechercherById(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("article", article);
				if(article !=null) {
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead = -1;
					while ((bytesRead = article.getImage().read(buffer)) != -1) {
						outputStream.write(buffer, 0, bytesRead);
					}
					byte[] imageBytes = outputStream.toByteArray();
					String image = Base64.getEncoder().encodeToString(imageBytes);
					
					request.setAttribute("image", image);
				}
				
			}
			catch (DaoException e) {
				request.setAttribute("erreur", e.getMessage());
			} catch (BeanException e) {
				request.setAttribute("erreur", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/detail.jsp").forward(request, response);
		} else {
			response.sendRedirect("index");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
