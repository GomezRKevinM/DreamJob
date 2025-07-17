package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.SQLQuery;
import com.talento_tech.BolsaEmpleo.Services.ServiceSql;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sql")
public class SQLController {
    Object data;

    @PatchMapping("/consultar")
    public ResponseEntity<ResponseDto> consultarSQL(@RequestBody SQLQuery query){
        ResponseDto response = new ServiceSql().consultarSQL(query);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
