package APPOO.lab2;

import com.sun.istack.internal.Nullable;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.*;
import java.util.List;

/**
 * Created by Dennis on 17-May-17.
 **/
public class MedicalDataSchema {

    private List<MedicalDataBean> medicalDataBeans;
    private String pathToCSV;

    MedicalDataSchema(String path) {
        pathToCSV = path;
        loadData();
    }

    private void loadData() {

        // BeanListProcessor converts each parsed row to an instance of a given class, then stores each instance into a list.
        BeanListProcessor<MedicalDataBean> rowProcessor = new BeanListProcessor<>(MedicalDataBean.class);

        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setRowProcessor(rowProcessor);
        parserSettings.setHeaderExtractionEnabled(true);

        CsvParser parser = new CsvParser(parserSettings);
        parser.parse(getFileReader(pathToCSV));

        // The BeanListProcessor provides a list of objects extracted from the input.
        medicalDataBeans = rowProcessor.getBeans();
        System.out.println("Done loading file.\n");
    }

    @Nullable
    private Reader getFileReader(String absolutePath) {
        try {
            return new InputStreamReader(new FileInputStream(new File(absolutePath)), "UTF-8");
        }
        catch (IOException e) {
            System.out.print("Error");
        }
        return null;
    }

    public List<MedicalDataBean> getMedicalDataBeans(){
        return medicalDataBeans;
    }
}
