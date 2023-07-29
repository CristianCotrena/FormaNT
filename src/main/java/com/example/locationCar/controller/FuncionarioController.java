package com.example.locationCar.controller;


import com.example.locationCar.models.FuncionarioModel;
import com.example.locationCar.repositories.FuncionarioRepository;
import com.example.locationCar.services.funcionarioService.FuncionarioServiceDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/funcionario")
public class FuncionarioController {

    FuncionarioRepository funcionarioRepository;
    FuncionarioServiceDelete funcionarioServiceDelete;

    public FuncionarioController(FuncionarioServiceDelete funcionarioServiceDelete) {
        this.funcionarioServiceDelete = funcionarioServiceDelete;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFuncionario(@PathVariable(value = "id") UUID funcionarioId) {
        FuncionarioModel funcionario = funcionarioServiceDelete.deleteFuncionario(funcionarioId);
        return (funcionario == null || !funcionario.getIdFuncionario().equals(funcionarioId)) ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee not found")
                : ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
    }
}
