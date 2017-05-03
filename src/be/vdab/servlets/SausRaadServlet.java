package be.vdab.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.enteties.Saus;
import be.vdab.repositories.SausRepository;

@WebServlet("/sausraden.htm")
public class SausRaadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sausraden.jsp";
	private static final String REDIRECT_URL = "%s/sausraden.htm";
	private final transient SausRepository sausRepository = new SausRepository();

	@Resource(name = SausRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		sausRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String saus = (String) session.getAttribute("saus");
		boolean[] lettersGeraden = (boolean[]) session.getAttribute("lettersGeraden");
		Integer pogingen = (Integer) session.getAttribute("pogingen");
		if (saus == null || lettersGeraden == null || pogingen == null) {
			List<Saus> sauzen = sausRepository.findAll();
			saus = sauzen.get((int) (Math.random() * sausRepository.findAll().size())).getNaam()
					.toUpperCase();
			pogingen = 0;
			lettersGeraden = new boolean[saus.length()];
			session.setAttribute("saus", saus);
			session.setAttribute("lettersGeraden", lettersGeraden);
			session.setAttribute("pogingen", pogingen);
		}

		String[] letters = new String[saus.length()];
		boolean gewonnen = true;
		for (int i = 0; i < saus.length(); i++) {

			if (lettersGeraden[i]) {
				letters[i] = String.valueOf(saus.charAt(i));
			} else {
				letters[i] = ".";
				gewonnen = false;
			}
		}
		if (pogingen >= 10) {
			request.setAttribute("saus", saus);
		}
		// System.out.println(pogingen);
		request.setAttribute("pogingen", pogingen);
		request.setAttribute("letters", letters);
		request.setAttribute("gewonnen", gewonnen);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String knop = request.getParameter("knop");

		boolean[] lettersGeraden = (boolean[]) session.getAttribute("lettersGeraden");
		if (knop.equals("raden")) {
			String saus = (String) session.getAttribute("saus");
			Integer pogingen = (Integer) session.getAttribute("pogingen");
			String letterString = request.getParameter("letter");
			if (letterString != null && letterString.length() == 1) {
				char letter = letterString.toUpperCase().charAt(0);
				boolean letterGevonden = false;
				for (int i = 0; i < saus.length(); i++) {

					if (saus.charAt(i) == letter) {
						lettersGeraden[i] = true;
						letterGevonden = true;
					}
				}
				if (!letterGevonden) {
					pogingen++;
				}
				session.setAttribute("lettersGeraden", lettersGeraden);
				session.setAttribute("pogingen", pogingen);
				response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
			} else {

				request.setAttribute("fout", "Geef enkel 1 letter in!");
				doGet(request, response);
			}
		}
		if (knop.equals("nieuwspel")) {
			session.removeAttribute("saus");
			session.removeAttribute("lettersGeraden");
			session.setAttribute("pogingen", 0);
			response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));

		}
	}

}
