package com.capgemini.test.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.capgemini.test.dao.ICustomerDAO;
import com.capgemini.test.domain.Customer;
import com.capgemini.test.util.Util;

@Component
public class CustomerServlet extends HttpServlet {

	private ICustomerDAO customerDAO;
	
	
	
	
	@Override
	public void init() throws ServletException {
		customerDAO = (ICustomerDAO)Util.getBean(this.getServletContext(), "customerDAO");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String action = req.getParameter(Constants.DISPATCH);
		if(action == null){
			req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
		}
		else if (action.equals("loginAjax")){
			loginAjax(req, resp);
		}else if (action.equals("logoutAjax")){
			logoutAjax(req, resp);
		}else if (action.equals("detail")){
			detail(req, resp);
		}
	}
	
	private void detail(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		req.getRequestDispatcher("pages/customerDetail.jsp").forward(req, resp);
	}
	
	
	private void loginAjax(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Customer customer = customerDAO.get(username, password);
		if(customer != null){
			req.getSession().setAttribute("ipad", Util.isIpadUserAgent(req.getHeader("User-Agent")));
			req.getSession().setAttribute(Constants.CUSTOMER, customer);
			resp.getWriter().write( mapper.writeValueAsString(customer));
		}else{
			resp.getWriter().write("Uw login is incorrect. Probeer opnieuw.");
		}
	}
	
	private void logoutAjax(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		req.getSession().invalidate();
	}
	
	
	

}
