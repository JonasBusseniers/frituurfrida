package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.SausRepository;

/**
 * Servlet implementation class SausServlet
 */

@WebServlet("/sauzen.htm")
public class SausServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sauzen.jsp";
	private static final String REDIRECT_URL = "%s/sauzen.htm";
	private final transient SausRepository sausRepository = new SausRepository();

	@Resource(name = SausRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		sausRepository.setDataSource(dataSource);
	}

	public SausServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("ingredient") != null) {
			if (request.getParameter("ingredient") != "") {
				if (sausRepository.find(request.getParameter("ingredient")).isPresent()) {
					request.setAttribute("sauzen", sausRepository.find(request.getParameter("ingredient")).get());
				} else {
					request.setAttribute("fout", "Geen Saus gevonden met dit ingredient");
				}
			} else {
				request.setAttribute("fout", "Dit veld is verplicht");
			}
		} else {
			request.setAttribute("sauzen", sausRepository.findAll());
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
