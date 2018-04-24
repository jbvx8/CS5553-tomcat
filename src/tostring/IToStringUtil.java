package tostring;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Manager;

public interface IToStringUtil {
    public String toString(Contained contained);
    public String toString(Object obj, Container container);
    public String toString(Object obj, Manager manager);
}
