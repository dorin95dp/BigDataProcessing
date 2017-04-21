import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import services.MyParser;

public class Main {

    public static void main(String[] args) {

        MyParser parser = new MyParser();
        double[][]indices = parser.getMatrix();
        
        // PearsonsCorrelation
        PearsonsCorrelation pearsonsCorr = new PearsonsCorrelation();
//        double[][]indices = {{1, 2, 4, 8, 16},
//                {2, 4, 8, 16, 32},
//                {3, 5, 7, 9, 11},
//                {2, 4, 6, 8, 10}
//        };

        RealMatrix realMatrixCorrelations = pearsonsCorr.computeCorrelationMatrix(indices);
        double[][]correlations = realMatrixCorrelations.getData();

        printCorrelations(correlations);

    }

    static void printCorrelations (double[][] corrs) {
        System.out.println("The correlation table: \n");

        for (int rows = 0; rows < corrs.length; rows++){
            for (int columns = 0; columns < corrs[rows].length; columns++){
                System.out.print(String.format("%.2f \t", corrs[rows][columns]));
            }
            System.out.println();
        }
    }


}
