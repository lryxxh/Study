package demo;


import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class PieChartDemo1 extends ApplicationFrame
{
  public PieChartDemo1(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel();
    localJPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(localJPanel);
  }

  private static PieDataset createDataset()
  {
    DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
    localDefaultPieDataset.setValue("One", new Double(3));
    localDefaultPieDataset.setValue("Two", new Double(4));
    localDefaultPieDataset.setValue("Three", new Double(5));
    localDefaultPieDataset.setValue("Four", new Double(1));
    localDefaultPieDataset.setValue("Five", new Double(4));
    localDefaultPieDataset.setValue("Six", new Double(9));
    return localDefaultPieDataset;
  }

  private static JFreeChart createChart(PieDataset paramPieDataset)
  {
    JFreeChart localJFreeChart = ChartFactory.createPieChart3D("Pie Chart Demo 1", paramPieDataset, true, true, false);
    TextTitle localTextTitle = localJFreeChart.getTitle();
    localTextTitle.setToolTipText("A title tooltip!");
    PiePlot localPiePlot = (PiePlot)localJFreeChart.getPlot();
    localPiePlot.setLabelFont(new Font("SansSerif", 0, 12));
    localPiePlot.setNoDataMessage("No data available");
    localPiePlot.setCircular(true);
    localPiePlot.setLabelGap(0.02D);
    return localJFreeChart;
  }

  public static JPanel createDemoPanel()
  {
    JFreeChart localJFreeChart = createChart(createDataset());
    return new ChartPanel(localJFreeChart);
  }

  public static void main(String[] paramArrayOfString)
  {
//    PieChartDemo1 localPieChartDemo1 = new PieChartDemo1("JFreeChart: PieChartDemo1.java");
//    localPieChartDemo1.pack();
//    RefineryUtilities.centerFrameOnScreen(localPieChartDemo1);
//    localPieChartDemo1.setVisible(true);
//    
	  XYSeriesCollection collection = new XYSeriesCollection();
	  XYSeries series = new XYSeries("", true);
	  series.add(1,5);
	  series.add(2,3);
	  
	  series.add(4,2);
	  series.add(3,6);
	  series.add(5,8);
	  series.add(6,0);
	  collection.addSeries(series);
	  JFreeChart chart = ChartFactory.createXYLineChart("test", "x", "y", collection, PlotOrientation.VERTICAL, true, false, false);
	  ChartPanel chartPanel = new ChartPanel(chart);
	  JFrame frame = new JFrame();
	  frame.setSize(800, 600);
	  frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  frame.getContentPane().add(chartPanel);
	  frame.setVisible(true);
	  
  }
}