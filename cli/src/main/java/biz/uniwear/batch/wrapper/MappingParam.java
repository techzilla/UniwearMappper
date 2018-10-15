package biz.uniwear.batch.wrapper;

import com.altova.io.Input;
import com.altova.io.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.typesafe.config.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

class MappingParam {
    private static final Logger logger = LogManager.getLogger();


    static List<Input> CreateInputs(List<String> inargs) {
        logger.traceEntry();

        logger.debug("Creating inputs for {} input arguments", inargs.size());

        List<Input> inputs = normalizePaths(inargs).stream()
                .map(MappingParam::getInputStream)
                .collect(Collectors.toList());

        logger.debug("Collected {} inputs", inputs.size());

        logger.traceExit(inputs);
        return inputs;


    }

    static List<Connection> CreateConnections(List<String> dbargs) {
        logger.traceEntry();

        logger.debug("Creating connections for {} connection arguments", dbargs.size());


        List<Connection> connections = dbargs.stream()
                .map(MappingParam::getConnection)
                .collect(Collectors.toList());

        logger.debug("Collected {} connections", connections.size());

        logger.traceExit(connections);
        return connections;
    }


    static List<Output> CreateOutputs(List<String> outargs) {
        logger.traceEntry();

        logger.debug("Creating outputs for {} output arguments", outargs.size());

        List<Output> outputs = normalizePaths(outargs).stream()
                .map(MappingParam::getOutputStream)
                .collect(Collectors.toList());

        logger.debug("Collected {} outputs", outputs.size());

        logger.traceExit(outputs);
        return outputs;

    }


    private static List<String> normalizePaths(List<String> IOStrings) {
        logger.traceEntry();

        List<String> ioStrings = IOStrings.stream()
                .map(n -> Paths.get(n).toAbsolutePath().normalize().toString())
                .collect(Collectors.toList());

        logger.traceExit(ioStrings);
        return ioStrings;
    }



    private static Input getInputStream(String inputString) {
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


    private static Connection getConnection(String DBString) {
        logger.traceEntry();

        Config dbConfig = MappingConfig.INSTANCE.getConfig("db." + DBString);
        String driver = dbConfig.getString("driver");
        String user = dbConfig.getString("user");
        String password = dbConfig.getString("password");
        String url = dbConfig.getString("url");


        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("Cannot find: {}", driver);
        }

        Connection connection = null;
        logger.debug("Creating Connection: {}; {}", url, user);

        try {
            connection = com.altova.db.Dbs.newConnection(url, user, password);
        } catch (Exception e) {
            logger.catching(e);
        }

        logger.traceExit(connection);
        return connection;
    }



    private static Output getOutputStream(String outputSting) {
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