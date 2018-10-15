package biz.uniwear.batch.wrapper;

import com.typesafe.config.ConfigFactory;
import com.typesafe.config.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

enum MappingConfig
{
    INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private final Config config = ConfigFactory.load();


    Config getConfig(String path) {

        return config.getConfig(path);
    }


}
