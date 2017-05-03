package be.vdab.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.enteties.Adres;
import be.vdab.enteties.Gemeente;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(urlPatterns="/index.htm", name = "indexservlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("adres", new Adres("Fratersplein", "9",new Gemeente("Gent", 9000)));
		int dag = LocalDateTime.now().getDayOfWeek().getValue();
		request.setAttribute("isOpen", dag == 1 || dag == 5 ? false : true);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
