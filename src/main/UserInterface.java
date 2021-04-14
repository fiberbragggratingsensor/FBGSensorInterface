package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

public class UserInterface {
    private JPanel optigratingPanel;
    private JPanel comsolPanel;
    private JPanel mainPanel;
    private JLabel optigratingLabel;
    private JLabel comsolLabel;
    private JButton rwdTimeBtn;
    private JButton fwdTimeBtn;
    private JLabel displayLabel;
    private JCheckBox zoomTemperatureCheckBox;
    private JCheckBox zoomWavelengthCheckBox;
    private String[] simTimes = {"0.0", "0.2", "0.4", "0.6","0.8", "1.0", "2.0", "4.0","5.0", "6.0", "10.0", "12.0", "14.0", "15.0"};
    static private int timeIndex;
    static private boolean zoomTemp;
    static private boolean zoomWavelength;

    public UserInterface() {
        fwdTimeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fwdTimeBtn.setText("Forward in Time");
                rwdTimeBtn.setText("Backward in Time");
                rwdTimeBtn.setEnabled(true);
                zoomTemperatureCheckBox.setEnabled(true);
                zoomWavelengthCheckBox.setEnabled(true);
                if (timeIndex >= -1 && timeIndex < 13) {
                    timeIndex++;
                }
                try {
                    updateFrame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        rwdTimeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String temp = "";
                if (timeIndex > 0 && timeIndex <= 13) {
                    timeIndex--;
                }
                try {
                    updateFrame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        zoomTemperatureCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (zoomTemp) {
                    zoomTemp = false;
                }else {
                    zoomTemp = true;
                }
                try {
                    updateFrame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        zoomWavelengthCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (zoomWavelength) {
                    zoomWavelength = false;
                }else {
                    zoomWavelength = true;
                }
                System.out.println("zoom " + zoomWavelength);
                try {
                    updateFrame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private double[] getDetails(){
        FileManager fmr = new FileManager("Assets/" + simTimes[timeIndex] + "ms/Reflection_" + simTimes[timeIndex] + "ms", "csv");
        FileManager fmt = new FileManager("Assets/" + simTimes[timeIndex] + "ms/Transmission_" + simTimes[timeIndex] + "ms", "csv");
        double[] wavelength = new double[1100];
        double[] reflectivity = new double[1100];
        double[] transmittivity = new double[1100];

        reflectivity = fmr.readCSVData(1101, 1);
        transmittivity = fmt.readCSVData(1101, 1);
        wavelength = fmr.readCSVData(1101, 0);

        double minT = transmittivity[0];
        double maxR = reflectivity[0];
        double minTwl = 0.0;
        double maxRwl = 0.0;

        for (int j = 0; j < wavelength.length; j++) {
            if (transmittivity[j] < minT) {
                minT = transmittivity[j];
                minTwl = wavelength[j];
            }
            if (reflectivity[j] > maxR) {
                maxR = reflectivity[j];
                maxRwl = wavelength[j];
            }
        }
        double[] output = {maxR, maxRwl, minT, minTwl};
        return output;
    }

    public void updateFrame() throws IOException {
        BufferedImage comsolImage = null;
        BufferedImage optigratingImage = null;
        if (zoomTemp == false) {
            comsolImage = ImageIO.read(new FileInputStream("Assets/" + simTimes[timeIndex] + "ms/TempRadiusZoom" + simTimes[timeIndex] + "ms.png"));
        }else {
            comsolImage = ImageIO.read(new FileInputStream("Assets/" + simTimes[timeIndex] + "ms/TempRadius" + simTimes[timeIndex] + "ms.png"));
        }
        if (zoomWavelength == false) {
            optigratingImage = ImageIO.read(new FileInputStream("Assets/" + simTimes[timeIndex] + "ms/OptigratingWide" + simTimes[timeIndex] + "ms.png"));
        }else {
            optigratingImage = ImageIO.read(new FileInputStream("Assets/" + simTimes[timeIndex] + "ms/OptigratingThin" + simTimes[timeIndex] + "ms.png"));
        }
        comsolLabel.setText("");
        comsolLabel.setIcon(new ImageIcon(comsolImage));
        comsolLabel.setVisible(true);
        optigratingLabel.setText("");
        optigratingLabel.setIcon(new ImageIcon(optigratingImage));
        optigratingLabel.setVisible(true);
        double[] details = getDetails();
        DecimalFormat wldf = new DecimalFormat("#.####");
        DecimalFormat dbdf = new DecimalFormat("###.##");
        displayLabel.setText("<html><p>Time: " + simTimes[timeIndex] + " ms" + "</p>" +
                "<p>Maximum Reflectivity: " + dbdf.format(details[1]) + "\t" + " dB at " + wldf.format(details[1]) + " um" + "</p>" +
                "<p>Maximum Transmittivity: " + dbdf.format(details[2]) + "\t" + " dB at " + wldf.format(details[3]) + " um" + "</p></html>");
    }

    public static void main(String[] args) {
        timeIndex = -1;
        zoomTemp = false;
        zoomWavelength = false;
        JFrame mainFrame = new JFrame("Sensor Interface");
        mainFrame.setContentPane(new UserInterface().mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        BufferedImage logoIcon = null;
        try {
            logoIcon = ImageIO.read(new FileInputStream("Images/Logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainFrame.setIconImage(new ImageIcon(logoIcon).getImage());

    }

    private void createUIComponents() throws IOException{
        BufferedImage comsolImage = ImageIO.read(new FileInputStream("Images/logo.png"));
        BufferedImage optigratingImage = ImageIO.read(new FileInputStream("Images/logo.png"));
        Image scaledCImage = comsolImage.getScaledInstance(320,320,Image.SCALE_SMOOTH);
        Image scaledOImage = optigratingImage.getScaledInstance(320,320,Image.SCALE_SMOOTH);
        comsolLabel = new JLabel(new ImageIcon(scaledCImage));
        //comsolLabel = new JLabel();
        comsolLabel.setVisible(true);
        //optigratingLabel = new JLabel();
        optigratingLabel = new JLabel(new ImageIcon(scaledOImage));
        optigratingLabel.setVisible(true);
    }
}