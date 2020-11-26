import javax.swing.*;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.HorizontalAlignment;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;

public class PlotData extends JDialog {
    // TODO: use Observer to update plot

    JFreeChart chart;
    ChartPanel panel;
    XYPlot plot;
    NumberAxis domain;
    NumberAxis range;
    XYSeriesCollection dataset = new XYSeriesCollection();

    public PlotData() {

    }

    public void prepareGUI()
    {
        if (chart == null || plot == null || panel == null)
        {
            this.setChart();
        }
        chart.setTitle("Attendance Plot");
        setTitle("Plot");
        setSize(400,400);
        setModal(true); 
        panel.setSize(400,400);
        add(panel);
        pack();
        setVisible(true);
    }

    public void setChart()
    {   
        chart = ChartFactory.createScatterPlot("Attendance Plot", "Attendance Percentage", "Student Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        plot = (XYPlot)chart.getPlot();
        domain = (NumberAxis)plot.getDomainAxis();
        domain.setRange(0,100);
        domain.setTickUnit(new NumberTickUnit(10));
        range = (NumberAxis)plot.getRangeAxis();
        panel = new ChartPanel(chart);
    }

    //TODO: add update(Observable o, Object arg)

    public void createDataset(String month, int day, int[] times)
    {
        if (plot == null)
        {
            this.setChart();
        }
        range.setRange(0,times.length);
        range.setTickUnit(new NumberTickUnit(1));

        String seriesName = month + " " + day;
        XYSeries series1 = new XYSeries(seriesName);  
        dataset.addSeries(series1);
        for (int i = 0; i <= 100; i+=10)
        {
            series1.add(i, 0);
        }
        for (int i = 0; i < times.length; i++)
        {
            if (times[i] == 0)
            {
                series1.updateByIndex(0, series1.getY(0).intValue()+1);
            }
            else if (times[i] <= 8)
            {
                series1.updateByIndex(1, series1.getY(1).intValue()+1);
            }
            else if (times[i] <= 15)
            {
                series1.updateByIndex(2, series1.getY(2).intValue()+1);
            }
            else if (times[i] <= 23)
            {
                series1.updateByIndex(3, series1.getY(3).intValue()+1);
            }
            else if (times[i] <= 30)
            {
                series1.updateByIndex(4, series1.getY(4).intValue()+1);
            }
            else if (times[i] <= 38)
            {
                series1.updateByIndex(5, series1.getY(5).intValue()+1);
            }
            else if (times[i] <= 45)
            {
                series1.updateByIndex(6, series1.getY(6).intValue()+1);
            }
            else if (times[i] <= 53)
            {
                series1.updateByIndex(7, series1.getY(7).intValue()+1);
            }
            else if (times[i] <= 60)
            {
                series1.updateByIndex(8, series1.getY(8).intValue()+1);
            }
            else if (times[i] <= 68)
            {
                series1.updateByIndex(9, series1.getY(9).intValue()+1);
            }
            else if (times[i] >= 75)
            {
                series1.updateByIndex(10, series1.getY(10).intValue()+1);
            }
        }
    }

    public void clearDataset()
    {
        dataset.removeAllSeries();
    }
}
