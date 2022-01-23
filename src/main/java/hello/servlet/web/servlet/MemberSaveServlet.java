package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("##### MemberSaveServlet.service #####");
        // HTML Form에서 전송한 값을 request에서 getParameter로 가져온다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 가져온 Member정보를  Memeber객체에 담고 저장한다.
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 브라우저에게 응답으로 돌려줄 response 객체의 세팅을 한다.
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w=  response.getWriter();

        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");

    }
}
