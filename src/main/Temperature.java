package main;

import java.util.Objects;

public class Temperature {
    private char scale;
    private double temp;

    public Temperature(char scale, double temp) {
        this.scale = scale;
        this.temp = temp;
    }

    public char getScale() {
        return scale;
    }

    public double getTemp() {
        return temp;
    }

    public double getTempK() {
        if (scale == 'k' || scale == 'K') {
            return temp;
        }else if (scale == 'c' || scale == 'C') {
            return KtoC(temp);
        }else if (scale == 'f' || scale == 'F') {
            return FtoK(temp);
        }else if (scale == 'r' || scale == 'R') {
            return RtoK(temp);
        }
        return 0;
    }

    public double getTempC() {
        if (scale == 'k' || scale == 'K') {
            return KtoC(temp);
        }else if (scale == 'c' || scale == 'C') {
            return temp;
        }else if (scale == 'f' || scale == 'F') {
            return FtoC(temp);
        }else if (scale == 'r' || scale == 'R') {
            return RtoC(temp);
        }
        return 0;
    }

    public double getTempF() {
        if (scale == 'k' || scale == 'K') {
            return KtoF(temp);
        }else if (scale == 'c' || scale == 'C') {
            return CtoF(temp);
        }else if (scale == 'f' || scale == 'F') {
            return temp;
        }else if (scale == 'r' || scale == 'R') {
            return RtoF(temp);
        }
        return 0;
    }

    public double getTempR() {
        if (scale == 'k' || scale == 'K') {
            return KtoR(temp);
        }else if (scale == 'c' || scale == 'C') {
            return CtoR(temp);
        }else if (scale == 'f' || scale == 'F') {
            return FtoR(temp);
        }else if (scale == 'r' || scale == 'R') {
            return temp;
        }
        return 0;
    }

    public void setScale(char scale) {
        this.scale = scale;
    }

    public void setTemp(double temperature) {
        this.temp = temperature;
    }

    public void setTempK(double temperature) {
        this.temp = temperature;
        this.scale = 'k';
    }

    public void setTempC(double temperature) {
        this.temp = temperature;
        this.scale = 'c';
    }

    public void setTempF(double temperature) {
        this.temp = temperature;
        this.scale = 'f';
    }

    public void setTempR(double temperature) {
        this.temp = temperature;
        this.scale = 'r';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return scale == that.scale &&
                Double.compare(that.temp, temp) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scale, temp);
    }

    @Override
    public String toString() {
        String scaleString = "";
        if (scale == 'k' || scale == 'K') {
            scaleString = "Kelvin";
        }else if (scale == 'c' || scale == 'C') {
            scaleString = "Deg Celsius";
        }else if (scale == 'f' || scale == 'F') {
            scaleString = "Deg Fahrenheit";
        }else if (scale == 'r' || scale == 'R') {
            scaleString = "Deg Rankine";
        }
        return temp + scaleString;
    }

    public double CtoK(double C) {
        return C - 273.15;
    }
    public double CtoF(double C) {
        return (C * 9 / 5) + 32;
    }
    public double CtoR(double C) {
        return FtoR(CtoF(C));
    }
    public double FtoK(double F) {
        return CtoK(FtoC(F));
    }
    public double FtoC(double F) {
        return (F - 32) * 5 / 9;
    }
    public double FtoR(double F) {
        return F - 459.67;
    }
    public double RtoK(double R) {
        return R * 5 / 9;
    }
    public double RtoF(double R) {
        return R + 459.67;
    }
    public double RtoC(double R) {
        return RtoK(KtoC(R));
    }
    public double KtoF(double K) {
        return CtoF(KtoC(K));
    }
    public double KtoC(double K) {
        return K + 273.15;
    }
    public double KtoR(double K) {
        return K * 9 / 5;
    }

    public int compare(Temperature other) {
        if (Double.compare(other.getTemp(),temp) == 0) {
            return 0;
        }else if (Double.compare(other.getTemp(),temp) < 0) {
            return 1;
        }else if (Double.compare(other.getTemp(),temp) > 0) {
            return -1;
        }else {
            return 0;
        }
    }

    public double difference(Temperature other) {
        return other.getTemp() - temp;
    }
}
