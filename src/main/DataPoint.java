package main;

import java.util.Objects;

public class DataPoint{
    private char scale;
    private Temperature tempKelvin;
    private Temperature tempCelsius;
    private Temperature tempFahrenheit;
    private Temperature tempRankine;

    private long timeMillis;

    //private long wavelengthShift
    //wavelength shift to temperature ex 5nm shift = 500C


    public DataPoint(char scale, double temperature) {
        this.scale = scale;
        this.timeMillis = System.currentTimeMillis();
        this.tempKelvin = new Temperature('k', temperature);
        this.tempCelsius = new Temperature('c', tempKelvin.KtoC(temperature));
        this.tempFahrenheit = new Temperature('f', tempKelvin.KtoF(temperature));
        this.tempRankine = new Temperature('r', tempKelvin.KtoR(temperature));
    }

    public long getTime() {
        return timeMillis;
    }

    public Temperature getTemp() {
        return tempKelvin;
    }

    public Temperature getTempKelvin() {
        return tempKelvin;
    }

    public Temperature getTempCelsius() {
        return tempCelsius;
    }

    public Temperature getTempFahrenheit() {
        return tempFahrenheit;
    }

    public Temperature getTempRankine() {
        return tempRankine;
    }

    public char getScale() {
        return scale;
    }

    private void setTime() {
        this.timeMillis = System.currentTimeMillis();
    }

    public void setTemp(double temperature) {
        this.tempKelvin.setTempK(temperature);
    }

    public void setScale(char scale) {
        this.scale = scale;
    }

    public void setTempKelvin(double temperature) {
        this.tempKelvin.setTempK(temperature);
    }

    public void setTempCelsius(double temperature) {
        this.tempCelsius.setTempC(temperature);
    }

    public void setTempFahrenheit(double temperature) {
        this.tempFahrenheit.setTempF(temperature);
    }

    public void setTempRankine(double temperature) {
        this.tempRankine.setTempR(temperature);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataPoint dataPoint = (DataPoint) o;
        return scale == dataPoint.scale &&
                timeMillis == dataPoint.timeMillis &&
                tempKelvin.compare(dataPoint.getTempKelvin()) == 0 &&
                tempCelsius.compare(dataPoint.getTempCelsius()) == 0 &&
                tempFahrenheit.compare(dataPoint.getTempFahrenheit()) == 0 &&
                tempRankine.compare(dataPoint.getTempRankine()) == 0;
    }

    public boolean equalTemp(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataPoint dataPoint = (DataPoint) o;
        return scale == dataPoint.scale &&
                tempKelvin.compare(dataPoint.getTempKelvin()) == 0 &&
                tempCelsius.compare(dataPoint.getTempCelsius()) == 0 &&
                tempFahrenheit.compare(dataPoint.getTempFahrenheit()) == 0 &&
                tempRankine.compare(dataPoint.getTempRankine()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scale, tempKelvin, tempCelsius, tempFahrenheit, tempRankine);
    }

    @Override
    public String toString() {
        return "DataPoint{" +
                "time=" + timeMillis +
                ", temp=" + tempKelvin +
                '}';
    }

    public int compare(DataPoint dataPoint) {
        if (tempKelvin.compare(dataPoint.getTempKelvin()) == 0) {
            return 0;
        }else if (tempKelvin.compare(dataPoint.getTempKelvin()) < 0) {
            return -1;
        }else if (tempKelvin.compare(dataPoint.getTempKelvin()) > 0) {
            return 1;
        }
        return 0;
    }
}
