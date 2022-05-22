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
        contextV2.execute(()-> log.info("비지니스 로직 실행2"));
    }
}
