package main;

import java.io.IOException;
import java.util.ArrayList;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

public class DrawOutput {

    public DrawOutput() {
    }

    public void drawOptigrating(String fileName,double[] xData, double[] transmittivity, double[] reflectivity) throws IOException {

        XYChart chart = new XYChartBuilder().width(640).height(480).xAxisTitle("Wavelength (um)").yAxisTitle("Reflectivity/Transmittivity (dB)").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.addSeries("Reflectivity", xData,reflectivity);
        chart.addSeries("Transmittivity", xData,transmittivity);
        chart.getStyler().setXAxisMin(xData[0]);
        chart.getStyler().setXAxisMax(xData[xData.length-1]);
        chart.getStyler().setXAxisMaxLabelCount(10);
        chart.getStyler().setXAxisDecimalPattern("#.#");
        BitmapEncoder.saveBitmap(chart, fileName, BitmapEncoder.BitmapFormat.PNG);
        new SwingWrapper(chart).displayChart();
    }

    public void drawOptigratingDouble(String fileName,double[] xData, double[] transmittivity, double[] reflectivity) throws IOException {

        XYChart chart = new XYChartBuilder().width(640).height(480).xAxisTitle("Wavelength (um)").yAxisTitle("Reflectivity/Transmittivity (dB)").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.addSeries("Reflectivity", xData,reflectivity);
        chart.addSeries("Transmittivity", xData,transmittivity);
        chart.getStyler().setXAxisMin(xData[0]);
        chart.getStyler().setXAxisMax(xData[xData.length-1]);
        chart.getStyler().setXAxisMaxLabelCount(10);
        chart.getStyler().setXAxisDecimalPattern("#.##");
        BitmapEncoder.saveBitmap(chart, fileName, BitmapEncoder.BitmapFormat.PNG);
        new SwingWrapper(chart).displayChart();
    }

    public void drawScaled(String fileName,double[] xData, double[] transmittivity, double[] reflectivity, double xmin, double xmax) throws IOException {
        XYChart chart = new XYChartBuilder().width(640).height(480).xAxisTitle("Wavelength (um)").yAxisTitle("Reflectivity/Transmittivity (dB)").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.addSeries("Reflectivity", xData,reflectivity);
        chart.addSeries("Transmittivity", xData,transmittivity);
        chart.getStyler().setXAxisMin(xData[0]);
        chart.getStyler().setXAxisMax(xData[1100]);
        BitmapEncoder.saveBitmap(chart, fileName, BitmapEncoder.BitmapFormat.PNG);
        new SwingWrapper(chart).displayChart();
    }

    public void drawBar(String fileName, double[] xData, double[] yData, double[] yData2) throws IOException {
        CategoryChart chart = new CategoryChartBuilder().width(640).height(480).xAxisTitle("Radius (um)").yAxisTitle("Temperature (K)").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideS);
        chart.getStyler().setOverlapped(true);
        chart.addSeries("Cladding", xData,yData);
        chart.addSeries("Core", xData, yData2);
        //chart.getStyler().setLegendVisible(false);
        BitmapEncoder.saveBitmap(chart, fileName, BitmapEncoder.BitmapFormat.PNG);
        //new SwingWrapper(chart).displayChart();
    }

    public void drawBarZoom(String fileName, double[] xData, double[] yData, double[] yData2) throws IOException {
        CategoryChart chart = new CategoryChartBuilder().width(640).height(480).xAxisTitle("Radius (um)").yAxisTitle("Temperature (K)").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideS);
        chart.getStyler().setOverlapped(true);
        chart.addSeries("Cladding", xData,yData);
        chart.addSeries("Core", xData, yData2);
        chart.getStyler().setYAxisMax(1150.0);
        chart.getStyler().setYAxisMin(0.0);
        //chart.getStyler().setLegendVisible(false);
        BitmapEncoder.saveBitmap(chart, fileName, BitmapEncoder.BitmapFormat.PNG);
        new SwingWrapper(chart).displayChart();
    }
}