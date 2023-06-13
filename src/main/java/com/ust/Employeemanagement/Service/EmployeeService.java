package com.ust.Employeemanagement.Service;

import com.ust.Employeemanagement.Exception.UserNotFoundException;
import com.ust.Employeemanagement.Repo.EmployeeRepo;
import com.ust.Employeemanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
@Service
@Transactional
public class EmployeeService {
    private  final EmployeeRepo employeeRepo;
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo){
        this.employeeRepo= employeeRepo;
    }

    public List<Employee> findAllEmpoyees() {
        return employeeRepo.findAll();
    }

    public Employee findEmployeeById(Long id) {
        return  employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("user by id"+ id +"was not found"));
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeecode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }
}
