package hello.core.singleton;

public class SingletonService {

    // 1. 자기 자신을 내부에 private static으로 선언.
    // static => jvm에 구동될 때 static영역에 instance를 미리 하나 생성함
    private static final SingletonService instance = new SingletonService();

    // 2. 객체 인스턴스가 필요하면, 이 static 메소드를 통해서만 조회하도록 허용(public)
    public static SingletonService getInstance(){
        return instance;
    }
    // 3. private 생성자로 외부에서 생성을 막음
    private SingletonService(){
    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
