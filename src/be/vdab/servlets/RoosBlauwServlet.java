package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RoosBlauwServlet
 */
@WebServlet("/roosblauw.htm")
public class RoosBlauwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/roosblauw.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getCookies() != null){
			
			for(Cookie cookie: request.getCookies()){
				
				if("kleur".equals(cookie.getName())){
					request.setAttribute("kleur", cookie.getValue());
					break;
				}
			}
		}
		
		request.getRequestDispatcher(VIEW).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String kleurCode = request.getParameter("roosblauw").equals("Roos") ? "pink" : "cyan";
		Cookie cookie = new Cookie("kleur",kleurCode);
		cookie.setMaxAge(30);
		response.addCookie(cookie);
		response.sendRedirect(request.getRequestURI());
	}

}
