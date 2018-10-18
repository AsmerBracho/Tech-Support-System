/* -------------------------
 * PopulationChartDemo1.java
 * -------------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package manager;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultKeyedValues2DDataset;

public class Chart extends JPanel {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public Chart(String title) {
        
        JPanel chartPanel = createDemoPanel();
        
        Border innerBorder = BorderFactory.createTitledBorder("Graph");
        Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        chartPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        this.setLayout(new BorderLayout());
        
        this.add(chartPanel, BorderLayout.CENTER);
    }

    public static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createStackedBarChart(
            "Efficiency in Days",
            "Days",     // domain axis label
            "Number Of tickets", // range axis label
            dataset,         // data
            PlotOrientation.HORIZONTAL,
            true,            // include legend
            true,            // tooltips
            false            // urls
        );
        return chart;
    }

    /**
     * Creates a dataset.
     *
     * @return A dataset.
     */
    
    
    public static CategoryDataset createDataset() {
        DefaultKeyedValues2DDataset data = new DefaultKeyedValues2DDataset();
        data.addValue(-6.0, "Closed", "70+");
        data.addValue(-8.0, "Closed", "60-69");
        data.addValue(-11.0, "Closed", "50-59");
        data.addValue(-7.0, "Closed", "40-49");
        data.addValue(-14.0, "Closed", "30-39");
        data.addValue(-15.0, "Closed", "20-29");
        data.addValue(-11.0, "Closed", "10-19");
        data.addValue(-21.0, "Closed", "0-9");
        data.addValue(10.0, "Opened", "70+");
        data.addValue(12.0, "Opened", "60-69");
        data.addValue(10.0, "Opened", "50-59");
        data.addValue(14.0, "Opened", "40-49");
        data.addValue(5.0, "Opened", "30-39");
        data.addValue(17.0, "Opened", "20-29");
        data.addValue(4.0, "Opened", "10-19");
        data.addValue(2.0, "Opened", "0-9");
        return data;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    

}
