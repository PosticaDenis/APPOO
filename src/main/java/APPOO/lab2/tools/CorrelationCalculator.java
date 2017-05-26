package APPOO.lab2.tools;

import APPOO.lab2.DataProcessor;
import APPOO.lab2.ProcessedMedicalDataBean;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.util.List;

/**
 * Created by Dennis on 20-May-17.
 **/
public class CorrelationCalculator {

    private PearsonsCorrelation correlation = new PearsonsCorrelation();
    private List<ProcessedMedicalDataBean> processedMedicalDataBeans;
    private double[][] columns;

    public CorrelationCalculator(List<ProcessedMedicalDataBean> processedMedicalDataBeans) {
        this.processedMedicalDataBeans = processedMedicalDataBeans;
        this.columns  = new double[8][processedMedicalDataBeans.size()];
        setColumns();
    }

    private void setColumns() {

        //List<ProcessedMedicalDataBean> processedMedicalDataBeans = dataProcessor.getProcessedMedicalDataBeans();
        int i = 0;

        for (ProcessedMedicalDataBean data: processedMedicalDataBeans) {
            setValues(data, i);
            i++;
        }
    }

    private void setValues(ProcessedMedicalDataBean data, int i) {
        columns[0][i] = data.getNpi();
        columns[1][i] = data.getBussinesStateTeritoryId();
        columns[2][i] = data.getZip();
        columns[3][i] = data.getSpecialityId();
        columns[4][i] = data.getProgramYear();
        columns[5][i] = data.getProviderStageNumberId();
        columns[6][i] = data.getProductClassificationId();
        columns[7][i] = data.getProductSettingId();

    }

    public void getResults(){

        System.out.println("The results are: ");

        // if the result is NaN it means that the variation of variables in one of the column is too small to be taken into consideration.
        for (int i = 0; i<7; i++) {
            System.out.println(correlation.correlation(columns[i], columns[i+1]));
        }
    }
}
