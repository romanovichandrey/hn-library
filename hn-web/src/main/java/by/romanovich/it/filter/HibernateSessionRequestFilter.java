package by.romanovich.it.filter;

import by.romanovich.it.pojos.Adress;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.service.service.UserService;
import by.romanovich.it.service.service.UserServiceImpl;
import by.romanovich.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Romanovich Andrei
 * @version 1.0
 */
public class HibernateSessionRequestFilter implements Filter {

    private static Logger log = Logger.getLogger(HibernateSessionRequestFilter.class);

    public static final String HIBERNATE_SESSION_KEY = "hibernateSession";

    private HibernateUtil util = null;
    private UserService userService = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initializing filter");
        log.info("Obtaining SessionFactory from static HibernateUtil singleton");
        util = HibernateUtil.getUtil();
        log.info("Obtaining UserServiceImpl");
        userService = UserServiceImpl.getUserService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        Session session = null;
        Session disconnectSession = null;
        User user = null;
        HttpSession httpSession = ((HttpServletRequest) servletRequest).getSession(false);
        if(httpSession != null) {
            user = (User) httpSession.getAttribute("user");
            disconnectSession = (Session) httpSession.getAttribute(HIBERNATE_SESSION_KEY);
            if(user != null && disconnectSession != null) {
                //SessionImpl tempSession = (SessionImpl)disconnectSession;
                try {
                    //disconnectSession.reconnect(tempSession.connection());
                    disconnectSession = util.getSession();
                    disconnectSession.beginTransaction();
                    boolean result = user.equals(userService.getUserById(user.getId()));
                    disconnectSession.getTransaction().commit();
                    //disconnectSession.disconnect();
                    if(result) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        util.sessionClose();
                    }
                } catch (ServiceExeption e) {
                    log.info("Cannot get user by id", e);
                    session.getTransaction().rollback();
                }
            } else {
                String login = servletRequest.getParameter("login");
                String password = servletRequest.getParameter("password");
                String firstname = servletRequest.getParameter("firstname");
                String lastname = servletRequest.getParameter("lastname");
                String telephone = servletRequest.getParameter("telephone");
                String email = servletRequest.getParameter("email");
                String street = servletRequest.getParameter("street");
                String city = servletRequest.getParameter("city");
                String state = servletRequest.getParameter("state");
                String country = servletRequest.getParameter("country");

                if(login != null && password != null && firstname != null && lastname != null &&
                        telephone != null && email != null && street != null && city != null &&
                        country != null) {
                    Adress adress = new Adress(street, city, state, country);
                    user = new User(firstname, lastname, telephone, email, login, password);
                    user.setAdress(adress);
                    adress.setUser(user);
                    boolean status;
                    try {
                        session = util.getSession();
                        session.beginTransaction();
                        status = userService.saveUser(user);
                        session.getTransaction().commit();
                        httpSession = ((HttpServletRequest) servletRequest).getSession();
                        httpSession.setAttribute(HIBERNATE_SESSION_KEY, session);
                        //session.disconnect();
                        if(status) {
                            httpSession.setAttribute("user", user);
                            filterChain.doFilter(servletRequest, servletResponse);
                            util.sessionClose();
                        }
                    } catch (ServiceExeption e) {
                        log.error("Cannot save user:", e);
                        session.getTransaction().rollback();
                    }

                } else if(login != null && password != null) {
                    try {
                        session = util.getSession();
                        session.beginTransaction();
                        user = userService.getUserByLoginAndPassword(login, password);
                        session.getTransaction().commit();
                        session.disconnect();
                        httpSession.setAttribute(HIBERNATE_SESSION_KEY, session);
                        if(user != null) {
                            httpSession.setAttribute("user", user);
                            filterChain.doFilter(servletRequest, servletResponse);
                            util.sessionClose();
                        } else {
                            servletRequest.getServletContext().getRequestDispatcher("/WEB-INF/views/registr.jsp").
                                    forward(servletRequest, servletResponse);
                        }
                    } catch (ServiceExeption e) {
                        log.info("Cannot get user by login and password", e);
                        session.getTransaction().rollback();
                    }
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
