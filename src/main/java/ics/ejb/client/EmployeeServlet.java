package ics.ejb.client;

import ics.ejb.interfaces.*;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EmployeeLocal e1;
	@EJB
	private EmployeeLocal e2;
	@EJB
	private EmployeeLocal e3;
	@EJB
	private DepartmentLocal d;

	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String beanInterface = "Local";
		String employeeBean = ics.ejb.interfaces.EmployeeLocal.class.getCanonicalName();
		String departmentBean = ics.ejb.interfaces.DepartmentLocal.class.getCanonicalName();

		String JNDIEmpName = "java:app/StatelessEJBProject/Employee!" + employeeBean;
		String JNDIDeptName = "java:app/StatelessEJBProject/Department!" + departmentBean;

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("<title>Employee Management Console</title>");
		out.println("<style>");
		out.println("body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f4; color: #333; }");
		out.println("h1, h2, h3 { color: #222; }");
		out.println(
				"div.container { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
		out.println("p { line-height: 1.6; }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='container'>");
		out.println("<h1>Employee Management System</h1>");
		out.println("<h2>Session Details</h2>");
		out.println("<p><strong>Employee Bean:</strong> " + employeeBean + "</p>");
		out.println("<p><strong>Employee Interface:</strong> " + beanInterface + "</p>");
		out.println("<p><strong>Employee JNDI:</strong> " + JNDIEmpName + "</p>");
		out.println("<p><strong>Department Bean:</strong> " + departmentBean + "</p>");
		out.println("<p><strong>Department Interface:</strong> " + beanInterface + "</p>");
		out.println("<p><strong>Department JNDI:</strong> " + JNDIDeptName + "</p>");
		out.println("<br>");
		out.println("<p>Title of Department</p>");
		
		if (d.getDeptName() == null) {
		    d.setDeptName("LUSEM");
		    d.setDeptRandomBudget();
		}
		e1.setName("1");
		e1.setAddress("Lund1");
		e2.setName("2");
		e2.setAddress("Lund2");
		e3.setName("3");
		e3.setAddress("Lund3");
		

		if (d.getEmployees().size() <= 0) {
			d.add(e1);
			d.add(e2);
			d.add(e3);
		}
		out.println("Setting random budget of: \n" + d.getDeptName());
		d.setDeptRandomBudget();
		out.println("Total Budget is: \n" + d.getDeptBudget());
		out.println("Average Salary Per Employee is: \n" + (d.getDeptBudget() / d.getEmployees().size()));
		out.println("Number of Employees: \n" + d.getEmployees().size());

		for (EmployeeLocal emp : d.getEmployees()) {

			out.println("<li>EmpName: " + emp.getName() + "\n");
			out.println("<li>EmpAdress: " + emp.getAddress() + "\n");
		}

		out.println("</body></html>");
	}
}
