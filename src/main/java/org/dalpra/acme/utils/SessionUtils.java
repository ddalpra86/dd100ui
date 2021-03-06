package org.dalpra.acme.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	public static HttpSession getSession() {
	    return (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
	                                     .getSession(false);
	  }

	  // Gets Http servlet for user request information.
	  public static HttpServletRequest getRequest() {
	    return (HttpServletRequest) FacesContext.getCurrentInstance()
	                                            .getExternalContext().getRequest();
	  }

	  public static String getJwtToken() {
	    return (String) getSession().getAttribute("jwt");
	  }

}
