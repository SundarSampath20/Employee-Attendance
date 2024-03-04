package com.example.EmployeeAttendanceTracking.Service;

import com.example.EmployeeAttendanceTracking.Model.EmployeeModel;
import com.example.EmployeeAttendanceTracking.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeModel createEmployee(EmployeeModel employeeModel) {
        return employeeRepository.save(employeeModel);
    }

    public List<EmployeeModel> addAllEmployee(List<EmployeeModel> employees) {
        return employeeRepository.saveAll(employees);
    }

    public List<EmployeeModel> readAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeModel readEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public String updateEmployee(Long employeeId, String name, String department, String position, String contact) {
        EmployeeModel employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            employee.setName(name);
            employee.setDepartment(department);
            employee.setPosition(position);
            employee.setContact(contact);
            employeeRepository.save(employee);
            return "Employee Details Updated";
        } else {
            return "Employee not found";
        }
    }
    public String deleteEmployee(Long employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return "Employee Deleted";
        } else {
            return "Employee not found";
        }
    }
    public String calPayRoll(long employeeId){
        EmployeeModel employeeModel=employeeRepository.getById(employeeId);
        double basePay=employeeModel.getBasePay();
        double hikeRate=employeeModel.getHikeRate();
        double salary=basePay*hikeRate;
        return "Salary for:"+employeeModel.getName()+"is  "+salary;
    }

    public List<EmployeeModel> getEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
}
