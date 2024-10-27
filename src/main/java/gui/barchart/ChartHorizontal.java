package gui.barchart;

import gui.barchart.blankchart.BlankPlotChart;
import gui.barchart.blankchart.BlankPlotChartHorizontal;
import gui.barchart.blankchart.BlankPlotChatRender;
import gui.barchart.blankchart.BlankPlotChatRenderHorizontal;
import gui.barchart.blankchart.SeriesSize;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class ChartHorizontal extends javax.swing.JPanel {

    private List<ModelLegend> legends = new ArrayList<>();
    private List<ModelChart> model = new ArrayList<>();
    private final int seriesSize = 12;
    private final int seriesSpace = 6;
    private final Animator animator;
    private float animate;

    public ChartHorizontal() {
        initComponents();
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                repaint();
            }
        };
        animator = new Animator(800, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        blankPlotChart.setBlankPlotChatRender(new BlankPlotChatRenderHorizontal() {
            @Override
            public String getLabelText(int index) {
                return model.get(index).getLabel();
            }

            @Override
            public void renderSeries(BlankPlotChartHorizontal chart, Graphics2D g2, SeriesSize size, int index) {
                double totalSeriesHeight = (seriesSize * legends.size()) + (seriesSpace * (legends.size() - 1));
                double y = (size.getHeight() - totalSeriesHeight) / 2;

                for (int i = 0; i < legends.size(); i++) {
                    ModelLegend legend = legends.get(i);
                    g2.setColor(legend.getColor());
                    double seriesValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getWidth()) * animate;
                    g2.fillRect((int) (size.getX()), (int) (size.getY() + y), (int) seriesValues, seriesSize);
                    y += seriesSpace + seriesSize;
                }
            }

        });
    }

    public void addLegend(String name, Color color) {
        ModelLegend data = new ModelLegend(name, color);
        legends.add(data);
        panelLegend.add(new LegendItem(data));
        panelLegend.repaint();
        panelLegend.revalidate();
    }

    public void addData(ModelChart data) {
        model.add(data);
        blankPlotChart.setLabelCount(model.size());
        double max = data.getMaxValues();
        if (max > blankPlotChart.getMaxValues()) {
            blankPlotChart.setMaxValues(max);
        }
    }

    public void clear() {
        animate = 0;
        blankPlotChart.setLabelCount(0);
        model.clear();
        repaint();
    }

    public void start() {
        if (!animator.isRunning()) {
            animator.start();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLegend = new javax.swing.JPanel();
        blankPlotChart = new gui.barchart.blankchart.BlankPlotChartHorizontal();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        panelLegend.setOpaque(false);
        panelLegend.setPreferredSize(new java.awt.Dimension(70, 20));
        panelLegend.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        add(panelLegend, java.awt.BorderLayout.SOUTH);
        add(blankPlotChart, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.barchart.blankchart.BlankPlotChartHorizontal blankPlotChart;
    private javax.swing.JPanel panelLegend;
    // End of variables declaration//GEN-END:variables
}
