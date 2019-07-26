package com.artyom.matrixtools.model;


public class MatrixOperationRequest {
    String firstMatrix;
    String operation;
    String secondMatrix;

    public String getFirstMatrix() {
        return firstMatrix;
    }

    public void setFirstMatrix(String firstMatrix) {
        this.firstMatrix = firstMatrix;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSecondMatrix() {
        return secondMatrix;
    }

    public void setSecondMatrix(String secondMatrix) {
        this.secondMatrix = secondMatrix;
    }
}
