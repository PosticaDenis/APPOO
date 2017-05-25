package APPOO.lab2;

import APPOO.lab2.tools.CorrelationCalculator;

/**
 * Created by Dennis on 29-Apr-17.
 **/
public class Main {

    public static void main(String[] args) {

        String path = "resources/MU_REPORT.csv";

        DataProcessor dataProcessor = new DataProcessor(path);
        CorrelationCalculator correlationCalculator = new CorrelationCalculator(dataProcessor);

        correlationCalculator.getResults();
    }
}