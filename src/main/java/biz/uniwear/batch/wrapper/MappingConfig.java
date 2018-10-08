package biz.uniwear.batch.wrapper;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class MappingConfig {
    private String mapping;
    private List<String> inargs;
    private List<String> outargs;
}
