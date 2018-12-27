package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.servlet.base.BasicServlet;

@WebServlet(value = "/test/hahah")
public class TestServlet extends BasicServlet {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public void test(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			out.println("hahahahahah");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
