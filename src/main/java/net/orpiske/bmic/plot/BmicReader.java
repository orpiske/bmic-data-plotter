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
            gzipStream = new GZIPInputStream(fileStream);

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
                    BmicRecord bmicRecord = new BmicRecord();
                    bmicRecord.setTimestamp(timeStamp);

                    bmicRecord.setLoadValue(Double.parseDouble(record.get(1)));
                    bmicRecord.setOpenFds(Integer.parseInt(record.get(2)));
                    bmicRecord.setFreeFds(Integer.parseInt(record.get(3)));
                    bmicRecord.setFreeMem(Integer.parseInt(record.get(4)));
                    bmicRecord.setSwapFree(Integer.parseInt(record.get(5)));
                    bmicRecord.setSwapCommitted(Integer.parseInt(record.get(6)));
                    bmicRecord.setEdenInitial(Integer.parseInt(record.get(7)));
                    bmicRecord.setEdenCommitted(Integer.parseInt(record.get(8)));
                    bmicRecord.setEdenMax(Integer.parseInt(record.get(9)));
                    bmicRecord.setEdenUsed(Integer.parseInt(record.get(10)));
                    bmicRecord.setSurvivorInitial(Integer.parseInt(record.get(11)));
                    bmicRecord.setSurvivorCommitted(Integer.parseInt(record.get(12)));
                    bmicRecord.setSurvivorMax(Integer.parseInt(record.get(13)));
                    bmicRecord.setSurvivorUsed(Integer.parseInt(record.get(14)));
                    bmicRecord.setTenuredInitial(Integer.parseInt(record.get(15)));
                    bmicRecord.setTenuredCommitted(Integer.parseInt(record.get(16)));
                    bmicRecord.setTenuredMax(Integer.parseInt(record.get(17)));
                    bmicRecord.setTenuredUsed(Integer.parseInt(record.get(18)));
                    bmicRecord.setPmInitial(Integer.parseInt(record.get(19)));
                    bmicRecord.setPmCommitted(Integer.parseInt(record.get(20)));
                    bmicRecord.setPmMax(Long.parseLong(record.get(21)));
                    bmicRecord.setPmUsed(Integer.parseInt(record.get(22)));
                    bmicRecord.setQueueSize(Integer.parseInt(record.get(23)));
                    bmicRecord.setConsumer(Integer.parseInt(record.get(23)));
                    bmicRecord.setAck(Integer.parseInt(record.get(25)));
                    bmicRecord.setExp(Integer.parseInt(record.get(26)));

                    bmicData.add(bmicRecord);
                } catch (Throwable t) {
                    logger.warn("Unable to parse record: {}", t.getMessage(), t);
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
