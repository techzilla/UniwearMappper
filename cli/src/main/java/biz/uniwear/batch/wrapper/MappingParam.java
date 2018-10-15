package biz.uniwear.batch.wrapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
public class MappingParam {
    private String mapping;
    private List<String> inargs;
    private List<String> outargs;
}
