package com.example.locationCar.services.employeeService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.validate.employee.ListEmployeeValidate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListEmployeeService {
  private final EmployeeRepository employeeRepository;

  public BaseDto listEmployees(String role, String position, String page) {
    List<BaseErrorDto> errors =
        new ListEmployeeValidate().validateParamsToSearch(role, position, page);

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    int pageToSearch = 0;

    if (page != null) pageToSearch = Integer.parseInt(page);

    Role roleToSearch = role != null ? Role.valueOf(role) : null;
    Position positionToSearch = position != null ? Position.valueOf(position) : null;

    PageRequest pageRequest = PageRequest.of(pageToSearch, 20);
    Page<EmployeeModel> employees =
        employeeRepository.listByRoleAndPosition(roleToSearch, positionToSearch, pageRequest);

    if (employees.isEmpty()) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND);
      return result.get();
    }

    if ((employees.getTotalPages() - 1) < pageToSearch) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_PAGE);
      return result.get();
    }

    return new ResponseSuccessBuilder<>(HttpStatus.OK, employees, SuccessMessage.LIST_EMPLOYEES)
        .get();
  }
}
