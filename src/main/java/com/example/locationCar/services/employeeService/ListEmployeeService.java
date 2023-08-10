package com.example.locationCar.services.funcionarioService;

import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ListEmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ListEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Page<EmployeeModel> listEmployees(Role role, Position position, Integer page){
        int pageToSearch = 0;

        if(page == null) pageToSearch = 0;

        PageRequest pageRequest = PageRequest.of(pageToSearch, 20);

        return employeeRepository.listByRoleAndPosition(role, position, pageRequest);
    }
}
