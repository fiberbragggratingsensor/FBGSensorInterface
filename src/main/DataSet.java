package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DataSet {
    private int len;
    private ArrayList<DataPoint> dataSetB;
    private DataPoint[] dataSet;
    private LocalDateTime time;
    private DateTimeFormatter shortTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
    private FileManager fm;

    public DataSet(int len, DataPoint[] dataSet) {
        this.time = LocalDateTime.now();
        this.len = len;
        this.dataSet = dataSet;
        this.dataSetB = new ArrayList<DataPoint>();
        fm = new FileManager("Data" + time.format(shortTimeFormat),"txt");
    }

    //TODO: Stat analysis, linear regression, ANOVA, etc
    public double calcMean() {
        double sum = 0;
        for (DataPoint dataPoint:dataSet) {
            sum += dataPoint.getTempKelvin().getTempK();
        }
        return sum/len;
    }

    public long calcMeanTime() {
        long sum = 0;
        for (DataPoint dataPoint:dataSet) {
            sum += dataPoint.getTime();
        }
        return sum/len;
    }

    public double calcMin() {
        double min = dataSet[0].getTempKelvin().getTempK();
        for (DataPoint dataPoint:dataSet) {
            if (dataPoint.getTempKelvin().getTempK() < min) {
                min = dataPoint.getTempKelvin().getTempK();
            }
        }
        return min;
    }

    public double calcMax() {
        double max = dataSet[0].getTempKelvin().getTempK();
        for (DataPoint dataPoint:dataSet) {
            if (dataPoint.getTempKelvin().getTempK() > max) {
                max = dataPoint.getTempKelvin().getTempK();
            }
        }
        return max;
    }

    public double calcMaxDelta() {
        return calcMax() - calcMin();
    }

    public void writeSet() {
        for (DataPoint dataPoint:dataSet) {
            fm.writeData(dataPoint.toString() + "\n");
        }
    }
}
