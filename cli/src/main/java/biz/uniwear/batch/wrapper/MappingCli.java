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
    private List<String> inargs = new ArrayList<>();

    @Option(names = {"-db"}, description = "DB Connection Profile")
    private List<String> dbargs = new ArrayList<>();

    @Option(names = {"-out"}, description = "Output File")
    private List<String> outargs = new ArrayList<>();

    public static void main(String[] args) {
        CommandLine.call(new MappingCli(), args);
    }

    @Override
    public Void call() {
        logger.traceEntry();


        MappingParams mappingParams = new MappingParams.MappingParamsBuilder()
                .Inputs(MappingParam.CreateInputs(inargs))
                .Connections(MappingParam.CreateConnections(dbargs))
                .Outputs(MappingParam.CreateOutputs(outargs))
                .build();




        logger.debug("Configuration Created");
        MappingClass mappingClass = new MappingClass();
        mappingClass.run(mappingClassName, mappingParams.getInvokeParams());

        logger.traceExit();
        System.exit(0);
        return null;
    }


}
