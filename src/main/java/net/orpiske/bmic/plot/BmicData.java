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

import java.util.*;

// TODO: lots of duplicated code here that can be removed

/**
 * Bmic data container
 */
public class BmicData {
    private Set<BmicRecord> bmicRecordSet = new TreeSet<>();

    public void add(BmicRecord record) {
        bmicRecordSet.add(record);
    }

    public List<Date> getTimestamps() {
        List<Date> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getTimestamp()));

        return list;
    }

    public List<Integer> getQueueSizes() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getQueueSize()));

        return list;
    }

    public List<Double> getLoadValues() {
        List<Double> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getLoadValue()));

        return list;
    }

    public List<Integer> getOpenFds() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getOpenFds()));

        return list;
    }

    public List<Integer> getFreeFds() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getFreeFds()));

        return list;
    }

    public List<Integer> getFreeMems() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getFreeMem()));

        return list;
    }

    public List<Integer> getSwapFrees() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getSwapFree()));

        return list;
    }

    public List<Integer> getSwapCommitteds() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getSwapCommitted()));

        return list;
    }

    public List<Integer> getEdenInitial() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getEdenInitial()));

        return list;
    }

    public List<Integer> getEdenCommitted() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getEdenCommitted()));

        return list;
    }

    public List<Integer> getEdenMax() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getEdenMax()));

        return list;
    }

    public List<Integer> getEdenUsed() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getEdenUsed()));

        return list;
    }

    public List<Integer> getSurvivorInitial() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getSurvivorInitial()));

        return list;
    }

    public List<Integer> getSurvivorCommitted() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getSurvivorCommitted()));

        return list;
    }

    public List<Integer> getSurvivorMax() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getSurvivorMax()));

        return list;
    }

    public List<Integer> getSurvivorUsed() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getSurvivorUsed()));

        return list;
    }

    public List<Integer> getTenuredInitial() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getTenuredInitial()));

        return list;
    }

    public List<Integer> getTenuredCommitted() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getTenuredCommitted()));

        return list;
    }

    public List<Integer> getTenuredMax() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getTenuredMax()));

        return list;
    }

    public List<Integer> getTenuredUsed() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getTenuredUsed()));

        return list;
    }

    public List<Integer> getPmInitial() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getPmInitial()));

        return list;
    }

    public List<Integer> getPmCommitted() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getPmCommitted()));

        return list;
    }

    public List<Long> getPmMax() {
        List<Long> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getPmMax()));

        return list;
    }

    public List<Integer> getPmUsed() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getPmUsed()));

        return list;
    }

    public List<Integer> getConsumers() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getConsumer()));

        return list;
    }

    public List<Integer> getAck() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getAck()));

        return list;
    }

    public List<Integer> getExp() {
        List<Integer> list = new LinkedList<>();

        bmicRecordSet.stream().forEach(item->list.add(item.getExp()));

        return list;
    }
}
