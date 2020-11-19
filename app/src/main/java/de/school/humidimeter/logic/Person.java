package de.school.humidimeter.logic;


public class Person {
    private static final String TAG = "Person";
    private int id;
    private String firstName;
    private int countryCode;
    private int postalCode;
    private String city;
    private boolean coronaMode;
    private boolean coldSensitive;
    private boolean heatSensitive;
    private boolean ventilationSkip;
    private String lastVentilation;

    public Person() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCoronaMode(boolean coronaMode) {
        this.coronaMode = coronaMode;
    }

    public void setColdSensitive(boolean coldSensitive) {
        this.coldSensitive = coldSensitive;
    }

    public void setHeatSensitive(boolean heatSensitive) {
        this.heatSensitive = heatSensitive;
    }

    public void setVentilationSkip(boolean ventilationSkip) {
        this.ventilationSkip = ventilationSkip;
    }

    public void setLastVentilation(String strLastVentilation) {
//        Log.d(TAG, "##########setLastVentilationLDT: " + strLastVentilation);
        this.lastVentilation = strLastVentilation;
    }

//    public void setLastVentilation(String strLastVentilation) {
//        if (strLastVentilation == null || strLastVentilation.equals("null")) {
//            Log.d(TAG, "str Null???");
//            return;
//        }
//
//        Log.d(TAG, "setLastVentilation: " + lastVentilation);
////        int year = Integer.parseInt(strLastVentilation.substring(1,5));
////        int month = Integer.parseInt(strLastVentilation.substring(6,8));
////        int day = Integer.parseInt(strLastVentilation.substring(9,11));
////        int hour = Integer.parseInt(strLastVentilation.substring(12,14));
////        int min = Integer.parseInt(strLastVentilation.substring(15,17));
//
//        int year = 2020;
//        int month = 11;
//        int day = 18;
//        int hour = 10;
//        int min = 20;
//
//        this.lastVentilation = LocalDateTime.of(year, month, day, hour, min);
//    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCountryCode() {
        return "Deutschland";
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public boolean getCoronaMode() {
        return coronaMode;
    }

    public boolean getColdSensitive() {
        return coldSensitive;
    }

    public boolean getHeatSensitive() {
        return heatSensitive;
    }

    public boolean getVentilationSkip() {
        return ventilationSkip;
    }

    public String getLastVentilation() {
        return lastVentilation;
    }
}
