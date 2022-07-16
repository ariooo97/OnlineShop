package ir.largesize.OnlineShop.config.filters;

import ir.largesize.OnlineShop.config.JwtTokenUtil;
import ir.largesize.OnlineShop.entities.people.User;
import ir.largesize.OnlineShop.helper.Exceptions.JwtTokenException;
import ir.largesize.OnlineShop.helper.uimodels.people.UserVm;
import ir.largesize.OnlineShop.services.people.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtRequestFilter implements Filter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    private List<String> excludeUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludeUrl = new ArrayList<>();
        excludeUrl.add("/api/user/login");
        excludeUrl.add("/api/color/");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            String url = (((HttpServletRequest) servletRequest).getRequestURI().toLowerCase());
            if (excludeUrl.stream().anyMatch(x -> url.equals(x))) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            String requestTokenHeader = ((HttpServletRequest) servletRequest).getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer"))
                throw new JwtTokenException("request token header dose not set");

            String token = requestTokenHeader.substring(7);
            String userName = jwtTokenUtil.getUserNameFromToken(token);

            if (userName == null)
                throw new JwtTokenException("username can not resolve");
            UserVm userVm = new UserVm(userService.getByUserName(userName));
            if (!jwtTokenUtil.validateToken(token, userVm))
                throw new JwtTokenException("invalid token");
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (JwtTokenException ex) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");

        } catch (Exception ex) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());

        }
    }
}
