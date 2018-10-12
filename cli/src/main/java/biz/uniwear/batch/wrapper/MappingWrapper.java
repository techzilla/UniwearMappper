package biz.uniwear.batch.wrapper;

import com.altova.io.Input;
import com.altova.io.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MappingWrapper {

    private static final Logger logger = LogManager.getLogger();

    void main(MappingConfig mappingConfig) {

        logger.traceEntry();

        String mappingClassName = mappingConfig.getMapping();
        List<String> inargs = normalizePaths(mappingConfig.getInargs());
        List<String> outargs = normalizePaths(mappingConfig.getOutargs());

        List<Input> inStreams = getInputStreams(inargs);
        logger.debug("Collected {} inputStreams", inStreams.size());

        List<Output> outStreams = getOutputStreams(outargs);
        logger.debug("Collected {} outputStreams", outStreams.size());


        Connection connection = getConnection();

        List<Connection> connections = new ArrayList<>();
        connections.add(connection);
        logger.debug("Collected {} connections", connections.size());


        List<Object> objArgs = Stream.of(inStreams, connections, outStreams)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        Object[] objParams = objArgs.toArray(new Object[0]);
        logger.debug("Collected {} parameters: {}", objArgs.size(), Arrays.toString(objParams));

        MappingClass mappingClass = new MappingClass();
        mappingClass.run(mappingClassName, objParams);


        logger.traceExit();
    }

    private Connection getConnection() {
        logger.traceEntry();

        Connection connection = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Cannot find: org.mariadb.jdbc.Driver");
        }

        try {
            connection = com.altova.db.Dbs.newConnection("jdbc:mariadb://127.0.0.1/uniwear", "root", "root");
        } catch (Exception e) {
            logger.catching(e);
        }

        logger.traceExit(connection);
        return connection;
    }

    private List<String> normalizePaths(List<String> IOStrings) {
        logger.traceEntry();

        List<String> ioStrings = IOStrings.stream()
                .map(n -> Paths.get(n).toAbsolutePath().normalize().toString())
                .collect(Collectors.toList());

        logger.traceExit(ioStrings);
        return ioStrings;


    }

    private List<Input> getInputStreams(List<String> InputStings) {
        logger.traceEntry();

        List<Input> inputStreams = InputStings.stream()
                .map(this::getInputStream)
                .collect(Collectors.toList());
        logger.traceExit(inputStreams);
        return inputStreams;


    }

    private Input getInputStream(String inputString) {
        logger.traceEntry();

        Path path = Paths.get(inputString);
        if (!Files.isReadable(path)) {
            if (!Files.exists(path) && !Files.notExists(path)) {
                logger.error("File {} not readable: Check permissions", path.toString());
                System.exit(1);
            } else {
                logger.error("File {} not found: Correct path", path.toString());
                System.exit(1);
            }
        }
        Input inputStream = null;

        try {
            inputStream = com.altova.io.StreamInput.createInput(path.toString());
        } catch (Exception e) {
            logger.catching(e);
        }

        logger.traceExit(inputStream);
        return inputStream;
    }


    private List<Output> getOutputStreams(List<String> OutputStings) {
        logger.traceEntry();

        List<Output> outputStreams = OutputStings.stream()
                .map(this::getOutputStream)
                .collect(Collectors.toList());

        logger.traceExit(outputStreams);
        return outputStreams;


    }

    private Output getOutputStream(String outputSting) {
        logger.traceEntry();

        Path path = Paths.get(outputSting);


        if (!Files.isWritable(path)) {
            if (!Files.exists(path) && !Files.notExists(path)) {
                logger.error("File {} not writable: Check permissions", path.toString());
                System.exit(1);
            } else {
                try {
                    Files.createDirectories(path.getParent());
                } catch (IOException e) {
                    logger.error("Directory {} not creatable: Check permissions", path.getParent().toString());
                    System.exit(1);
                }
            }
        } else {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                logger.catching(e);
                System.exit(1);
            }
        }

        Output outputStream = null;

        try {
            Files.createFile(path);
            outputStream = new com.altova.io.FileOutput(path.toString());

        } catch (Exception e) {
            logger.catching(e);
        }


        logger.traceExit(outputStream);
        return outputStream;
    }

}

