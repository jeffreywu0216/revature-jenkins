package com.ex.eflea_springboot.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = {"/*"})
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest)request;
        resp.addHeader("Access-Control-Allow-Origin", "*");

        //check preflight
        /*preflights send certain headers
            Access-Control-Request-Method
            Access-Control-Request-Header
            Origin
         */

        if (req.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(req.getMethod())) {
            resp.addHeader("Access-Control-Allow-Method", "GET, PUT, POST, DELETE");
            resp.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
