package hello.servlet.domain.web.frontcontroller.v3;

import hello.servlet.domain.web.frontcontroller.ModelView;
import hello.servlet.domain.web.frontcontroller.MyView;
import hello.servlet.domain.web.frontcontroller.v2.ControllerV2;
import hello.servlet.domain.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.domain.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.domain.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.domain.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.domain.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.domain.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }


        @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("### FrontControllerServletV3.service ###");

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);    // request정보를 paramMap에 담아 컨트롤러 호출

        // 논리이름 new-form
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);

    }

    // 파라미터로 넘어온 논리명으로 뷰의 물리명을 만들어 MyView객체로 반환한다.
    // (viewPath를 가진 MyView는 뷰로의 forward가 가능하다)
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    // 컨트롤러를 호출할 때 request객체를 넘기지 않고 map을 넘기기위해,
    // map을 만들고 request의 모든 정보를 담는 메서드
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    @Override
    public void init() throws ServletException {
        System.out.println(" Init 시작####################################");
    }

    @Override
    public void destroy() {
        System.out.println(" Destoryyyyyy ##########################");
    }
}
