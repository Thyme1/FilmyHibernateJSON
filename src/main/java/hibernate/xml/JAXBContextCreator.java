package hibernate.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class JAXBContextCreator {

        public JAXBContext create(String packageName, ClassLoader classLoader) throws JAXBException {
            return JAXBContext.newInstance(packageName, classLoader);
        }

}
