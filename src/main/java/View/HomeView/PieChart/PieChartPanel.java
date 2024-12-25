/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.HomeView.PieChart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ADMIN
 */
public class PieChartPanel extends javax.swing.JPanel {
    private int num1 = 25;
    private int num2 = 100;
    private String color1 = "#ee9ca7";
    private String color2 = "#33FF57";
    private ChartPanel chartPanel;

    public PieChartPanel() {
        initComponents();
        createPieChart();
    }

    public void setValue(int num1, int num2, String color1, String color2) {
        this.num1 = num1;
        this.num2 = num2;
        this.color1 = color1;
        this.color2 = color2;
        createPieChart();
    }

    private void createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Type 1", num1);
        dataset.setValue("Type 2", num2);

        JFreeChart pieChart = ChartFactory.createPieChart(
            "", dataset, false, true, false
        );

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setLabelGenerator(null);
        plot.setShadowPaint(null);
        plot.setOutlinePaint(Color.WHITE);
        plot.setOutlineStroke(new BasicStroke(3.0f));
        plot.setBackgroundPaint(null);
        plot.setSectionOutlinesVisible(true);
        plot.setSectionPaint("Type 1", Color.decode(color1));
        plot.setSectionPaint("Type 2", Color.decode(color2));

        if (chartPanel != null) {
            this.remove(chartPanel);
        }
        // Loại bỏ padding mặc định và giảm khoảng cách của PiePlot
        plot.setInsets(new RectangleInsets(0, 0, 0, 0));
        plot.setInteriorGap(0.0); // Loại bỏ khoảng cách nội bộ
        chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(150, 150));
        this.setLayout(new BorderLayout());
        this.add(chartPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
