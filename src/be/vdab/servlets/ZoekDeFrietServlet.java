package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.repositories.OpenGesloten;

/**
 * Servlet implementation class ZoekDeFrietServlet
 */
@WebServlet("/zoekdefriet.htm")
public class ZoekDeFrietServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "WEB-INF/JSP/zoekdefriet.jsp";
	private static final String REDIRECT_URL = "%s/zoekdefriet.htm";
	private static final String OPENDEUREN = "opendeuren";
	private Map<Long, String> deuren;
	private static final String FRIETLOCATIE = "frietlocatie";
	private static final int aantalDeuren = 7;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OpenGesloten deurenDatabase = new OpenGesloten(aantalDeuren);
		deuren = deurenDatabase.getDeuren();
		
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Set<Long> openDeuren = (Set<Long>) session.getAttribute(OPENDEUREN);
		Long frietLocatie = (Long) session.getAttribute(FRIETLOCATIE);
		if(openDeuren != null){
		for (Long openDeur :  openDeuren) {
			if (openDeur == frietLocatie){
				deuren.replace(openDeur, "gevonden");
			} else {
				deuren.replace(openDeur, "deuropen");
			}
		}}
		request.setAttribute("deuren", deuren);
		request.setAttribute("aantaldeuren", aantalDeuren);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("volgnummer") != null) {
			Long volgnummer = Long.parseLong(request.getParameter("volgnummer"));
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Set<Long> openDeuren = (Set<Long>) session.getAttribute(OPENDEUREN);
			if (openDeuren == null) {
				openDeuren = new LinkedHashSet<>();
				session.setAttribute(FRIETLOCATIE, (long)(int) (Math.random() * aantalDeuren + 1));
			}
			openDeuren.add(volgnummer);
			session.setAttribute(OPENDEUREN, openDeuren);

		}
		if (request.getParameter("nieuwspel") != null) {
			HttpSession session = request.getSession();
			Set<Long> openDeuren = new LinkedHashSet<>();
			
			session.setAttribute(OPENDEUREN, openDeuren);
			session.setAttribute(FRIETLOCATIE, (long)(int) (Math.random() * aantalDeuren + 1));
		}
		response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
	}
}
