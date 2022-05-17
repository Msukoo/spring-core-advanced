package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId){
        // 너무 지저분한 코드 엄청난 수작업
        // 핵심기능은 간단하지만 부가기능인 로그추적기때문에 코드가 복잡해짐
        // -> 이 문제를 효율적으로 처리하기 위해서는?
        // -> 변하는 부분(핵심기능)과 변하지 않는 부분(부가기능)을 분리하여 모듈화 한다.
        // --> 동일한 패턴으로 템플릿 메소드를 만든다!!
        TraceStatus status = null;
        try{
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        }catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 한다. 나가는 예외는 건들지 않아야 함.
        }
    }
}
