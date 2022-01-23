package hello.servlet.domain.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); // 쿼리스트링으로 넘어온 값을 사용하는 방법
        System.out.println("username = " + username);

        response.setContentType("text/plain");  // http의 header에 데이터가 들어감
        response.setCharacterEncoding("utf-8"); // http의 header에 데이터가 들어감
        response.getWriter().write("hello " + username); // http Response의 body에 데이터가 들어감
    }
}

