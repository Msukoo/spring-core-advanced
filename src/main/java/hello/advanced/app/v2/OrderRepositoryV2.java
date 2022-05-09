package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {
        // 저장 로직
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId, "OrderRepositoryV1.save()");
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외발생!");
            }
            sleep(1000); // 상품을 저장하는데 1초정도 걸린다고 가정.
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 한다. 나가는 예외는 건들지 않아야 함.
        }
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
