//package com.example.demo_EMS.controller;
//
//import com.example.demo_EMS.entities.Employee;
//import com.example.demo_EMS.repositoies.EmployeeRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/employees")
//@CrossOrigin("*")  // Allows requests from any origin, useful for testing
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeRepo employeeRepo;
//
//    // Create a new employee
//    @PostMapping
//    public Employee createEmployee(@RequestBody Employee employee) {
//        return employeeRepo.save(employee);
//    }
//
//    // Get all employees
//    @GetMapping
//    public List<Employee> getAllEmployees() {
//        return employeeRepo.findAll();
//    }
//
//    // Update an existing employee by ID
//    @PutMapping("/{id}")
//    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
//        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
//
//        if (optionalEmployee.isPresent()) {
//            Employee employee = optionalEmployee.get();
//            employee.setName(employeeDetails.getName());
//            employee.setDepartement(employeeDetails.getDepartement());
//            return employeeRepo.save(employee);
//        } else {
//            throw new RuntimeException("Employee not found with id " + id);
//        }
//    }
//
//    // Delete an employee by ID
//    @DeleteMapping("/{id}")
//    public String deleteEmployee(@PathVariable Long id) {
//        if (employeeRepo.existsById(id)) {
//            employeeRepo.deleteById(id);
//            return "Employee deleted successfully.";
//        } else {
//            return "Employee not found.";
//        }
//    }
//}
///////////////////////// using front end also //////////////////////
package com.example.demo_EMS.controller;

import com.example.demo_EMS.entities.Employee;
import com.example.demo_EMS.repositoies.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    // Show all employees
    @GetMapping("/")
    public String viewEmployees(Model model) {
        model.addAttribute("employees", employeeRepo.findAll());
        return "employeesList"; // refers to employeesList.html
    }

    // Show form to add a new employee
    @GetMapping("/addEmployee")
    public String newEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "new"; // refers to new.html
    }

    // Handle form submission to save employee
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepo.save(employee);
        return "redirect:/";
    }

    // Show form to edit employee
    @GetMapping("/editEmployee/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeRepo.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        return "edit"; // refers to edit.html
    }

    // Handle update form submission
    @PostMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employeeDetails) {
        Employee employee = employeeRepo.findById(id).orElseThrow();
        employee.setName(employeeDetails.getName());
        employee.setDepartement(employeeDetails.getDepartement());
        employeeRepo.save(employee);
        return "redirect:/";
    }

    // Handle delete
    @GetMapping("/deleteEmpoyee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepo.deleteById(id);
        return "redirect:/";
    }
}
