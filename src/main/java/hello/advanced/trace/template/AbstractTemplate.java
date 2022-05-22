package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> { // 반환타입 정의
    private final LogTrace trace;

    // 작업에서 알고리즘의 골격을 정의하고 일부 단계를 하위 클래스로 연기
    // -> 하위 클래스가 알고리즘의 구조를 변경하지 않고도 알고리즘의 특정 단게를 재정의할 수 있음.

    // But
    // 상속을 사용하기 때문에 상속의 단점을 모두 안고감.
    // 자식 클래스 입장에서는 부모클래스의 기능을 전혀 사용하지 않는데 그럼에도 불구하고 탬플릿 메서드 패턴을 위해 상속하고 있음 (컴파일 시점에 강하게 결합되는 문제)
    // 이러한 강한 결합은 좋은 설계가 아님!!
    // 부모클래스를 수정하면 자식 클래스에도 영향을 줄 수 있다.

    public AbstractTemplate(LogTrace trace){
        this.trace = trace;
    }

    public T execute(String message){ // 로그에 출력할 메세지를 파라미터로 입력받는다.
        TraceStatus status = null;
        try{
            status = trace.begin(message);
            T result = call();
            trace.end(status);
            return result;
        }catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 한다. 나가는 예외는 건들지 않아야 함.
        }
    }

    protected abstract T call();
}
