package biz.uniwear.batch.wrapper;

import biz.uniwear.batch.wrapper.MappingConfig;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Parameters;


@Command(description = "Executes Uuniwear Mapping Classes.", name = "Uniwear MappingCli", mixinStandardHelpOptions = true, version = "MappingCli 1.0")
class MappingCli implements Callable<Void> {

    @Parameters(index = "0", paramLabel = "Mapping", description = "Maping Class")
    String mappingClassName;

    @Option(names = {"-in"}, description = "Input File")
    private List<String> inargs;

    @Option(names = {"-out"}, description = "Output File")
    private List<String> outargs;

    public static void main(String[] args) throws Exception {
        CommandLine.call(new MappingCli(), args);
    }

    @Override
    public Void call() throws Exception {

        MappingConfig mappingConfig = new MappingConfig.MappingConfigBuilder()
                .mapping(mappingClassName)
                .inargs(inargs)
                .outargs(outargs)
                .build();
        MappingWrapper mappingWrapper = new MappingWrapper();
        mappingWrapper.main(mappingConfig);
        return null;
    }
}
