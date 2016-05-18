package by.romanovich.it.filter;

import by.romanovich.it.pojos.User;
import by.romanovich.it.service.service.UserService;
import by.romanovich.it.service.service.UserServiceImpl;
import by.romanovich.it.util.HibernateUtil;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 */
public class AuthorizationFilter implements Filter {

    private static Logger log = Logger.getLogger(AuthorizationFilter.class);

    private UserService userService = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = UserServiceImpl.getUserService();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        User user;
        HttpSession httpSession = ((HttpServletRequest) request).getSession(false);
        if(httpSession != null) {
            user = (User) httpSession.getAttribute("user");
            if(user != null) {
                chain.doFilter(request, response);
                HibernateUtil.getUtil().sessionClose();
            } else {
                chain.doFilter(request, response);
            }
        } else {
            ((HttpServletRequest) request).getSession().setAttribute("user", null);
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        HibernateUtil.destroy();
    }
}
