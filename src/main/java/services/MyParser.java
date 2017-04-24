package services;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import models.Exchange;
import models.ExchangeManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class MyParser {
    private static final String jsonPath = "C:\\Users\\Popa\\IdeaProjects\\BigDataProcessing\\src\\main\\resources\\data";
    private static final Gson parser = new Gson();

    public double[][] getMatrix() {
        try {
            JsonReader json = new JsonReader(new FileReader(jsonPath));

            ExchangeManager exchangeManager = parser.fromJson(json, ExchangeManager.class);

            ArrayList<Exchange> exchanges = exchangeManager.exchanges;
            int length = exchanges.size();

            double[] eur = new double[length];
            double[] usd = new double[length];
            double[] ron = new double[length];
            double[] rub = new double[length];
            double[] gbp = new double[length];

            int i = 0;
            for (Exchange exchange: exchanges) {
                eur[i] = exchange.EUR;
                usd[i] = exchange.USD;
                ron[i] = exchange.RON;
                rub[i] = exchange.RUB;
                gbp[i] = exchange.GBP;

                i++;
            }

            double[][]matrix = {eur, usd, ron, rub, gbp};

            return ArrayHelper.getTransposed(matrix);


        } catch (FileNotFoundException e) {
            System.out.println("Json file reading error: \n" + e.getMessage());
            return new double[0][0];
        }

    }
}
