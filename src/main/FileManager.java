package main;

import java.io.*;
import java.util.Objects;

public class FileManager {
    private String filename;
    private String extension;
    private int lines;

    public FileManager(String filename, String extension) {
        this.filename = filename;
        this.extension = extension;
        createFile();
        boolean eof = false;
        int lines = 0;
        try {
            FileReader fr = new FileReader(filename + "." + extension);
            BufferedReader br = new BufferedReader(fr);
            while (!eof){
                if(br.readLine() != null){
                    lines++;
                }else {
                    eof = true;
                }
            }
            br.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        this.lines = lines;
    }

    public void createFile() {
        try {
            File dataFile = new File(filename + "." + extension);
            if (dataFile.createNewFile()) {
                System.out.println("File created: " + dataFile.getName());
            } else {
                System.out.println("File (" + filename + "." + extension + ") already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean writeData(String data) {
        try {
            FileWriter fw = new FileWriter(filename + "." + extension, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
            return false;
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return true;
        }
    }

    public boolean clearData() {
        String data = "";
        try {
            FileWriter fw = new FileWriter(filename + "." + extension);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
            return false;
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return true;
        }
    }

    public String readData() {
        String output;
        try {
            FileReader fr = new FileReader(filename + "." + extension);
            BufferedReader br = new BufferedReader(fr);
            output = br.readLine();
            br.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
        if (output != null) {
            return output;
        }else {
            return null;
        }
    }

    public double[] readDataCSV(int size, int part) {
        if (!extension.equalsIgnoreCase("csv")) return null;
        String output;
        String[] parts = new String[13];
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
        int count = 0;
        try {
            FileReader fr = new FileReader(filename + "." + extension);
            BufferedReader br = new BufferedReader(fr);
            while((output = br.readLine()) != null){
                parts = output.split(",");
                outputArrZero[count] = Double.parseDouble(parts[0]);
                outputArrOne[count] = Double.parseDouble(parts[1]);
                outputArrTwo[count] = Double.parseDouble(parts[2]);
                outputArrThree[count] = Double.parseDouble(parts[3]);
                outputArrFour[count] = Double.parseDouble(parts[4]);
                outputArrFive[count] = Double.parseDouble(parts[5]);
                outputArrSix[count] = Double.parseDouble(parts[6]);
                outputArrSeven[count] = Double.parseDouble(parts[7]);
                outputArrEight[count] = Double.parseDouble(parts[8]);
                outputArrNine[count] = Double.parseDouble(parts[9]);
                outputArrTen[count] = Double.parseDouble(parts[10]);
                outputArrEleven[count] = Double.parseDouble(parts[11]);
                outputArrTwelve[count] = Double.parseDouble(parts[12]);
                count++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        switch(part) {
            case 0:return outputArrZero;
            case 1:return outputArrOne;
            case 2:return outputArrTwo;
            case 3:return outputArrThree;
            case 4:return outputArrFour;
            case 5:return outputArrFive;
            case 6:return outputArrSix;
            case 7:return outputArrSeven;
            case 8:return outputArrEight;
            case 9:return outputArrNine;
            case 10:return outputArrTen;
            case 11:return outputArrEleven;
            case 12:return outputArrTwelve;
            default:return null;
        }
    }

    public double[] readData(int size, int part) {
        String output;
        String[] parts = new String[2];
        double[] outputArrZero = new double[size];
        double[] outputArrOne = new double[size];
        int count = 0;
        try {
            FileReader fr = new FileReader(filename + "." + extension);
            BufferedReader br = new BufferedReader(fr);
            while((output = br.readLine()) != null){
                parts = output.split(" ");
                outputArrZero[count] = Double.parseDouble(parts[0]);
                outputArrOne[count] = Double.parseDouble(parts[1]);
                count++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        switch(part) {
            case 0:return outputArrZero;
            case 1:return outputArrOne;
            default:return null;
        }
    }

    public double[] readCSVData(int size, int part) {
        String output;
        String[] parts = new String[2];
        double[] outputArrZero = new double[size];
        double[] outputArrOne = new double[size];
        int count = 0;
        try {
            FileReader fr = new FileReader(filename + "." + extension);
            BufferedReader br = new BufferedReader(fr);
            while((output = br.readLine()) != null){
                parts = output.split(",");
                outputArrZero[count] = Double.parseDouble(parts[0]);
                outputArrOne[count] = Double.parseDouble(parts[1]);
                count++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        switch(part) {
            case 0:return outputArrZero;
            case 1:return outputArrOne;
            default:return null;
        }
    }

    public String readDataAtLine(int line) {
        String output = "";
        int i = 0;
        try {
            FileReader fr = new FileReader(filename + "." + extension);
            BufferedReader br = new BufferedReader(fr);
            while (i != line) {
                output = br.readLine();
                i++;
            }

            br.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
        if (output.equalsIgnoreCase(null)) {
            return output;
        }else {
            return null;
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "FileManager{" +
                "filename='" + filename + '\'' +
                ", extension='" + extension + '\'' +
                ", lines=" + lines +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileManager that = (FileManager) o;
        return lines == that.lines && filename.equals(that.filename) && extension.equals(that.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename, extension, lines);
    }
}
