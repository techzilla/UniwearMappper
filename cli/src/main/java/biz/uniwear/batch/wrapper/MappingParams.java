package biz.uniwear.batch.wrapper;

import com.altova.io.Input;
import com.altova.io.Output;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Setter
@Getter
@Builder
@ToString
class MappingParams {

    private static final Logger logger = LogManager.getLogger();

    private List<Input> Inputs;
    private List<Connection> Connections;
    private List<Output> Outputs;

    Object[] getInvokeParams() {
        logger.traceEntry();

        List<Object> objArgs = Stream.of(this.Inputs, this.Connections, this.Outputs)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        Object[] objParams = objArgs.toArray(new Object[0]);
        logger.debug("Collected {} parameters: {}", objArgs.size(), Arrays.toString(objParams));

        logger.traceExit(objParams);
        return objParams;
    }
}
