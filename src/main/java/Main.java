import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    private static final String jsonPath = "C:\\Users\\Popa\\IdeaProjects\\BigDataProcessing\\src\\main\\resources\\data";
    private static final Gson parser = new Gson();

    public static void main(String[] args) {

        try {
            JsonReader json = new JsonReader(new FileReader(jsonPath));

            ExchangeManager exchangeManager = parser.fromJson(json, ExchangeManager.class);

            ArrayList<Exchange> exchanges = exchangeManager.exchanges;
            double[] eur = new double[exchanges.size()];
            double[] usd = new double[exchanges.size()];
            double[] ron = new double[exchanges.size()];

            int i = 0;
            for (Exchange exchange: exchanges) {
                eur[i] = exchange.EUR;
                usd[i] = exchange.USD;
                ron[i] = exchange.RON;
                i++;
            }

            double[][]indices = {eur, usd, ron};

            // PearsonsCorrelation
            PearsonsCorrelation pearsonsCorr = new PearsonsCorrelation();
//        double[][]indices = {{1, 2, 4, 8, 16},
//                             {2, 4, 8, 16, 32},
//                             {3, 5, 7, 9, 11},
//                             {2, 4, 6, 8, 10}
//        };

            RealMatrix realMatrixCorrelations = pearsonsCorr.computeCorrelationMatrix(indices);
            double[][]correlations = realMatrixCorrelations.getData();

            printCorrelations(correlations);


        } catch (FileNotFoundException e) {
            System.out.println("Json file reading error: \n" + e.getMessage());
        }
    }

    static void printCorrelations (double[][] corrs) {
        System.out.println("The correlation table: \n");

        for (int rows = 0; rows < corrs.length; rows++){
            for (int columns = 0; columns < corrs[rows].length; columns++){
                System.out.print(String.format("%.3f \t", corrs[rows][columns]));
            }
            System.out.println();
        }
    }


}
