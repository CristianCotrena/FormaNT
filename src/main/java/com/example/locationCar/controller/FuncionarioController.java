package com.example.locationCar.controller;


import com.example.locationCar.dtos.FuncionarioRecordDto;
import com.example.locationCar.models.FuncionarioModel;
import com.example.locationCar.repositories.FuncionarioRepository;

import com.example.locationCar.services.funcionarioService.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/funcionario")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioService funcionarioServiceCadastro;


    public FuncionarioController(FuncionarioService funcionarioServiceCadastro, FuncionarioRepository funcionarioRepository, FuncionarioService funcionarioServiceCadastro1) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioServiceCadastro = funcionarioServiceCadastro1;
    }

    @PostMapping
    public ResponseEntity<FuncionarioModel> saveFuncionario (@RequestBody @Valid FuncionarioRecordDto funcionarioRecordDto) {
        var funcionarioModel = new FuncionarioModel();
        BeanUtils.copyProperties(funcionarioRecordDto, funcionarioModel);
                FuncionarioModel savedFuncionario = FuncionarioService.saveFuncionario(funcionarioModel);

        return  ResponseEntity.status(HttpStatus.CREATED).body(funcionarioRepository.save(funcionarioModel));
    }

}




