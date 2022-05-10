package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    private ThreadLocal<String > nameStore = new ThreadLocal<>();

    public String logic(String name){
        log.info("저장 name={} --> nameStore={}", name, nameStore.get());
        String localField = name;
        nameStore.set(name); // 저장
        sleep(1000);
        log.info("조회 nameStore={}, localField={}", nameStore.get(), localField);
        return nameStore.get(); // 저장한 데이터 꺼내기
        // 값 제거 : nameStore.remove() - 해당 쓰레드가 쓰레드 로컬을 모두 사용하고 나면 ThreadLocal.remove() 로 로컬에 저장된 값을 제거해 주어야 함
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
