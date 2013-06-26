package com.akwolf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.akwolf.bean.Person;
import com.akwolf.service.IPersonService;
import com.akwolf.service.impl.PersonServiceImpl;
import com.akwolf.util.RequestUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String action = request.getParameter("action");
		//System.out.println(action);

		if ("add".equals(action)) {
			addPerson(request, response);
		} else if ("list".equals(action)) {
			doListPerson(request, response);
		}
	}

	private void addPerson(HttpServletRequest request, HttpServletResponse response) {

	}

	private void doListPerson(HttpServletRequest request, HttpServletResponse response) {
		IPersonService personService = new PersonServiceImpl();
		int[] page = RequestUtil.getPageParam(request);

		List<Person> list = personService.listPerson(page[0], page[1]);
		long total = personService.resultCount();

		JSONArray array = new JSONArray();
		//		JSONArray array = JSONArray.fromObject(list);
		array.addAll(list);
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("total", total);
		jsonObject.put("rows", array);

		responsePage(response, jsonObject.toJSONString());
	}

	private void responsePage(HttpServletResponse response, String str) {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
