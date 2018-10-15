//package biz.uniwear.batch.wrapper;
//
//        import com.typesafe.config.*;
//
//public class MappingConfigContext {
//    private Config config;
//
//    public MappingConfigContext(Config config) {
//        this.config = config;
//        config.checkValid(ConfigFactory.defaultReference(), "db");
//    }
//
//    public MappingConfigContext() {
//        this(ConfigFactory.load());
//    }
//
//    public void printSetting(String path) {
//        System.out.println("The setting '" + path + "' is: " + config.getString(path));
//    }
//}