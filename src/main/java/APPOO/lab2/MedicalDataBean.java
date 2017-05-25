package APPOO.lab2;


import com.univocity.parsers.annotations.Parsed;

/**
 * Created by Dennis on 17-May-17.
 **/
public class MedicalDataBean {

        @Parsed(index = 0)
        private double npi;
        @Parsed(index = 3)
        private String bussinesStateTeritory;
        @Parsed(index = 4)
        private String zip;
        @Parsed(index = 5)
        private String speciality;
        @Parsed(index = 8)
        private double programYear;
        @Parsed(index = 9)
        private String providerStageNumber;
        @Parsed(index = 20)
        private String productClassification;
        @Parsed(index = 21)
        private String productSetting;

        public double getNpi() {
            return npi;
        }

        public String getBussinesStateTeritory() {
            return bussinesStateTeritory;
        }

        public String getZip() {
            return zip;
        }

        public String getSpeciality() {
            return speciality;
        }

        public double getProgramYear() {
            return programYear;
        }

        public String getProviderStageNumber() {
            return providerStageNumber;
        }

        public String getProductClassification() {
            return productClassification;
        }

        public String getProductSetting() {
            return productSetting;
        }
}
