import javax.swing.*;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
    // TODO: create scatter plot using attenadance per student
    // TODO: y-axis is number of students (0 to total row in table)
    // TODO: x-axis is % of attendance (0%, 10%, ..., 90%, 100%) 100% -> >=75 min
    // TODO: each attendance column represent a population
    // TODO: use Observer to update plot

    public PlotData() {

    }

    public void prepareGUI()
    {
        XYDataset dataset = createDataset();
        
        JFreeChart chart = ChartFactory.createScatterPlot("Attendance Plot", "X-Axis", "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        // XYPlot plot = (XYPlot)chart.getPlot();
        ChartPanel panel = new ChartPanel(chart);
        chart.setTitle("Attendance Plot");
        setTitle("Plot");
        setSize(400,400);
        setModal(true); 
        panel.setSize(400,400);
        add(panel);
        pack();
        setVisible(true);
    }

    private XYDataset createDataset()
    {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Boys");  
        series1.add(1, 72.9);  
        series1.add(2, 81.6);  
        series1.add(3, 88.9);  
        series1.add(4, 96);  
        series1.add(5, 102.1);  
        series1.add(6, 108.5);  
        series1.add(7, 113.9);  
        series1.add(8, 119.3);  
        series1.add(9, 123.8);  
        series1.add(10, 124.4);  
    
        dataset.addSeries(series1);  
        
        XYSeries series2 = new XYSeries("Girls");  
        series2.add(1, 72.5);  
        series2.add(2, 80.1);  
        series2.add(3, 87.2);  
        series2.add(4, 94.5);  
        series2.add(5, 101.4);  
        series2.add(6, 107.4);  
        series2.add(7, 112.8);  
        series2.add(8, 118.2);  
        series2.add(9, 122.9);  
        series2.add(10, 123.4);  
      
        dataset.addSeries(series2);  

        return dataset;
    }
}
