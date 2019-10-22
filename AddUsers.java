package com.virtusa.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtusa.dao.UsersDAO;
import com.virtusa.entities.Users;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  int user_id=Integer.parseInt(request.getParameter("user_id"));
        if(user_id!=0){
            Users users=new Users();
            users.setUser_id(user_id);
            users.setUsername(request.getParameter("user_name"));
            String dob=request.getParameter("date_of_birth");
            java.sql.Date dob1=java.sql.Date.valueOf(dob);
            users.setDate_of_birth(dob1);
            users.setEmail_id(request.getParameter("mail_id"));
            users.setEmployee_type(request.getParameter("employee_type"));
    		PrintWriter out=response.getWriter();
                out.println(new UsersDAO().addUsersDao(users));
        }RequestDispatcher disp=request.getRequestDispatcher("AddUser.jsp");
        disp.include(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
