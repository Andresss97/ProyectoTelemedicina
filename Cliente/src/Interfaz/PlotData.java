/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author Teresa Romero
 */
public class PlotData extends ApplicationFrame {

    ArrayList<Integer> analogData = new ArrayList<Integer>();
    int numberSamples;
    String EMGorECG;

    public PlotData(final String title, ArrayList<Integer> analogData, int numberSamples, String EMGorECG) {
        super(title);
        this.analogData = analogData;
        this.numberSamples = numberSamples;
        this.EMGorECG = EMGorECG;
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
        chartPanel.setVisible(true);
    }

    private JFreeChart createChart(final XYDataset dataset) {

        return ChartFactory.createTimeSeriesChart(
                EMGorECG,
                "Minutes:Seconds",
                "Analog Data Values", //hacer conversión a mV,
                dataset,
                false,
                false,
                false);
    }

    private XYDataset createDataset() {
        final TimeSeries series = new TimeSeries("EMG Plot");
        Millisecond current = new Millisecond();
        //TimeSeriesDataItem time = new TimeSeriesDataItem(RegularTimePeriod milisecond, Number value);

// code
        //long startTime = System.nanoTime();
        for (int i = 0; i < analogData.size(); i++) {
            try {
                series.add(current, new Double(analogData.get(i)));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlotData.class.getName()).log(Level.SEVERE, null, ex);
                }
                Millisecond next = new Millisecond();
                current = next;
            } catch (SeriesException e) {
                System.err.println("Error adding to series");
            }
        }

        return new TimeSeriesCollection(series);
    }

}
