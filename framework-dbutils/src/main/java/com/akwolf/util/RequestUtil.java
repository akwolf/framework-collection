package com.akwolf.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	public static int[] getPageParam(HttpServletRequest request){
		int[] p = new int[2] ;
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		int beginRow = (page - 1) * rows;
		p[0] = beginRow ;
		p[1] = rows ;
		
		return p ;
	}
}
