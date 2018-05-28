package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Dao.CreateJobOpeningsDao;
import com.Model.JobOpenings;;


public class CreateJobOpeningsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CreateJobOpeningsController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		if(request.getParameter("submit").equalsIgnoreCase("Create"))
		{
			
			String department = request.getParameter("department");
			String jobTitle = request.getParameter("jobTitle");
			int noOfOpenings = Integer.parseInt(request.getParameter("noOfOpenings"));

				
			
			System.out.println(department);
			System.out.println(jobTitle);
			System.out.println(noOfOpenings);
			
				
			JobOpenings jobOpenings = new JobOpenings();
			jobOpenings.setDepartment(department);
			jobOpenings.setJobTitle(jobTitle);
			jobOpenings.setNoOfOpenings(noOfOpenings);
			
			
				
			int	i = CreateJobOpeningsDao.JobOpenings(jobOpenings);
			
			System.out.println(i);
			
			if(i>0){
				
					request.setAttribute("msg", "Job Opening created successfully!!!");
					request.getRequestDispatcher("CreateJobOpenings.jsp").forward(request, response);
			}else {
					request.setAttribute("msg", "Job Opening could not be created due to an error!!!");
					request.getRequestDispatcher("CreateJobOpenings.jsp").forward(request, response);
			}
		}
			
	}
			
}
