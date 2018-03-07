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

package net.orpiske.bmic.main;


import net.orpiske.bmic.plot.BmicData;
import net.orpiske.bmic.plot.BmicPlotter;
import net.orpiske.bmic.plot.BmicReader;
import net.orpiske.bmic.utils.Constants;
import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static CommandLine cmdLine;

    private static String fileName;

    /**
     * Prints the help for the action and exit
     * @param options the options object
     * @param code the exit code
     */
    private static void help(final Options options, int code) {
        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp(Constants.BIN_NAME, options);
        System.exit(code);
    }

    private static void processCommand(String[] args) {
        CommandLineParser parser = new DefaultParser();

        Options options = new Options();

        options.addOption("h", "help", false, "prints the help");
        options.addOption("f", "file", true, "file to plot");

        try {
            cmdLine = parser.parse(options, args);
        } catch (ParseException e) {
            help(options, -1);
        }

        if (cmdLine.hasOption("help")) {
            help(options, 0);
        }

        fileName = cmdLine.getOptionValue('f');
        if (fileName == null) {
            help(options, -1);
        }
    }

    public static void main(String[] args) {
        processCommand(args);

        try {
            BmicReader bmicReader = new BmicReader();

            BmicData bmicData = bmicReader.read(fileName);

            // Removes the gz
            String baseName = FilenameUtils.removeExtension(fileName);
            // Removes the csv
            baseName = FilenameUtils.removeExtension(baseName);

            // Plotter
            BmicPlotter plotter = new BmicPlotter(baseName);
            logger.debug("Number of records to plot: {} ", bmicData.getTimestamps().size());
            for (Date d : bmicData.getTimestamps()) {
                logger.debug("Adding date record for plotting: {}", d);
            }

            plotter.plot(bmicData);

            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        System.exit(1);
    }


}
