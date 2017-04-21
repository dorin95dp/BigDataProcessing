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

            return indices;


        } catch (FileNotFoundException e) {
            System.out.println("Json file reading error: \n" + e.getMessage());
            return new double[0][0];
        }

    }
}
