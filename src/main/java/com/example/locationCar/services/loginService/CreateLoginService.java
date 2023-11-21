package com.example.locationCar.services.loginService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.CreateLoginDto;
import com.example.locationCar.dtos.input.LoginInputDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.LoginModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.repositories.LoginRepository;
import com.example.locationCar.services.core.EncryptService;
import com.example.locationCar.validate.login.CreateLoginValidate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreateLoginService {

    private final LoginRepository loginRepository;
    private final EncryptService encryptService;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public CreateLoginService(LoginRepository loginRepository, EncryptService encryptService,
                              ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.loginRepository = loginRepository;
        this.encryptService = encryptService;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    public BaseDto inserir(LoginInputDto loginInputDto) {
        List<BaseErrorDto> errors = new CreateLoginValidate().validate(loginInputDto);

        if (loginInputDto.getEmail() != null) {
            Boolean email = loginRepository.existsByEmail(loginInputDto.getEmail());
            if (email) {
                errors.add(new BaseErrorDto("email", ErrorMessage.CONFLIT_EMAIL));
            }
        }

        if (errors.size() > 0) {
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
            return result.get();
        }

        String password = encryptService.encrypt(loginInputDto.getPassword());

        LoginModel login = new LoginModel();
        login.setEmail(loginInputDto.getEmail());
        login.setPassword(password);

        EmployeeModel employee = new EmployeeModel();
        ClientModel client = new ClientModel();

        if (loginInputDto.getIdClient() != null) {
            try {
                UUID idCliente = UUID.fromString(loginInputDto.getIdClient());
                Optional<ClientModel> clientEntity = clientRepository.findById(idCliente);
                if(clientEntity.isEmpty()){
                    errors.add(new BaseErrorDto("Cliente", ErrorMessage.NOT_FOUND));
                    ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
                    return result.get();
                }
                client = clientEntity.get();
            } catch (IllegalArgumentException e) {
                errors.add(new BaseErrorDto("idClient", ErrorMessage.INVALID_UUID));
                ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.PRECONDITION_FAILED, errors);
                return result.get();
            }

        }

        if (loginInputDto.getEmployeeId() != null) {
            try {
                UUID employeeId = UUID.fromString(loginInputDto.getEmployeeId());
                Optional<EmployeeModel> employeeEntity = employeeRepository.findById(employeeId);
                if(employeeEntity.isEmpty()){
                    errors.add(new BaseErrorDto("Funcionario", ErrorMessage.NOT_FOUND));
                    ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
                    return result.get();
                }
                employee = employeeEntity.get();
            } catch (IllegalArgumentException e) {
                errors.add(new BaseErrorDto("employeeId", ErrorMessage.INVALID_UUID));
                ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.PRECONDITION_FAILED, errors);
                return result.get();
            }
        }

        UUID createdId = loginRepository.save(login).getIdLogin();
        login.setIdLogin(createdId);

        if (loginInputDto.getIdClient() != null) {
            client.setLogin(login);
            clientRepository.save(client);
        }

        if (loginInputDto.getEmployeeId() != null) {
            employee.setLogin(login);
            employeeRepository.save(employee);
        }

        return new ResponseSuccessBuilder<CreateLoginDto>(
                HttpStatus.CREATED,
                new CreateLoginDto(createdId.toString()),
                SuccessMessage.CREATE_LOGIN)
                .get();
    }
}