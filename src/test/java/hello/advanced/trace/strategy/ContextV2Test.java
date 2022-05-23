package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1(){
        // 하나의 Context만 생성 가능 -> 메서드의 파라미터로 전달하기 때문에
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }

    /**
     * [ 템플릿 콜백 패턴 ]
     * - 스프링에서 쓰는 용어
     * - 전략패턴에서 'Context'-> 템플릿 역할, 'Strategy'-> 콜백으로 넘어옴
     * - 필요에 따라 즉시 실행하거나 나중에 실행할 수 있음
     * - 전략패턴에서 템플릿과 콜백 부분이 강조된 패턴.
     */

    @Test
    void strategyV2(){
        // 하나의 Context만 생성 가능 -> 메서드의 파라미터로 전달하기 때문에
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new Strategy(){
            @Override
            public void call() {
                log.info("비지니스 로직 실행1");
            }
        });
        contextV2.execute(()-> log.info("비지니스 로직 실행2")); // 콜백 : 나중에 실행 할 코드를 넘겨줌
    }
}
