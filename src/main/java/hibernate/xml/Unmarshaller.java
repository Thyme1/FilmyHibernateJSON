package hibernate.xml;

import hibernate.model.*;


import javax.naming.spi.ObjectFactory;
import javax.security.auth.login.Configuration;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.InputStream;

public class Unmarshaller {

    private  JAXBContextCreator contextCreator = new JAXBContextCreator();
    private  ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

    public Actors unmarshallConfiguration(InputStream stream) {
        if (stream == null) {
            throw new IllegalArgumentException("Stream is NULL. Cannot read XML.");
        }
        try {
            JAXBContext ctx;
            try {
                ctx = contextCreator.create("hibernate.model", classLoadingUtil.getClassloader());
            } catch (JAXBException e) {
                ctx = null;
            }
            if (ctx == null) {
                ctx = contextCreator.create("hibernate.model", ObjectFactory.class.getClassLoader());
            }
            javax.xml.bind.Unmarshaller um = ctx.createUnmarshaller();
            @SuppressWarnings("unchecked")
            JAXBElement<Actors> el = (JAXBElement<Actors>) um.unmarshal(stream);
            return el.getValue();
        } catch (JAXBException ue) {
            throw new IllegalStateException("Cannot parse holidays XML file.", ue);
        }
    }

    public class JAXBContextCreator {
        public JAXBContext create(String packageName, ClassLoader classLoader) throws JAXBException {
            return JAXBContext.newInstance(packageName, classLoader);
        }
    }

}

