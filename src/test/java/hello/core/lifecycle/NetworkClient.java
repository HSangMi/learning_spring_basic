package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// InitializingBean(초기화 빈) 인터페이스 상속
// DisposableBean
public class NetworkClient implements InitializingBean, DisposableBean {
    public String url;
    public NetworkClient() {
        System.out.println("생성자 호출, url : " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect url : " + url);
    }
    public void disconnect() {
        System.out.println("disconnect url : " + url);
    }
    public void call(String message){
        System.out.println("call url : " + url + " message : " + message);
    }

    // 의존관계 주입이 끝나면 실행하는 메서드
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("# afterPropertiesSet start ");
        connect();
        call("초기화 연결메세지");
        System.out.println("# afterPropertiesSet end ");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("# destroy start");
        disconnect();
        System.out.println("# destroy end ");
    }
}
