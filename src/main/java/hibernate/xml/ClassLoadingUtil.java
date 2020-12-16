package hibernate.xml;

import org.apache.log4j.Logger;

public class ClassLoadingUtil {

    private static final Logger LOG = Logger.getLogger(ClassLoadingUtil.class.getName());

    /**
     * Loads the class by class name with the current threads context
     * classloader. If there occurs an exception the class will be loaded by
     * default classloader.
     *
     * @param className
     *            a {@link String} object.
     * @return a {@link Class} object.
     * @throws ClassNotFoundException
     *             if any.
     */
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        try {
            return Class.forName(className, true, getClassloader());
        } catch (Exception e) {
            LOG.warn("Could not load class with current threads context classloader. Using default. Reason: "
                    + e.getClass().getSimpleName() + ": " + e.getMessage());
            return Class.forName(className);
        }
    }

    /**
     * Returns the current threads context classloader.
     *
     * @see Thread#currentThread()
     * @return the current threads context classloader
     */
    public ClassLoader getClassloader() {
        return Thread.currentThread().getContextClassLoader();
    }
}

