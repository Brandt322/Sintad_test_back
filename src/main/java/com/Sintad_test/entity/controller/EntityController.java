package com.Sintad_test.entity.controller;

import com.Sintad_test.entity.interfaces.EntityBasicCrud;
import com.Sintad_test.entity.models.request.EntityDetailsRequest;
import com.Sintad_test.entity.models.response.EntityBasicResponse;
import com.Sintad_test.entity.models.response.EntityDetailsResponse;
import com.Sintad_test.exceptions.BadRequestException;
import com.Sintad_test.exceptions.NotFoundException;
import com.Sintad_test.shared.interfaces.Pagination;
import com.Sintad_test.shared.pagination.PagedResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/entity")
@RestController
public class EntityController {

    @Autowired
    private Pagination<EntityBasicResponse> basicResponsePagination;

    @Autowired
    private EntityBasicCrud entityBasicCrud;

    @GetMapping("/all")
    public PagedResponse<EntityBasicResponse> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int documentType,
            @RequestParam(defaultValue = "0") int taxpayerType,
            @RequestParam(defaultValue = "") String state
    ) {
        return basicResponsePagination.findAll(page, size, documentType, taxpayerType, state);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            EntityDetailsResponse entityDetailsResponse = entityBasicCrud.findById(id);
            return new ResponseEntity<>(entityDetailsResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody EntityDetailsRequest entityDetailsRequest) {
        try {
            EntityDetailsResponse entityDetailsResponse1 = entityBasicCrud.create(entityDetailsRequest);
            return new ResponseEntity<>(entityDetailsResponse1, HttpStatus.CREATED);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody EntityDetailsRequest entityDetailsRequest) {
        try {
            EntityDetailsResponse entityDetailsResponse = entityBasicCrud.update(id, entityDetailsRequest);
            return new ResponseEntity<>(entityDetailsResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            entityBasicCrud.delete(id);
            return new ResponseEntity<>("Entidad eliminada correctamente", HttpStatus.OK);
        } catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
