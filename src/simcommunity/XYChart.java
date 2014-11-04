/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simcommunity;

/**
 *
 * @author Raffaele
 */

import java.util.ArrayList;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYChart extends JFrame {

   private static final long serialVersionUID = 1L;

   public XYChart(String applicationTitle, String chartTitle, ArrayList<Float> dSet) {
        super(applicationTitle);

        // based on the dataset we create the chart
        JFreeChart pieChart = ChartFactory.createXYLineChart(chartTitle, "ERA", "OMOGENEITA'", createDataset(dSet),PlotOrientation.VERTICAL, true, true, false);

        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
      
        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
      
        // add to contentPane
        setContentPane(chartPanel);
        
         this.pack();
         this.setVisible(true);
    }
  
   /*private XYDataset createDataset() {
     
      final XYSeries firefox = new XYSeries("Firefox");
      firefox.add(1.0, 1.0);
      firefox.add(2.0, 3.0);
      firefox.add(3.0, 4.0);
     
      final XYSeries chrome = new XYSeries("Chrome");
      chrome.add(1.0, 4.0);
      chrome.add(2.0, 6.0);
      chrome.add(3.0, 5.0);
    
     
      final XYSeries iexplorer = new XYSeries("InternetExplorer");
      iexplorer.add(3.0, 4.0);
      iexplorer.add(4.0, 5.0);
      iexplorer.add(5.0, 4.0);
     
     
      final XYSeriesCollection dataset = new XYSeriesCollection();
      dataset.addSeries(firefox);
      dataset.addSeries(chrome);
      dataset.addSeries(iexplorer);
     
      return dataset;
     
  }*/
   
   private XYDataset createDataset(ArrayList<Float> dSet) {
     
      final XYSeries omogeneita = new XYSeries("omogeneita");
      int i;
      
       for (i = 0; i < dSet.size(); i++)          
            omogeneita.add(i, dSet.get(i));
   
       
     
      final XYSeriesCollection dataset = new XYSeriesCollection();
      dataset.addSeries(omogeneita);

     
      return dataset;
     
  }

}
