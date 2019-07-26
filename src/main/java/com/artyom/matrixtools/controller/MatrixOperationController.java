package com.artyom.matrixtools.controller;

import com.artyom.matrixtools.model.MatrixOperationRequest;
import com.artyom.matrixtools.model.MatrixOperationResponseBody;
import com.artyom.matrixtools.services.MatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class MatrixOperationController {
    MatrixService matrixService;

    @Autowired
    public void setMatrixService(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    @PostMapping("/api/operations")
    public ResponseEntity<?> getMatrixOperationResult(@Valid @RequestBody MatrixOperationRequest request,
                                                      Errors errors) {
        MatrixOperationResponseBody responseBody = new MatrixOperationResponseBody();

        // if there are errors, just return as a bad request
        if (errors.hasErrors()) {
            responseBody.setMsg(errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(responseBody);
        }

        String result;
        // perform the operation
        try {
            result = matrixService.performMatrixOperation(request.getFirstMatrix(),
                    request.getOperation(),
                    request.getSecondMatrix());
        } catch (Exception e) {
            // if something unexpected occurs, then return a bad request
            responseBody.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }

        // if it is ok, then set the message and the result
        responseBody.setMsg(request.getOperation() + " success!");
        responseBody.setResult(result);

        return ResponseEntity.ok(responseBody);
    }
}
