<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%
    // request, response 사용 가능
    //  jsp도 서블릿으로 자동으로 변환되기 때문에 사용 가능하다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("##### MemberSaveServlet.service #####");
    // HTML Form에서 전송한 값을 request에서 getParameter로 가져온다.
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    // 가져온 Member정보를  Memeber객체에 담고 저장한다.
    Member member = new Member(username, age);
    memberRepository.save(member);
%>

<html>
<head>
 <title>Title</title>
</head>
    <body>
    성공
    <ul>
        <li>id=${member.id }</li>
        <li>username=${member.username }</li>
        <li>age=${member.age }</li>
    </ul>
    <a href="/index.html">메인</a>
    </body>
</html>