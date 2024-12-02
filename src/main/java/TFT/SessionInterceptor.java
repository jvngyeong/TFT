package TFT;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("auth");
        String popup = request.getParameter("popup");
        if (user == null) {
            response.sendRedirect("/login/loginForm?popup="+popup);
            return false;
        }
        return true;
    }
}

