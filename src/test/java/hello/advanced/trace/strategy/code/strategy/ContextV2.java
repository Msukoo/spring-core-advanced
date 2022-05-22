package hello.advanced.trace.strategy.code.strategy;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *  필드로 주입받지 않고 메서드의 파라미터로 전달
 *  execute() 가 호출될 때마다 파라미터로 전달받음.
 */
@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy){
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        strategy.call();
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
