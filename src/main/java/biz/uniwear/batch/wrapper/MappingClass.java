package biz.uniwear.batch.wrapper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

class MappingClass {

    private static final Logger logger = LogManager.getLogger();

    void run(String mappingClassName, Object[] args) {
        logger.traceEntry();
        String mappingFullClass = "biz.uniwear.batch.mappings." + mappingClassName;

        Class c = null;
        Object t = null;


        try {
            c = Class.forName(mappingFullClass);
        } catch (ClassNotFoundException e) {

            logger.error("Cannot find: {}", mappingFullClass);
            System.exit(1);

        }
        logger.debug("Loaded class: {}", mappingFullClass);

        try {
            t = c.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            logger.error("Cannot create {} Object", mappingFullClass);
            System.exit(1);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.catching(e);
        }


        Method[] allMethods = c.getDeclaredMethods();
        for (Method m : allMethods) {
            String mname = m.getName();
            if (!mname.startsWith("run")) {
                continue;
            }

            Method method = c.getDeclaredMethod("run", parameterTypes);

            logger.debug("Passing parameters: {}", Arrays.toString(args));

//            Type[] pType = m.getGenericParameterTypes();
//            m.setAccessible(true);

            try {
                switch (args.length) {
                    case 1:
                        logger.debug("Invoking {}: {}", mname, Arrays.toString(args));
                        m.invoke(t, args[0]);
                        break;
                    case 2:
                        logger.debug("Invoking {}: {}", mname, Arrays.toString(args));
                        m.invoke(t, args[0], args[1]);
                        break;
                    case 3:
                        logger.debug("Invoking {}: {}", mname, Arrays.toString(args));
                        m.invoke(t, args[0], args[1], args[2]);
                        break;
                    case 4:
                        logger.debug("Invoking {}: {}", mname, Arrays.toString(args));
                        m.invoke(t, args[0], args[1], args[2], args[3]);
                        break;
                    case 5:
                        logger.debug("Invoking {}: {}", mname, Arrays.toString(args));
                        m.invoke(t, args[0], args[1], args[2], args[3], args[4]);
                        break;
                    default:
                        logger.error("Invoking {} with incorrect parameters: {}", mname, Arrays.toString(args));
                        break;
                }
            } catch (IllegalAccessException e) {
                logger.error("Invoking {} failed: {}", mname, e.getMessage());
                System.exit(1);

            } catch (InvocationTargetException e) {
                logger.error("Invoking {} failed: {}", mname, e.getCause().getMessage());
                System.exit(1);
            }


            logger.traceExit();
        }

    }
}
