package biz.uniwear.batch.wrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


@Command(description = "Executes Uniwear Mapping Classes.", name = "Uniwear Batch Cli", mixinStandardHelpOptions = true, version = "MappingCli 1.0")
class MappingCli implements Callable<Void> {

    private static final Logger logger = LogManager.getLogger();


    @Parameters(index = "0", paramLabel = "Mapping", description = "Maping Class")
    private String mappingClassName;

    @Option(names = {"-in"}, description = "Input File")
    private List<String> inargs = new ArrayList<String>();

    @Option(names = {"-out"}, description = "Output File")
    private List<String> outargs = new ArrayList<String>();

    public static void main(String[] args) {
        CommandLine.call(new MappingCli(), args);
    }

    @Override
    public Void call() {

        MappingConfig mappingConfig = new MappingConfig.MappingConfigBuilder()
                .mapping(mappingClassName)
                .inargs(inargs)
                .outargs(outargs)
                .build();
        logger.debug("Configuration Created");
        MappingWrapper mappingWrapper = new MappingWrapper();
        mappingWrapper.main(mappingConfig);

        System.exit(0);
        return null;
    }
}
