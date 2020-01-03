package project.pandemie.visualization;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import project.pandemie.api.IVisual;
import project.pandemie.data.Round;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Plotter implements IVisual {

    static XYChart chartInstance;
    static JPanel chartPanel;
    private String title;
    private String x;
    private String y;
    private int w;
    private int h;

    private boolean started = false;

    private ArrayList<Integer> rounds = new ArrayList<>();
    private ArrayList<Integer> population = new ArrayList<>();


    public Plotter(String title, String x, String y, int w, int h) {
        Visualizer.getVisualizerList().add(this);
        this.title = title;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void visualize(Round r) {
        population.add(r.getCityWrapper().getPopulation().size());
        if (started) {
            chartInstance.updateXYSeries("world", null, population, null);
            chartPanel.revalidate();
            chartPanel.repaint();
        } else {
            initChart();
            started = true;
        }
    }

    private void initChart() {
        chartInstance = new XYChartBuilder().width(w).height(h).title(title).xAxisTitle(x).yAxisTitle(y).build();
        chartInstance.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line).setLegendPosition(Styler.LegendPosition.InsideSW).setInfoPanelVisible(true).setLegendVisible(true);
        chartInstance.addSeries("world", null, population, null);

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> {

            // Create and set up the window.
            JFrame frame = new JFrame("World Population");
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // chart
            chartPanel = new XChartPanel<>(chartInstance);
            frame.add(chartPanel, BorderLayout.CENTER);

            // label
            JLabel label = new JLabel("Visualization", SwingConstants.CENTER);
            frame.add(label, BorderLayout.SOUTH);

            // Display the window.
            frame.pack();
            frame.setVisible(true);
        });
    }

}
