package services;

public class ArrayHelper {
    public static double[][] getTransposed (double[][] biArray) {

        double[][] pivot = new double[biArray[0].length][];
        for (int row = 0; row < biArray[0].length; row++)
            pivot[row] = new double[biArray.length];

        for (int row = 0; row < biArray.length; row++)
            for (int col = 0; col < biArray[row].length; col++)
                pivot[col][row] = biArray[row][col];

        return pivot;
    }
}
