package com.web.servlet.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BasicServlet
 */
public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 获取传入进来要调用的方法名称，StudentManage继承了该类，所以在这里接收方法 */
		String handler = request.getParameter("handler");

		try {
			/* 通过此对象获取类，再获取类中指定名称并传入参数类型的对应Class对象 */
			Method method = this.getClass().getDeclaredMethod(handler, HttpServletRequest.class,
					HttpServletResponse.class);
			/* 执行方法，this实际上是StudentManage对象 */
			method.invoke(this, request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
