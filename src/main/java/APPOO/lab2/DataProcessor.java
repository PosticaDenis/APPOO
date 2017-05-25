package APPOO.lab2;

import java.util.*;

/**
 * Created by Dennis on 17-May-17.
 **/
public class DataProcessor {

    private List<MedicalDataBean> medicalDataBeans;
    private List<ProcessedMedicalDataBean> processedMedicalDataBeans = new ArrayList<>();
    SortedSet<String> hashSet = new TreeSet<>();

    DataProcessor(String path) {

        MedicalDataSchema medicalDataSchema = new MedicalDataSchema(path);
        medicalDataBeans = medicalDataSchema.getMedicalDataBeans();
        copyData();
    }

    private void copyData(){

        populateHashSet();

        for (MedicalDataBean medicalDataBean: medicalDataBeans) {
            ProcessedMedicalDataBean processedMedicalDataBean = new ProcessedMedicalDataBean();
            processedMedicalDataBean.setNpi(medicalDataBean.getNpi());
            processedMedicalDataBean.setBussinesStateTeritoryId(getId(medicalDataBean.getBussinesStateTeritory()));
            processedMedicalDataBean.setZip(getId(medicalDataBean.getZip()));
            processedMedicalDataBean.setSpecialityId(getId(medicalDataBean.getSpeciality()));
            processedMedicalDataBean.setProgramYear(medicalDataBean.getProgramYear());
            processedMedicalDataBean.setProviderStageNumberId(getId(medicalDataBean.getProviderStageNumber()));
            processedMedicalDataBean.setProductClassificationId(getId(medicalDataBean.getProductClassification()));
            processedMedicalDataBean.setProductSettingId(getId(medicalDataBean.getProductSetting()));

            processedMedicalDataBeans.add(processedMedicalDataBean);
        }
    }

    private void populateHashSet(){

        for (MedicalDataBean medicalDataBean: medicalDataBeans) {
            setId(medicalDataBean.getZip());
            setId(medicalDataBean.getBussinesStateTeritory());
            setId(medicalDataBean.getSpeciality());
            setId(medicalDataBean.getProviderStageNumber());
            setId(medicalDataBean.getProductClassification());
            setId(medicalDataBean.getProductSetting());
        }
    }

    private void setId(String data){
        if (data != null && !data.equals("")) {
            hashSet.add(data);
        }
    }

    private double getId(String data) {

        if (data == null ){
            return -1.0;
        }
        //System.out.println(data);
        return hashSet.contains(data)? hashSet.headSet(data).size(): -1;
    }

    public List<ProcessedMedicalDataBean> getProcessedMedicalDataBeans() {
        return processedMedicalDataBeans;
    }
}
