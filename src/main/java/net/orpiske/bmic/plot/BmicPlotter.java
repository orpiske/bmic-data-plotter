/*
 *  Copyright 2017 Otavio Rodolfo Piske
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.orpiske.bmic.plot;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.colors.ChartColor;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public class BmicPlotter {
    private String baseName;

    private int outputWidth = 1200;
    private int outputHeight = 700;
    private boolean plotGridLinesVisible = true;

    public BmicPlotter(final String baseName) {
        this.baseName = baseName;
    }

    private XYChart buildCommonChart(String title, String yTitle) {

        // Create Chart
        XYChart chart = new XYChartBuilder()
                .width(outputWidth)
                .height(outputHeight)
                .title(title)
                .xAxisTitle("Time")
                .yAxisTitle(yTitle)
                .build();

        chart.getStyler().setPlotBackgroundColor(ChartColor.getAWTColor(ChartColor.WHITE));
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));

        chart.getStyler().setPlotGridLinesVisible(plotGridLinesVisible);

        chart.getStyler().setYAxisTickMarkSpacingHint(15);

        chart.getStyler().setXAxisLabelRotation(45);

        chart.getStyler().setAxisTickMarkLength(15);
        chart.getStyler().setPlotMargin(0);
        chart.getStyler().setPlotContentSize(.95);
        chart.getStyler().setDatePattern("yyyy-MM-dd HH:mm:ss");

        return chart;
    }


    private void plotQueueSize(List<Date> xData, List<Integer> yData) throws IOException {
        // Create Chart
        XYChart chart = buildCommonChart("Queue Size", "Number of messages");

        // Series
        XYSeries series = chart.addSeries("Queue Size", xData, yData);

        series.setLineColor(XChartSeriesColors.BLUE);
        series.setMarkerColor(Color.LIGHT_GRAY);
        series.setMarker(SeriesMarkers.DIAMOND);
        series.setLineStyle(SeriesLines.SOLID);

        BitmapEncoder.saveBitmap(chart, baseName + "_queue_size.png", BitmapEncoder.BitmapFormat.PNG);
    }

    private void plotEdenMemory(BmicData bmicData) throws IOException {
        // Create Chart
        XYChart chart = buildCommonChart("Eden Memory", "Memory");

        // Series
        XYSeries edenUsedSeries = chart.addSeries("Eden Used", bmicData.getTimestamps(), bmicData.getEdenUsed());

        edenUsedSeries.setLineColor(XChartSeriesColors.BLUE);
        edenUsedSeries.setMarkerColor(Color.LIGHT_GRAY);
        edenUsedSeries.setMarker(SeriesMarkers.DIAMOND);
        edenUsedSeries.setLineStyle(SeriesLines.SOLID);

        XYSeries edenCommitted = chart.addSeries("Eden Committed", bmicData.getTimestamps(),
                bmicData.getEdenCommitted());

        edenCommitted.setLineColor(XChartSeriesColors.GREEN);
        edenCommitted.setMarkerColor(Color.LIGHT_GRAY);
        edenCommitted.setMarker(SeriesMarkers.DIAMOND);
        edenCommitted.setLineStyle(SeriesLines.SOLID);

        BitmapEncoder.saveBitmap(chart, baseName + "_eden_memory.png", BitmapEncoder.BitmapFormat.PNG);
    }


    public void plot(BmicData bmicData) throws IOException {
        plotQueueSize(bmicData.getTimestamps(), bmicData.getQueueSizes());
        plotEdenMemory(bmicData);
    }

    public void setOutputWidth(int outputWidth) {
        this.outputWidth = outputWidth;
    }

    public void setOutputHeight(int outputHeight) {
        this.outputHeight = outputHeight;
    }

    public void setPlotGridLinesVisible(boolean plotGridLinesVisible) {
        this.plotGridLinesVisible = plotGridLinesVisible;
    }
}
