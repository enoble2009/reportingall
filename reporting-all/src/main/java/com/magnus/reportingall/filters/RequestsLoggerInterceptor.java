package com.magnus.reportingall.filters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 
@Component
public class RequestsLoggerInterceptor extends HandlerInterceptorAdapter {
 
	private static final Logger logger = Logger.getLogger(RequestsLoggerInterceptor.class);
 
	//before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object handler) {

		logger.info("Request done to " + request.getRequestURI());
	
		return true;
	}
 
	//after the handler is executed
	public void postHandle() {
		
	}
}
