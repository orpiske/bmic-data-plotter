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

import net.orpiske.bmic.plot.exceptions.BmicEmptyDataSet;
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
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public class BmicPlotter extends AbstractBmicPlotter {
    private String baseName;

    public BmicPlotter(final String baseName) {
        this.baseName = baseName;
    }

    protected void validateDataSet(final List<?> xData, final List<?> yData) throws BmicEmptyDataSet {
        if (xData == null || xData.size() == 0) {
            throw new BmicEmptyDataSet("The 'X' column data set is empty");
        }

        if (yData == null || yData.size() == 0) {
            throw new BmicEmptyDataSet("The 'Y' column data set is empty");
        }
    }


    protected void plotQueueData(final BmicData bmicData) throws IOException, BmicEmptyDataSet {
        // Create Chart
        XYChart chart = buildCommonChart("Queue Size", "Number of messages");

        validateDataSet(bmicData.getTimestamps(), bmicData.getQueueSizes());

        // Series
        XYSeries queueSeries = chart.addSeries("Queue Size", bmicData.getTimestamps(),
                bmicData.getQueueSizes());

        queueSeries.setLineColor(XChartSeriesColors.BLUE);
        queueSeries.setMarkerColor(Color.LIGHT_GRAY);
        queueSeries.setMarker(SeriesMarkers.DIAMOND);
        queueSeries.setLineStyle(SeriesLines.SOLID);

        validateDataSet(bmicData.getTimestamps(), bmicData.getExp());

        XYSeries expSeries = chart.addSeries("Expired Messages", bmicData.getTimestamps(),
                bmicData.getExp());

        expSeries.setLineColor(XChartSeriesColors.GREEN);
        expSeries.setMarkerColor(Color.LIGHT_GRAY);
        expSeries.setMarker(SeriesMarkers.NONE);
        expSeries.setLineStyle(SeriesLines.SOLID);

        BitmapEncoder.saveBitmap(chart, baseName + "_queue_data.png", BitmapEncoder.BitmapFormat.PNG);
    }


    protected void plotEdenMemory(final BmicData bmicData) throws IOException, BmicEmptyDataSet {
        // Create Chart
        XYChart chart = buildCommonChart("Eden Memory", "Memory (Megabytes)");

        validateDataSet(bmicData.getTimestamps(), bmicData.getEdenUsed());

        // Series
        XYSeries edenUsedSeries = chart.addSeries("Eden Used", bmicData.getTimestamps(),
                bmicData.getEdenUsed());

        edenUsedSeries.setLineColor(XChartSeriesColors.BLUE);
        edenUsedSeries.setMarkerColor(Color.LIGHT_GRAY);
        edenUsedSeries.setMarker(SeriesMarkers.NONE);
        edenUsedSeries.setLineStyle(SeriesLines.SOLID);

        validateDataSet(bmicData.getTimestamps(), bmicData.getEdenCommitted());

        XYSeries edenCommitted = chart.addSeries("Eden Committed", bmicData.getTimestamps(),
                bmicData.getEdenCommitted());

        edenCommitted.setLineColor(XChartSeriesColors.GREEN);
        edenCommitted.setMarkerColor(Color.LIGHT_GRAY);
        edenCommitted.setMarker(SeriesMarkers.NONE);
        edenCommitted.setLineStyle(SeriesLines.SOLID);

        BitmapEncoder.saveBitmap(chart, baseName + "_eden_memory.png", BitmapEncoder.BitmapFormat.PNG);
    }

    protected void plotOSMemory(final BmicData bmicData) throws IOException, BmicEmptyDataSet {
        // Create Chart
        XYChart chart = buildCommonChart("OS Memory", "Memory (Megabytes)");

        validateDataSet(bmicData.getTimestamps(), bmicData.getFreeMems());

        // Series
        XYSeries edenUsedSeries = chart.addSeries("Free Memory", bmicData.getTimestamps(),
                bmicData.getFreeMems());

        edenUsedSeries.setLineColor(XChartSeriesColors.BLUE);
        edenUsedSeries.setMarkerColor(Color.LIGHT_GRAY);
        edenUsedSeries.setMarker(SeriesMarkers.NONE);
        edenUsedSeries.setLineStyle(SeriesLines.SOLID);

        validateDataSet(bmicData.getTimestamps(), bmicData.getSwapFrees());

        XYSeries edenCommitted = chart.addSeries("Free Swap", bmicData.getTimestamps(),
                bmicData.getSwapFrees());

        edenCommitted.setLineColor(XChartSeriesColors.GREEN);
        edenCommitted.setMarkerColor(Color.LIGHT_GRAY);
        edenCommitted.setMarker(SeriesMarkers.NONE);
        edenCommitted.setLineStyle(SeriesLines.SOLID);

        BitmapEncoder.saveBitmap(chart, baseName + "_memory.png", BitmapEncoder.BitmapFormat.PNG);
    }


    protected void plotSurvivorMemory(final BmicData bmicData) throws IOException, BmicEmptyDataSet {
        // Create Chart
        XYChart chart = buildCommonChart("Survivor Memory", "Memory (Megabytes)");

        validateDataSet(bmicData.getTimestamps(), bmicData.getSurvivorUsed());

        // Series
        XYSeries edenUsedSeries = chart.addSeries("Survivor Used", bmicData.getTimestamps(),
                bmicData.getSurvivorUsed());

        edenUsedSeries.setLineColor(XChartSeriesColors.BLUE);
        edenUsedSeries.setMarkerColor(Color.LIGHT_GRAY);
        edenUsedSeries.setMarker(SeriesMarkers.NONE);
        edenUsedSeries.setLineStyle(SeriesLines.SOLID);

        validateDataSet(bmicData.getTimestamps(), bmicData.getSurvivorCommitted());

        XYSeries edenCommitted = chart.addSeries("Survivor Committed", bmicData.getTimestamps(),
                bmicData.getSurvivorCommitted());

        edenCommitted.setLineColor(XChartSeriesColors.GREEN);
        edenCommitted.setMarkerColor(Color.LIGHT_GRAY);
        edenCommitted.setMarker(SeriesMarkers.NONE);
        edenCommitted.setLineStyle(SeriesLines.SOLID);

        BitmapEncoder.saveBitmap(chart, baseName + "_survivor_memory.png", BitmapEncoder.BitmapFormat.PNG);
    }

    protected void plotTenuredMemory(final BmicData bmicData) throws IOException, BmicEmptyDataSet {
        // Create Chart
        XYChart chart = buildCommonChart("Tenured Memory", "Memory (Megabytes)");

        validateDataSet(bmicData.getTimestamps(), bmicData.getTenuredUsed());

        // Series
        XYSeries edenUsedSeries = chart.addSeries("Tenured Used", bmicData.getTimestamps(),
                bmicData.getTenuredUsed());

        edenUsedSeries.setLineColor(XChartSeriesColors.BLUE);
        edenUsedSeries.setMarkerColor(Color.LIGHT_GRAY);
        edenUsedSeries.setMarker(SeriesMarkers.NONE);
        edenUsedSeries.setLineStyle(SeriesLines.SOLID);

        validateDataSet(bmicData.getTimestamps(), bmicData.getTenuredCommitted());

        XYSeries edenCommitted = chart.addSeries("Tenured Committed", bmicData.getTimestamps(),
                bmicData.getTenuredCommitted());

        edenCommitted.setLineColor(XChartSeriesColors.GREEN);
        edenCommitted.setMarkerColor(Color.LIGHT_GRAY);
        edenCommitted.setMarker(SeriesMarkers.NONE);
        edenCommitted.setLineStyle(SeriesLines.SOLID);

        BitmapEncoder.saveBitmap(chart, baseName + "_tenured_memory.png", BitmapEncoder.BitmapFormat.PNG);
    }

    protected void plotPMMemory(final BmicData bmicData) throws IOException, BmicEmptyDataSet {
        // Create Chart
        XYChart chart = buildCommonChart("PermGen/Metaspace Memory", "Memory (Megabytes)");

        validateDataSet(bmicData.getTimestamps(), bmicData.getPmUsed());

        // Series
        XYSeries edenUsedSeries = chart.addSeries("PermGen/Metaspace Used", bmicData.getTimestamps(),
                bmicData.getPmUsed());

        edenUsedSeries.setLineColor(XChartSeriesColors.BLUE);
        edenUsedSeries.setMarkerColor(Color.LIGHT_GRAY);
        edenUsedSeries.setMarker(SeriesMarkers.NONE);
        edenUsedSeries.setLineStyle(SeriesLines.SOLID);

        validateDataSet(bmicData.getTimestamps(), bmicData.getPmCommitted());

        XYSeries edenCommitted = chart.addSeries("PermGen/Metaspace Committed", bmicData.getTimestamps(),
                bmicData.getPmCommitted());

        edenCommitted.setLineColor(XChartSeriesColors.GREEN);
        edenCommitted.setMarkerColor(Color.LIGHT_GRAY);
        edenCommitted.setMarker(SeriesMarkers.NONE);
        edenCommitted.setLineStyle(SeriesLines.SOLID);

        BitmapEncoder.saveBitmap(chart, baseName + "_pm_memory.png", BitmapEncoder.BitmapFormat.PNG);
    }

    protected void plotFileDescriptor(final BmicData bmicData) throws IOException, BmicEmptyDataSet {
        // Create Chart
        XYChart chart = buildCommonChart("File Descriptors", "Count");

        validateDataSet(bmicData.getTimestamps(), bmicData.getOpenFds());

        // Series
        XYSeries edenUsedSeries = chart.addSeries("Open File Descriptors", bmicData.getTimestamps(),
                bmicData.getOpenFds());

        edenUsedSeries.setLineColor(XChartSeriesColors.BLUE);
        edenUsedSeries.setMarkerColor(Color.LIGHT_GRAY);
        edenUsedSeries.setMarker(SeriesMarkers.NONE);
        edenUsedSeries.setLineStyle(SeriesLines.SOLID);

        validateDataSet(bmicData.getTimestamps(), bmicData.getFreeFds());

        XYSeries edenCommitted = chart.addSeries("Free File Descriptors", bmicData.getTimestamps(),
                bmicData.getFreeFds());

        edenCommitted.setLineColor(XChartSeriesColors.GREEN);
        edenCommitted.setMarkerColor(Color.LIGHT_GRAY);
        edenCommitted.setMarker(SeriesMarkers.NONE);
        edenCommitted.setLineStyle(SeriesLines.SOLID);

        BitmapEncoder.saveBitmap(chart, baseName + "_descriptors.png", BitmapEncoder.BitmapFormat.PNG);
    }

    public void plot(final BmicData bmicData) throws IOException, BmicEmptyDataSet {
        plotQueueData(bmicData);
        plotOSMemory(bmicData);
        plotEdenMemory(bmicData);
        plotSurvivorMemory(bmicData);
        plotTenuredMemory(bmicData);
        plotPMMemory(bmicData);
    }
}
