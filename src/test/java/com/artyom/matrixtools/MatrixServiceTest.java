package com.artyom.matrixtools;

import com.artyom.matrixtools.services.MatrixService;
import com.artyom.mtx.RealMatrix;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MatrixServiceTest extends Assert {
    private MatrixService matrixService = new MatrixService();
    private static final double eps = 1e-6;
    @Test
    public void testEqualityMatrix() {
        String eqMatrixStr = "  1 0   \n 0  1";

        try {
            RealMatrix parsedMatrix = matrixService.parseMatrix(eqMatrixStr);
            RealMatrix eqMatrix = RealMatrix.getEqualityMatrix(2);

            for(int i = 0; i < 2; i++)
                for(int j = 0; j < 2; j++)
                    assertEquals(eqMatrix.getElement(i,j), parsedMatrix.getElement(i, j), eps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
