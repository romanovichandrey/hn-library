package by.romanovich.it.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private static Logger log = Logger.getLogger(CharsetFilter.class);

    private String encoding = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("charset");
        if(encoding == null) {
            encoding = "UTF-8";
        }
        log.info("Initial parameter encoding" + encoding);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
