package com.fsa.firststepapp.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Filtru pentru gestionarea cererilor CORS (Cross-Origin Resource Sharing).
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
    /**
     * Implementarea metodei principale a filtrului pentru gestionarea cererilor CORS.
     *
     * @param req Cererea primită.
     * @param res Răspunsul care va fi trimis.
     * @param chain Lanțul de filtre.
     * @throws IOException      Excepție în cazul unor erori de IO.
     * @throws ServletException Excepție în cazul unor erori de servlet.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        // Configurarea header-elor pentru CORS
        response.setHeader("access-control-allow-origin", "*");
        response.setHeader("access-control-allow-methods", "*");
        response.setHeader("access-control-max-age", "3600");
        response.setHeader("access-control-allow-headers", "*");

        // Verificarea cererii prefligh (OPTIONS) și gestionarea corespunzătoare
        if ("options".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Propagarea cererii în lanțul de filtre
            chain.doFilter(req, res);
        }
    }

    /**
     * Inițializarea filtrului.
     *
     * @param filterconfig Configurația filtrului.
     */
    @Override
    public void init(FilterConfig filterconfig) {
    }

    /**
     * Distrugerea filtrului.
     */
    @Override
    public void destroy() {
    }
}
