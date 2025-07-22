package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.SQLQuery;
import com.talento_tech.BolsaEmpleo.Services.ServiceSql;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sql")
public class SQLController {

    private final ServiceSql service;

    @Autowired
    public SQLController(ServiceSql service) {
        this.service = service;
    }

    @PatchMapping("/consultar")
    public ResponseEntity<ResponseDto> consultarSQL(@RequestBody SQLQuery query) {
        ResponseDto response = service.consultarSQL(query);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
