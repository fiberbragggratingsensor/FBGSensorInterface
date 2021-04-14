package main;

import java.io.IOException;
import java.util.Arrays;

public class ImportData {
    //private String[] simTimes = {"0.0", "0.2", "0.4", "0.6", "0.8", "1.0", "2.0", "4.0", "5.0", "6.0", "10.0", "12.0", "14.0", "15.0"};

    /*public static void main(String[] args) throws IOException{
        createXYThin();
    }*/


    public ImportData() {

    }

    private static void createXY() throws IOException {
        String[] simTimes = {"0.0", "0.2", "0.4", "0.6", "0.8", "1.0", "2.0", "4.0", "5.0", "6.0", "10.0", "12.0", "14.0", "15.0"};
        DrawOutput dop = new DrawOutput();
        for (int i = 0; i < simTimes.length; i++) {
            System.out.println(i);
            FileManager fmRCSV = new FileManager("Assets/" + simTimes[i] + "ms/Reflection_" + simTimes[i] + "ms", "csv");
            FileManager fmTCSV = new FileManager("Assets/" + simTimes[i] + "ms/Transmission_" + simTimes[i] + "ms", "csv");
            //fmTCSV.clearData();
            //fmRCSV.clearData();
            FileManager fmR = new FileManager("Assets/" + simTimes[i] + "ms/Reflection_" + simTimes[i] + "ms", "txt");
            FileManager fmT = new FileManager("Assets/" + simTimes[i] + "ms/Transmission_" + simTimes[i] + "ms", "txt");
            double[] reflectivity = new double[1101];
            double[] transmittivity = new double[1101];
            double[] wavelength = new double[1101];

            reflectivity = fmR.readData(1101, 1);
            transmittivity = fmT.readData(1101, 1);
            wavelength = fmR.readData(1101, 0);

            double maxR = reflectivity[0];
            double minT = transmittivity[0];
            double maxRwl = 0.0;
            double minTwl = 0.0;
            int maxRindex = 0;

            for (int j = 0; j < wavelength.length; j++) {
                //fmRCSV.writeData(wavelength[j] + "," + reflectivity[j] + "\n");
                //fmTCSV.writeData(wavelength[j] + "," + transmittivity[j] + "\n");
                if (transmittivity[j] < minT) {
                    minT = transmittivity[j];
                    minTwl = wavelength[j];
                }
                if (reflectivity[j] > maxR) {
                    maxR = reflectivity[j];
                    maxRwl = wavelength[j];
                    maxRindex = j;
                }
            }
            //System.out.println("max R value: " + maxR + " @ " + maxRwl + " @ index: " + maxRindex);

            //System.out.println("MinR: " + minT + "\tMinR wl: " + minTwl + "\tMaxT: " + maxR + "\tMaxT wl: " + maxRwl);
            //System.out.println("Created wide at " + simTimes[i]);
            dop.drawOptigrating("Assets/" + simTimes[i] + "ms/OptigratingWide" + simTimes[i] + "ms", wavelength, transmittivity, reflectivity);
            double[] reflectivityShrunk = new double[101];
            double[] transmittivityShrunk = new double[101];
            double[] wavelengthShrunk = new double[101];
            if (maxRindex>51) {
                wavelengthShrunk = Arrays.copyOfRange(wavelength,maxRindex-50,maxRindex+50);
                reflectivityShrunk = Arrays.copyOfRange(reflectivity,maxRindex-50,maxRindex+50);
                transmittivityShrunk = Arrays.copyOfRange(transmittivity,maxRindex-50,maxRindex+50);
            }else {
                wavelengthShrunk = Arrays.copyOfRange(wavelength,0,101);
                reflectivityShrunk = Arrays.copyOfRange(reflectivity,0,101);
                transmittivityShrunk = Arrays.copyOfRange(transmittivity,0,101);
            }
            System.out.println("Created thin at " + simTimes[i]);
            dop.drawOptigratingDouble("Assets/" + simTimes[i] + "ms/OptigratingThin" + simTimes[i] + "ms", wavelengthShrunk, transmittivityShrunk, reflectivityShrunk);
        }
    }

    private static void createXYThin() throws IOException {
        int i = 8;
        String[] simTimes = {"0.0", "0.2", "0.4", "0.6", "0.8", "1.0", "2.0", "4.0", "5.0", "6.0", "10.0", "12.0", "14.0", "15.0"};
        DrawOutput dop = new DrawOutput();
        FileManager fmR = new FileManager("Assets/" + simTimes[i] + "ms/Reflection_" + simTimes[i] + "ms", "txt");
        FileManager fmT = new FileManager("Assets/" + simTimes[i] + "ms/Transmission_" + simTimes[i] + "ms", "txt");
        double[] reflectivity = new double[1101];
        double[] transmittivity = new double[1101];
        double[] wavelength = new double[1101];
        reflectivity = fmR.readData(1101, 1);
        transmittivity = fmT.readData(1101, 1);
        wavelength = fmR.readData(1101, 0);

        double maxR = reflectivity[0];
        double minT = transmittivity[0];
        double maxRwl = 0.0;
        double minTwl = 0.0;
        int maxRindex = 0;
        System.out.println("Created wide at " + simTimes[i]);
        dop.drawOptigrating("Assets/" + simTimes[i] + "ms/OptigratingWide" + simTimes[i] + "ms", wavelength, transmittivity, reflectivity);
        double[] reflectivityShrunk = new double[101];
        double[] transmittivityShrunk = new double[101];
        double[] wavelengthShrunk = new double[101];

        for (int j = 0; j < wavelength.length; j++) {
            if (transmittivity[j] < minT) {
                minT = transmittivity[j];
                minTwl = wavelength[j];
            }
            if (reflectivity[j] > maxR) {
                maxR = reflectivity[j];
                maxRwl = wavelength[j];
                maxRindex = j;
            }
        }
        System.out.println("MinR: " + minT + "\tMinR wl: " + minTwl + "\tMaxT: " + maxR + "\tMaxT wl: " + maxRwl);

        if (maxRindex>51) {
            wavelengthShrunk = Arrays.copyOfRange(wavelength,maxRindex-50,maxRindex+50);
            reflectivityShrunk = Arrays.copyOfRange(reflectivity,maxRindex-50,maxRindex+50);
            transmittivityShrunk = Arrays.copyOfRange(transmittivity,maxRindex-50,maxRindex+50);
        }else {
            wavelengthShrunk = Arrays.copyOfRange(wavelength,0,101);
            reflectivityShrunk = Arrays.copyOfRange(reflectivity,0,101);
            transmittivityShrunk = Arrays.copyOfRange(transmittivity,0,101);
        }
        System.out.println("Created thin at " + simTimes[i]);
        dop.drawOptigrating("Assets/" + simTimes[i] + "ms/OptigratingThin" + simTimes[i] + "ms", wavelengthShrunk, transmittivityShrunk, reflectivityShrunk);
    }

    private static void createBar() throws IOException{
        String[] simTimes = {"0.0", "0.2", "0.4", "0.6", "0.8", "1.0", "2.0", "4.0", "5.0", "6.0", "10.0", "12.0", "14.0", "15.0"};
        DrawOutput dop = new DrawOutput();
        int index = 5;

        double[] xdata = {62, 50, 40, 30, 20, 10, 5, 4, 3.8, 2, 1, 0, 1, 2, 3.8, 4, 5, 10, 20, 30, 40, 50, 62};
        double[] claddata = new double[23];
        double[] coredata = new double[23];

        int size = 14;
        double[] outputArrZero = new double[size];
        double[] outputArrOne = new double[size];
        double[] outputArrTwo = new double[size];
        double[] outputArrThree = new double[size];
        double[] outputArrFour = new double[size];
        double[] outputArrFive = new double[size];
        double[] outputArrSix = new double[size];
        double[] outputArrSeven = new double[size];
        double[] outputArrEight = new double[size];
        double[] outputArrNine = new double[size];
        double[] outputArrTen = new double[size];
        double[] outputArrEleven = new double[size];
        double[] outputArrTwelve = new double[size];

        FileManager fm = new FileManager("Assets/flux1000", "csv");
        outputArrZero = fm.readDataCSV(size, 0);
        outputArrOne = fm.readDataCSV(size, 1);
        outputArrTwo = fm.readDataCSV(size, 2);
        outputArrThree = fm.readDataCSV(size, 3);
        outputArrFour = fm.readDataCSV(size, 4);
        outputArrFive = fm.readDataCSV(size, 5);
        outputArrSix = fm.readDataCSV(size, 6);
        outputArrSeven = fm.readDataCSV(size, 7);
        outputArrEight = fm.readDataCSV(size, 8);
        outputArrNine = fm.readDataCSV(size, 9);
        outputArrTen = fm.readDataCSV(size, 10);
        outputArrEleven = fm.readDataCSV(size, 11);
        outputArrTwelve = fm.readDataCSV(size, 12);
        for (int i = 0; i < 23; i++) {
            claddata[i] = 0;
            coredata[i] = 0;
        }


        for (int www = 0; www < simTimes.length; www++) {
            claddata[0] = outputArrTwelve[www];
            claddata[22] = outputArrTwelve[www];
            claddata[1] = outputArrEleven[www];
            claddata[21] = outputArrEleven[www];
            claddata[2] = outputArrTen[www];
            claddata[20] = outputArrTen[www];
            claddata[3] = outputArrNine[www];
            claddata[19] = outputArrNine[www];
            claddata[4] = outputArrEight[www];
            claddata[18] = outputArrEight[www];
            claddata[5] = outputArrSeven[www];
            claddata[17] = outputArrSeven[www];
            claddata[6] = outputArrSix[www];
            claddata[16] = outputArrSix[www];
            claddata[7] = outputArrFive[www];
            claddata[15] = outputArrFive[www];
            claddata[8] = outputArrFour[www];
            claddata[14] = outputArrFour[www];
            claddata[9] = outputArrThree[www];
            claddata[13] = outputArrThree[www];
            claddata[10] = outputArrTwo[www];
            claddata[12] = outputArrTwo[www];
            claddata[11] = outputArrOne[www];
            coredata[7] = outputArrFive[www];
            coredata[15] = outputArrFive[www];
            coredata[8] = outputArrFour[www];
            coredata[14] = outputArrFour[www];
            coredata[9] = outputArrThree[www];
            coredata[13] = outputArrThree[www];
            coredata[10] = outputArrTwo[www];
            coredata[12] = outputArrTwo[www];
            coredata[11] = outputArrOne[www];
            dop.drawBar("Assets/" + simTimes[www] + "ms/TempRadius" + simTimes[www] + "ms",xdata,claddata,coredata);
            dop.drawBarZoom("Assets/" + simTimes[www] + "ms/TempRadiusZoom" + simTimes[www] + "ms",xdata,claddata,coredata);
        }

        /*String out;
        for (int k = 0; k < size; k++) {
            out = "";
            FileManager fmnew = new FileManager("Assets/" + simTimes[k] + "ms/Temperature_" + simTimes[k] + "ms", "csv");
            for (int ind = 0; ind < 22; ind++) {
                out+=claddata[ind] + ",";
            }
            out+=claddata[22];
            fmnew.clearData();
            fmnew.writeData(out);
        }*/
    }
}
