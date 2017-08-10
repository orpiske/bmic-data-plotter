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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPInputStream;

public class BmicReader {
    private static final Logger logger = LoggerFactory.getLogger(BmicReader.class);


    public BmicData read(String filename) throws IOException {
        InputStream fileStream = null;
        InputStream gzipStream = null;
        Reader in = null;

        logger.debug("Reading file {}", filename);

        try {
            fileStream = new FileInputStream(filename);
            gzipStream= new GZIPInputStream(fileStream);

            in = new InputStreamReader(gzipStream);

            Iterable<CSVRecord> records = CSVFormat.RFC4180
                    .withCommentMarker('#')
                    .withFirstRecordAsHeader()
                    .withRecordSeparator(';')
                    .withQuote('"')
                    .withQuoteMode(QuoteMode.NON_NUMERIC)
                    .parse(in);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BmicData bmicData = new BmicData();

            for (CSVRecord record : records) {
                Date timeStamp = null;
                try {
                    timeStamp = formatter.parse(record.get(0));
                    bmicData.getTimestamps().add(timeStamp);

                    bmicData.getLoadValues().add(Double.parseDouble(record.get(1)));
                    bmicData.getOpenFds().add(Integer.parseInt(record.get(2)));
                    bmicData.getFreeFds().add(Integer.parseInt(record.get(3)));
                    bmicData.getFreeMems().add(Integer.parseInt(record.get(4)));
                    bmicData.getSwapFrees().add(Integer.parseInt(record.get(5)));
                    bmicData.getSwapCommitteds().add(Integer.parseInt(record.get(6)));
                    bmicData.getEdenInitial().add(Integer.parseInt(record.get(7)));
                    bmicData.getEdenCommitted().add(Integer.parseInt(record.get(8)));
                    bmicData.getEdenMax().add(Integer.parseInt(record.get(9)));
                    bmicData.getEdenUsed().add(Integer.parseInt(record.get(10)));
                    bmicData.getSurvivorInitial().add(Integer.parseInt(record.get(11)));
                    bmicData.getSurvivorCommitted().add(Integer.parseInt(record.get(12)));
                    bmicData.getSurvivorMax().add(Integer.parseInt(record.get(13)));
                    bmicData.getSurvivorUsed().add(Integer.parseInt(record.get(14)));
                    bmicData.getTenuredInitial().add(Integer.parseInt(record.get(15)));
                    bmicData.getTenuredCommitted().add(Integer.parseInt(record.get(16)));
                    bmicData.getTenuredMax().add(Integer.parseInt(record.get(17)));
                    bmicData.getTenuredUsed().add(Integer.parseInt(record.get(18)));
                    bmicData.getPmInitial().add(Integer.parseInt(record.get(19)));
                    bmicData.getPmCommitted().add(Integer.parseInt(record.get(20)));

                    // TODO: fix
                    // bmicData.getPmMax().add(Integer.parseInt(record.get(21)));
                    bmicData.getPmUsed().add(Integer.parseInt(record.get(22)));
                    bmicData.getQueueSizes().add(Integer.parseInt(record.get(23)));
                    bmicData.getConsumers().add(Integer.parseInt(record.get(23)));
                    bmicData.getAck().add(Integer.parseInt(record.get(25)));
                    bmicData.getExp().add(Integer.parseInt(record.get(26)));
                } catch (ParseException e) {
                    logger.warn("Unable to parse record: {}", e.getMessage(), e);
                    continue;
                }
            }

            return bmicData;
        }
        finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(gzipStream);
            IOUtils.closeQuietly(fileStream);
        }
    }
}
