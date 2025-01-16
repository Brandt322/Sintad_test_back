package com.Sintad_test.taxpayerType.controller;

import com.Sintad_test.exceptions.NotFoundException;
import com.Sintad_test.shared.interfaces.Crud;
import com.Sintad_test.taxpayerType.interfaces.TaxpayerTypeList;
import com.Sintad_test.taxpayerType.models.request.TaxpayerTypeBasicRequest;
import com.Sintad_test.taxpayerType.models.response.TaxpayerTypeBasicResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/taxpayer-type")
@RestController
public class TaxpayerTypeController {
    private final Crud<TaxpayerTypeBasicRequest, TaxpayerTypeBasicResponse> crud;
    private final TaxpayerTypeList taxpayerTypeList;

    public TaxpayerTypeController(Crud<TaxpayerTypeBasicRequest, TaxpayerTypeBasicResponse> crud, TaxpayerTypeList taxpayerTypeList) {
        this.crud = crud;
        this.taxpayerTypeList = taxpayerTypeList;
    }

    @GetMapping("/taxpayer-types")
    public ResponseEntity<Object> getAllTaxpayerTypes() {
        return new ResponseEntity<>(crud.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaxpayerTypeById(@PathVariable Long id) {
        try {
            TaxpayerTypeBasicResponse taxpayerTypeBasicResponse = crud.findById(id);
            return new ResponseEntity<>(taxpayerTypeBasicResponse, HttpStatus.OK);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    @PostMapping("/create")
    public ResponseEntity<Object> createTaxpayerType(@Valid @RequestBody TaxpayerTypeBasicRequest taxpayerTypeBasicRequest) {
        try {
            TaxpayerTypeBasicResponse taxpayerTypeBasicResponse = crud.create(taxpayerTypeBasicRequest);
            return new ResponseEntity<>(taxpayerTypeBasicResponse, HttpStatus.CREATED);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateTaxpayerType(@PathVariable Long id,@Valid @RequestBody TaxpayerTypeBasicRequest taxpayerTypeBasicRequest) {
        try {
            TaxpayerTypeBasicResponse taxpayerTypeBasicResponse = crud.update(id, taxpayerTypeBasicRequest);
            return new ResponseEntity<>(taxpayerTypeBasicResponse, HttpStatus.OK);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTaxpayerType(@PathVariable Long id) {
        try {
            crud.delete(id);
            return new ResponseEntity<>("Taxpayer type deleted successfully", HttpStatus.OK);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getBasicTaxpayerType() {
        return new ResponseEntity<>(taxpayerTypeList.findBasicTaxpayerType(), HttpStatus.OK);
    }
}
