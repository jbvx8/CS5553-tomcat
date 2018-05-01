package requestutil;

import javax.servlet.http.HttpServletRequest;

public interface IRequestUtility {
    public StringBuffer getRequestURL(HttpServletRequest request);
}
