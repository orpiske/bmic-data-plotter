package net.orpiske.bmic.plot;

import net.orpiske.bmic.plot.exceptions.BmicEmptyDataSet;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SimpleTest {
    public void plot(final String fileName) throws IOException, BmicEmptyDataSet {
        // Removes the gz
        String baseName = FilenameUtils.removeExtension(fileName);

        // Removes the csv
        baseName = FilenameUtils.removeExtension(baseName);

        BmicReader bmicReader = new BmicReader();
        BmicData bmicData = bmicReader.read(fileName);

        BmicPlotter plotter = new BmicPlotter(baseName);
        plotter.plot(bmicData);
    }

    private void validatePlotFile(File baseDir, List<String> files) {
        for (String reportFileName : files) {
            File reportFile = new File(baseDir, reportFileName);

            assertTrue("Plot file " + reportFile + " does not exist", reportFile.exists());
        }
    }

    @Test
    public void testPlotWellFormed() throws Exception {
        String fileName = this.getClass().getResource("broker-jvm-inspector-ok.csv.gz").getPath();

        plot(fileName);

        File baseDir = new File(fileName).getParentFile();

        validatePlotFile(baseDir,
            Arrays.asList("broker-jvm-inspector-ok_tenured_memory.png",
                "broker-jvm-inspector-ok_memory.png", "broker-jvm-inspector-ok_pm_memory.png",
                "broker-jvm-inspector-ok_survivor_memory.png", "broker-jvm-inspector-ok_queue_data.png",
                "broker-jvm-inspector-ok_eden_memory.png"));
    }


    @Test
    public void testPlotMalformed() throws Exception {
        String fileName = this.getClass().getResource("broker-jvm-inspector-malformed.csv.gz").getPath();

        plot(fileName);

        File baseDir = new File(fileName).getParentFile();

        validatePlotFile(baseDir,
                Arrays.asList("broker-jvm-inspector-malformed_tenured_memory.png",
                        "broker-jvm-inspector-malformed_memory.png", "broker-jvm-inspector-malformed_pm_memory.png",
                        "broker-jvm-inspector-malformed_survivor_memory.png", "broker-jvm-inspector-malformed_queue_data.png",
                        "broker-jvm-inspector-malformed_eden_memory.png"));
    }
}
