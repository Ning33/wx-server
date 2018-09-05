package cn.hnisi.wx.server.filter;

import cn.hnisi.wx.core.utils.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class HttpServletFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(HttpServletRequest.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ServletRequest requestWrapper = null;
        logger.info("------------------进入过滤器-----------------");
        if(request instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper(httpRequest);
        }

        if(requestWrapper == null) {
            chain.doFilter(httpRequest, httpResponse);
        } else {
            chain.doFilter(requestWrapper, httpResponse);
        }
    }

    @Override
    public void destroy() {
    }

}
