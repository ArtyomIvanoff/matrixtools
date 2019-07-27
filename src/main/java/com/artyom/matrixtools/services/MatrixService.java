package com.artyom.matrixtools.services;

import org.springframework.stereotype.Service;
import com.artyom.mtx.RealMatrix;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatrixService {
    private RealMatrix firstMatrix;
    private RealMatrix secondMatrix;

    public String performMatrixOperation(String firstMatrixStr, String operationStr, String secondMatrixStr)
            throws IOException {
        firstMatrix = parseMatrix(firstMatrixStr);

        // if it's a scalar multiplication
        if ("scalar_mult".equals(operationStr)) {
            double scalar = Double.parseDouble(secondMatrixStr);
            return String.valueOf(firstMatrix.scalarMultiply(scalar));
        }

        switch (operationStr) {
            case "trace":
                return String.valueOf(firstMatrix.getTrace());
            case "determinant":
                return String.valueOf(firstMatrix.getDeterminant());
            case "transpose":
                return String.valueOf(firstMatrix.transpose());
            case "inverted":
                return String.valueOf(firstMatrix.getInverseMatrix());
            case "addition":
                secondMatrix = parseMatrix(secondMatrixStr);
                return String.valueOf(firstMatrix.add(secondMatrix));
            case "product":
                secondMatrix = parseMatrix(secondMatrixStr);
                return String.valueOf(firstMatrix.multiply(secondMatrix));
            default:
                throw new IOException("Unknown operation!");
        }
    }

    public RealMatrix parseMatrix(String matrixStr) throws IOException {
        String[] rows = matrixStr.split("[\n\r]");
        int rowNumber = rows.length;

        if (rowNumber < 1)
            throw new IOException("Empty line!");

        // get rows of converted numbers
        List<double[]> parsedRows = Arrays.stream(rows)
                .map(row -> row.trim().split("\\s+"))
                .map(rowElements -> Arrays.stream(rowElements).mapToDouble(Double::parseDouble).toArray())
                .collect(Collectors.toList());
        // assume that every row has the same length
        int columnNumber = parsedRows.get(0).length;

        RealMatrix matrix = new RealMatrix(rowNumber, columnNumber);

        int i = 0;
        for (double[] parsedRow : parsedRows) {
            if (parsedRow.length != columnNumber)
                throw new IOException("Number of elements in rows must be same!");

            // fill the current row
            for (int j = 0; j < columnNumber; j++) {
                matrix.setElement(i, j, parsedRow[j]);
            }
            // move to the next row
            i++;
        }

        return matrix;
    }

}
