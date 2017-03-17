package ycp.cs320.teamProject.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ycp.cs320.teamProject.model.User;
////WHAT is this servlet page do, is this not what mainPage is???

public class AccountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	//private getAccountInfo user = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/account.jsp").forward(req, resp);
		
		String user = (String) req.getSession().getAttribute("User.username");
		User model = new User();
		if (user == null) {
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;

		}
		
		//added afterwards 
		String password = (String) req.getSession().getAttribute("password");
		String password2 = (String) req.getSession().getAttribute("password2");
		if (password != password2){
			//Passwords don't match
			resp.sendRedirect(req.getContextPath()+ "/retrychangePassword");
		}
		model.setPassword(password);

		int UserNumber = (int) req.getSession().getAttribute("UserID");
		model.setUserID(UserNumber);
		String FirstName = (String) req.getSession().getAttribute("FirstName");
		model.setFirstName(FirstName);
		String LastName = (String) req.getSession().getAttribute("LastName");
		model.setLastName(LastName);
		String Email = (String) req.getSession().getAttribute("Email");
		model.setEmailAddress(Email);
		
		req.getRequestDispatcher("/_view/retrychangePassword.jsp").forward(req, resp);
		req.getRequestDispatcher("/_view/changePassword.jsp").forward(req, resp);
		req.getRequestDispatcher("/_view/CreateAccount.jsp").forward(req, resp);
		req.getRequestDispatcher("/_view/Account.jsp").forward(req, resp);
	}



}
