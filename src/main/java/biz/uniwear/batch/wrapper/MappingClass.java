package biz.uniwear.batch.wrapper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MappingClass {

    private static final Logger logger = LogManager.getLogger();

    void run(String mappingClassName, Object[] args) {
        logger.traceEntry();
        String mappingFullClass = "biz.uniwear.batch.mappings." + mappingClassName;

        Class<?> c = null;
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
            logger.error("Cannot instantiate: {}", mappingFullClass);
            System.exit(1);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.catching(e);
        }
        logger.debug("Instantiated object: {}", t);


        callMethod(t,"run", args);
        logger.traceExit();

    }

    private static void callMethod(Object receiver, String mName, Object[] params) {
        logger.traceEntry();

        if (receiver == null || mName == null) {
            return;
        }
        Class<?> cls = receiver.getClass();
        Method[] methods = cls.getMethods();
        Method m = null;
        mloop: for (Method method : methods) {
            if (!mName.equals(method.getName())) {
                continue;
            }
            Class<?>[] pTypes = method.getParameterTypes();
            if (params == null && pTypes == null) {
                m = method;
                break;
            } else if (params == null || pTypes == null
                    || pTypes.length != params.length) {
                continue;
            }

            for (int i = 0; i < params.length; ++i) {
                if (!pTypes[i].isAssignableFrom(params[i].getClass())) {
                    continue mloop;
                }
            }
            m = method;
        }
        if (m != null) {
            try {
                m.invoke(receiver, params);
            } catch (Exception t) {
                logger.catching(t);
            }
        }
        logger.traceExit();
    }
}
