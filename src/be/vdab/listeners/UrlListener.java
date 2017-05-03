/*
 * package be.vdab.listeners;
 * 
 * import java.util.Arrays; import java.util.Set; import
 * java.util.concurrent.ConcurrentHashMap; import
 * java.util.concurrent.CopyOnWriteArraySet; import
 * java.util.concurrent.atomic.AtomicInteger;
 * 
 * import javax.servlet.ServletContextEvent; import
 * javax.servlet.ServletContextListener; import
 * javax.servlet.ServletRequestEvent; import
 * javax.servlet.ServletRequestListener; import
 * javax.servlet.annotation.WebListener; import
 * javax.servlet.http.HttpServletRequest;
 * 
 * 
 * @WebListener public class UrlListener implements ServletContextListener,
 * ServletRequestListener { private static final String AANTAL_REQUESTS =
 * "aantalRequests"; private static final Set<String> TE_NEGEREN_FILE_EXTENSIES
 * = new
 * CopyOnWriteArraySet<String>(Arrays.asList("png","css","jpg","gif","js","ico")
 * );
 * 
 * public UrlListener() { // TODO Auto-generated constructor stub }
 * 
 * public void requestDestroyed(ServletRequestEvent arg0) { // TODO
 * Auto-generated method stub }
 * 
 * public void requestInitialized(ServletRequestEvent event) { if
 * (event.getServletRequest() instanceof HttpServletRequest){ HttpServletRequest
 * request = (HttpServletRequest)event.getServletRequest(); String url =
 * request.getRequestURI(); int indexVanPunt = url.lastIndexOf("."); boolean
 * verwerkRequest = true; if (indexVanPunt != -1){ String fileExtensie =
 * url.substring(indexVanPunt + 1).toLowerCase();
 * if(TE_NEGEREN_FILE_EXTENSIES.contains(fileExtensie)){ verwerkRequest = false;
 * } } if (verwerkRequest) { int index = url.indexOf(";jsessionid="); if (index
 * != -1) { url = url.substring(0, index); }
 * 
 * @SuppressWarnings("unchecked") ConcurrentHashMap<String, AtomicInteger>
 * statistiek = (ConcurrentHashMap<String, AtomicInteger>)
 * request.getServletContext() .getAttribute(AANTAL_REQUESTS); AtomicInteger
 * aantalReedsAanwezig = statistiek.putIfAbsent(url, new AtomicInteger(1)); if
 * (aantalReedsAanwezig != null) { aantalReedsAanwezig.incrementAndGet(); } } }
 * 
 * }
 * 
 * 
 * public void contextDestroyed(ServletContextEvent arg0) { // TODO
 * Auto-generated method stub }
 * 
 * public void contextInitialized(ServletContextEvent event) {
 * event.getServletContext().setAttribute(AANTAL_REQUESTS, new
 * ConcurrentHashMap<String, AtomicInteger>()); }
 * 
 * }
 */