package controller;

import model.Employee;
import model.TypeCustomer;
import model.TypeRent;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import service.GetTypeService;
import service.GetTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet",urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createNewEmployee(request,response);
                break;
            case "update":
                updateEmployee(request,response);
                break;
            case "insert":
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id =request.getParameter("id");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String id_card = request.getParameter("id_card");
        String salary = request.getParameter("salary");
        String phone = request.getParameter("phone");
        String email= request.getParameter("email");
        String position_id= request.getParameter("position_id");
        String education_id= request.getParameter("education_id");
        String division_id= request.getParameter("division_id");
        String username= request.getParameter("username");
        EmployeeService employeeSevice = new EmployeeServiceImpl();
        employeeSevice.UpdateEmployee(new Employee(id,name,birthday,id_card,salary,phone,email,position_id,education_id,division_id,username));
        request.setAttribute("listEmployee",employeeSevice.findAll());
        request.getRequestDispatcher("/employee/view.jsp").forward(request,response);
    }

    private void createNewEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id =request.getParameter("id");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String id_card = request.getParameter("id_card");
        String salary = request.getParameter("salary");
        String phone = request.getParameter("phone");
        String email= request.getParameter("email");
        String position_id= request.getParameter("position_id");
        String education_id= request.getParameter("education_id");
        String division_id= request.getParameter("division_id");
        String username= request.getParameter("username");
        EmployeeService employeeSevice = new EmployeeServiceImpl();
        employeeSevice.CreateNewEmployee(new Employee(id,name,birthday,id_card,salary,phone,email,position_id,education_id,division_id,username));
        response.sendRedirect("/employee");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action =request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                viewCreateEmployeeForm(request,response);
                break;
            case "view":
                viewEmployeeList(request,response);
                break;
            case "edit":
                viewEditForm(request,response);
                break;
            case "delete":
                deleteEmployee(request,response);
                break;
            default:
                viewEmployeeList(request,response);
                break;
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id =request.getParameter("id");
        EmployeeService employeeSevice = new EmployeeServiceImpl();
        employeeSevice.DeleteEmployee(id);
        request.setAttribute("listEmployee",employeeSevice.findAll());
        request.getRequestDispatcher("/employee/view.jsp").forward(request,response);
    }

    private void viewEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id =request.getParameter("id");
        EmployeeService employeeSevice = new EmployeeServiceImpl();
        Employee employee = employeeSevice.findEmployeeById(id);

        request.setAttribute("employee",employee);
        request.getRequestDispatcher("/employee/edit.jsp").forward(request,response);
    }

    private void viewCreateEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        GetTypeService getTypeService = new GetTypeServiceImpl();
        List<TypeRent> typeRentList = getTypeService.findAllTypeRent();
        request.setAttribute("typeRentList",typeRentList);
        request.getRequestDispatcher("/customer/create.jsp").forward(request,response);
    }

    public void viewEmployeeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService employeeSevice = new EmployeeServiceImpl();
        request.setAttribute("listEmployee",employeeSevice.findAll());
        request.getRequestDispatcher("/employee/view.jsp").forward(request,response);
    }
}