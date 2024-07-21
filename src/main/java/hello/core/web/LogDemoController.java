package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // 생성자로 의존관계주입
public class LogDemoController {

    private final LogDemoService logDemoService;
    // 방법 1. Provider 사용
    // MyLogger가 아닌 Dependency Lookup을 할 수 있는 Provider가 주입
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    // 방법 2. MyLogger에 Proxy 설정
    private final MyLogger myLogger;


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger);
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testID");
        return "ok";
    }
}
