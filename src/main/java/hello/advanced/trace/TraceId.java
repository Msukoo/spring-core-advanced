package hello.advanced.trace;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Getter
public class TraceId {

    private String id;
    private int level;

    public TraceId(){
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level){
        this.id = id;
        this.level = level;
    }

    private String createId(){
        return UUID.randomUUID().toString().substring(0,8); // UUID의 앞 8자리만 사용
    }

    public TraceId crateNextId(){
        return new TraceId(id, level + 1);
    }

    public TraceId cratePreviousId(){
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel(){
        return level == 0;
    }
}
