package com.example.locationCar.controller;


import com.example.locationCar.models.FuncionarioModel;
import com.example.locationCar.repositories.FuncionarioRepository;
import com.example.locationCar.services.funcionarioService.FuncionarioServiceDelete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
