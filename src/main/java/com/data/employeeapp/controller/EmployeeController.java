package com.data.employeeapp.controller;

import com.data.employeeapp.model.Employee;
import com.data.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String showForm(Employee employee) {
        return "index";
    }

    @PostMapping("/addEmployee")
    public String saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/displayAll";
    }

    // restful api to get all employees
    @GetMapping("/api/displayAll")
    @ResponseBody
    public List<Employee> getAllEmployeesAsList() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/displayAll")
    public String getAllEmployees() {
        return "list";
    }

    @GetMapping("/api/display/{employeeId}")
    @ResponseBody
    public Optional<Employee> getEmployeeByIdAsOptional(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/display/{employeeId}")
    public String getEmployeeById(@PathVariable String employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        return "info";
    }

}
