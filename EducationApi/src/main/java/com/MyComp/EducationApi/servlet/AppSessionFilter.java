package com.MyComp.EducationApi.servlet;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppSessionFilter implements Filter {

   // private ArrayList<String> urlList;
    
	public void init(FilterConfig config) throws ServletException {
		System.out.println("Filter init");
		
		/*		BasicConfigurator.configure();
		
	    String urls = config.getInitParameter("avoid-urls");
	    StringTokenizer token = new StringTokenizer(urls, ",");
	    urlList = new ArrayList<String>();
	    while (token.hasMoreTokens()) {
	        urlList.add(token.nextToken());
	
	    }
*/	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
	    HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    String url = request.getServletPath();
	    System.out.println("Filter=" + url + " request " + request);

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers","X-ACCESS_TOKEN, Access-Control-Allow-Origin, Authorization, Origin, x-requested-with, Content-Type, Content-Range, Content-Disposition, Content-Description");
		
		chain.doFilter(req, res);
	}

	public void destroy() {
		System.out.println("Filter destroy");
	}
	
}