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

@WebServlet("/sausverwijderen.htm")
public class SausVerwijderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sauzen.jsp"; 
	private static final String REDIRECT_URL = "%s/sauzen.htm";
	private final transient SausRepository sausRepository = new SausRepository();

	@Resource(name = SausRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		sausRepository.setDataSource(dataSource);
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("testPost");
		if (request.getParameterValues("nummer") != null){
		String[] teVerwijderenSauzen = request.getParameterValues("nummer");
		for (String saus : teVerwijderenSauzen) {
					sausRepository.verwijder(Long.parseLong(saus));
					
		}
		response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
		} else {
			request.setAttribute("foutVerwijder", "Geen saus aangeduid");
			this.doGet(request, response);
		}
	}
	
}
