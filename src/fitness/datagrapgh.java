/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness;

import java.awt.Dimension;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author hp
 */
public class datagrapgh {

    JFrame frame;
    public datagrapgh(String table,String y) throws SQLException {
            frame = new JFrame();
            Calendar cal = Calendar.getInstance();
            DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
            cal.add(Calendar.DATE,-8);
            XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries series1 = new XYSeries(table);
            connec c = new connec();
            for(int i=0;i<7;i++)
            {
                cal.add(Calendar.DATE, 1);
                String s=df.format(cal.getTime());
                c.connect();
                if(table.equals("consumption"))
                {
                    c.rs=c.st.executeQuery("SELECT calories FROM consumption WHERE uid='"+temp.getId()+"' AND date='"+s+"'");
                    while(c.rs.next())
                    {
                        //System.out.println("hello");
                        String gra=c.rs.getString("calories");
                        int n= Integer.parseInt(gra);
                        series1.add(i,n);
                    }
                }
                else
                {
                    c.rs=c.st.executeQuery("SELECT calories_burned FROM workout WHERE uid='"+temp.getId()+"' AND date='"+s+"'");
                        while(c.rs.next())
                    {
                        //System.out.println("hello");
                        String gra=c.rs.getString("calories_burned");
                        int n= Integer.parseInt(gra);
                        series1.add(i,n);
                    }
                }    
                
            }
            c.disconnect();
            dataset.addSeries(series1);
            JFreeChart chart = ChartFactory.createXYLineChart("Fitness analyisis","day", y, dataset,PlotOrientation.VERTICAL,true,true,false);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(400, 400));
            chartPanel.setBounds(100, 100, 400, 400);
            chartPanel.setVisible(true);
            frame.setSize(500,500);
            frame.setVisible(true);
            frame.add(chartPanel);
            
            
    }
    
    
    
}
