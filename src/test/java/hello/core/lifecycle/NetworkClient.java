package hello.core.lifecycle;


// InitializingBean(초기화 빈) 인터페이스 상속
// DisposableBean
public class NetworkClient{
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
    public void init() {
        System.out.println("# NetworkClient.init ");
        connect();
        call("초기화 연결메세지");
    }

    // 소멸 전 호출되는 메소드
    public void close() {
        System.out.println("# NetworkClient.close");
        disconnect();
    }
}
