package com.siupay.openapi.filter;

import com.siupay.starter.chaincontext.ChainRequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;


@Component
public class ChainContextHeaderFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		Enumeration<String> headerNames = httpRequest.getHeaderNames();
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = httpRequest.getHeader(headerName);
//				System.out.println(headerName+": " + headerValue);
				ChainRequestContext.getCurrentContext().put(headerName,headerValue);
			}
		}

		//doFilter
		filterChain.doFilter(httpRequest, servletResponse);
	}
}

