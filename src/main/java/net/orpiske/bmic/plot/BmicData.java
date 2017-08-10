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

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BmicData {
    private List<Date> timestamps = new LinkedList<>();
    private List<Double> loadValues = new LinkedList<>();
    private List<Integer> openFds = new LinkedList<>();
    private List<Integer> freeFds = new LinkedList<>();
    private List<Integer> freeMems = new LinkedList<>();
    private List<Integer> swapFrees = new LinkedList<>();
    private List<Integer> swapCommitteds = new LinkedList<>();
    private List<Integer> edenInitial = new LinkedList<>();
    private List<Integer> edenCommitted = new LinkedList<>();
    private List<Integer> edenMax = new LinkedList<>();
    private List<Integer> edenUsed = new LinkedList<>();

    private List<Integer> survivorInitial = new LinkedList<>();
    private List<Integer> survivorCommitted = new LinkedList<>();
    private List<Integer> survivorMax = new LinkedList<>();
    private List<Integer> survivorUsed = new LinkedList<>();

    private List<Integer> tenuredInitial = new LinkedList<>();
    private List<Integer> tenuredCommitted = new LinkedList<>();
    private List<Integer> tenuredMax = new LinkedList<>();
    private List<Integer> tenuredUsed = new LinkedList<>();

    private List<Integer> pmInitial = new LinkedList<>();
    private List<Integer> pmCommitted = new LinkedList<>();
    private List<Integer> pmMax = new LinkedList<>();
    private List<Integer> pmUsed = new LinkedList<>();


    private List<Integer> queueSizes = new LinkedList<>();
    private List<Integer> consumers = new LinkedList<>();
    private List<Integer> ack = new LinkedList<>();
    private List<Integer> exp = new LinkedList<>();

    public List<Date> getTimestamps() {
        return timestamps;
    }

    public List<Integer> getQueueSizes() {
        return queueSizes;
    }

    public List<Double> getLoadValues() {
        return loadValues;
    }

    public List<Integer> getOpenFds() {
        return openFds;
    }

    public List<Integer> getFreeFds() {
        return freeFds;
    }

    public List<Integer> getFreeMems() {
        return freeMems;
    }

    public List<Integer> getSwapFrees() {
        return swapFrees;
    }

    public List<Integer> getSwapCommitteds() {
        return swapCommitteds;
    }

    public List<Integer> getEdenInitial() {
        return edenInitial;
    }

    public List<Integer> getEdenCommitted() {
        return edenCommitted;
    }

    public List<Integer> getEdenMax() {
        return edenMax;
    }

    public List<Integer> getEdenUsed() {
        return edenUsed;
    }

    public List<Integer> getSurvivorInitial() {
        return survivorInitial;
    }

    public List<Integer> getSurvivorCommitted() {
        return survivorCommitted;
    }

    public List<Integer> getSurvivorMax() {
        return survivorMax;
    }

    public List<Integer> getSurvivorUsed() {
        return survivorUsed;
    }

    public List<Integer> getTenuredInitial() {
        return tenuredInitial;
    }

    public List<Integer> getTenuredCommitted() {
        return tenuredCommitted;
    }

    public List<Integer> getTenuredMax() {
        return tenuredMax;
    }

    public List<Integer> getTenuredUsed() {
        return tenuredUsed;
    }

    public List<Integer> getPmInitial() {
        return pmInitial;
    }

    public List<Integer> getPmCommitted() {
        return pmCommitted;
    }

    public List<Integer> getPmMax() {
        return pmMax;
    }

    public List<Integer> getPmUsed() {
        return pmUsed;
    }

    public List<Integer> getConsumers() {
        return consumers;
    }

    public List<Integer> getAck() {
        return ack;
    }

    public List<Integer> getExp() {
        return exp;
    }
}
