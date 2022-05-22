package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> { // 반환타입 정의
    private final LogTrace trace;

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
