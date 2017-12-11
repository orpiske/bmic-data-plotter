package net.orpiske.bmic.plot;

import java.time.Instant;
import java.util.Date;

public class BmicRecord implements Comparable<BmicRecord> {
    private Date timestamp;
    private Double loadValue;
    private Integer openFds;
    private Integer freeFds;
    private Integer freeMem;
    private Integer swapFree;
    private Integer swapCommitted;
    private Integer edenInitial;
    private Integer edenCommitted;
    private Integer edenMax;
    private Integer edenUsed;

    private Integer survivorInitial;
    private Integer survivorCommitted;
    private Integer survivorMax;
    private Integer survivorUsed;

    private Integer tenuredInitial;
    private Integer tenuredCommitted;
    private Integer tenuredMax;
    private Integer tenuredUsed;

    private Integer pmInitial;
    private Integer pmCommitted;
    private Long pmMax;
    private Integer pmUsed;

    private Integer queueSize;
    private Integer consumer;
    private Integer ack;
    private Integer exp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getLoadValue() {
        return loadValue;
    }

    public void setLoadValue(Double loadValue) {
        this.loadValue = loadValue;
    }

    public Integer getOpenFds() {
        return openFds;
    }

    public void setOpenFds(Integer openFds) {
        this.openFds = openFds;
    }

    public Integer getFreeFds() {
        return freeFds;
    }

    public void setFreeFds(Integer freeFds) {
        this.freeFds = freeFds;
    }

    public Integer getFreeMem() {
        return freeMem;
    }

    public void setFreeMem(Integer freeMem) {
        this.freeMem = freeMem;
    }

    public Integer getSwapFree() {
        return swapFree;
    }

    public void setSwapFree(Integer swapFree) {
        this.swapFree = swapFree;
    }

    public Integer getSwapCommitted() {
        return swapCommitted;
    }

    public void setSwapCommitted(Integer swapCommitted) {
        this.swapCommitted = swapCommitted;
    }

    public Integer getEdenInitial() {
        return edenInitial;
    }

    public void setEdenInitial(Integer edenInitial) {
        this.edenInitial = edenInitial;
    }

    public Integer getEdenCommitted() {
        return edenCommitted;
    }

    public void setEdenCommitted(Integer edenCommitted) {
        this.edenCommitted = edenCommitted;
    }

    public Integer getEdenMax() {
        return edenMax;
    }

    public void setEdenMax(Integer edenMax) {
        this.edenMax = edenMax;
    }

    public Integer getEdenUsed() {
        return edenUsed;
    }

    public void setEdenUsed(Integer edenUsed) {
        this.edenUsed = edenUsed;
    }

    public Integer getSurvivorInitial() {
        return survivorInitial;
    }

    public void setSurvivorInitial(Integer survivorInitial) {
        this.survivorInitial = survivorInitial;
    }

    public Integer getSurvivorCommitted() {
        return survivorCommitted;
    }

    public void setSurvivorCommitted(Integer survivorCommitted) {
        this.survivorCommitted = survivorCommitted;
    }

    public Integer getSurvivorMax() {
        return survivorMax;
    }

    public void setSurvivorMax(Integer survivorMax) {
        this.survivorMax = survivorMax;
    }

    public Integer getSurvivorUsed() {
        return survivorUsed;
    }

    public void setSurvivorUsed(Integer survivorUsed) {
        this.survivorUsed = survivorUsed;
    }

    public Integer getTenuredInitial() {
        return tenuredInitial;
    }

    public void setTenuredInitial(Integer tenuredInitial) {
        this.tenuredInitial = tenuredInitial;
    }

    public Integer getTenuredCommitted() {
        return tenuredCommitted;
    }

    public void setTenuredCommitted(Integer tenuredCommitted) {
        this.tenuredCommitted = tenuredCommitted;
    }

    public Integer getTenuredMax() {
        return tenuredMax;
    }

    public void setTenuredMax(Integer tenuredMax) {
        this.tenuredMax = tenuredMax;
    }

    public Integer getTenuredUsed() {
        return tenuredUsed;
    }

    public void setTenuredUsed(Integer tenuredUsed) {
        this.tenuredUsed = tenuredUsed;
    }

    public Integer getPmInitial() {
        return pmInitial;
    }

    public void setPmInitial(Integer pmInitial) {
        this.pmInitial = pmInitial;
    }

    public Integer getPmCommitted() {
        return pmCommitted;
    }

    public void setPmCommitted(Integer pmCommitted) {
        this.pmCommitted = pmCommitted;
    }

    public Long getPmMax() {
        return pmMax;
    }

    public void setPmMax(Long pmMax) {
        this.pmMax = pmMax;
    }

    public Integer getPmUsed() {
        return pmUsed;
    }

    public void setPmUsed(Integer pmUsed) {
        this.pmUsed = pmUsed;
    }

    public Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
    }

    public Integer getConsumer() {
        return consumer;
    }

    public void setConsumer(Integer consumer) {
        this.consumer = consumer;
    }

    public Integer getAck() {
        return ack;
    }

    public void setAck(Integer ack) {
        this.ack = ack;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    @Override
    public int compareTo(BmicRecord bmicRecord) {
        Instant current = this.getTimestamp().toInstant();
        Instant other = bmicRecord.getTimestamp().toInstant();

        if (current.isBefore(other)) {
            return -1;
        }
        else {
            if (current.isAfter(other)) {
                return 1;
            }
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BmicRecord that = (BmicRecord) o;

        return timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return timestamp.hashCode();
    }

    @Override
    public String toString() {
        return "BmicRecord{" +
                "timestamp=" + timestamp +
                ", loadValue=" + loadValue +
                ", openFds=" + openFds +
                ", freeFds=" + freeFds +
                ", freeMem=" + freeMem +
                ", swapFree=" + swapFree +
                ", swapCommitted=" + swapCommitted +
                ", edenInitial=" + edenInitial +
                ", edenCommitted=" + edenCommitted +
                ", edenMax=" + edenMax +
                ", edenUsed=" + edenUsed +
                ", survivorInitial=" + survivorInitial +
                ", survivorCommitted=" + survivorCommitted +
                ", survivorMax=" + survivorMax +
                ", survivorUsed=" + survivorUsed +
                ", tenuredInitial=" + tenuredInitial +
                ", tenuredCommitted=" + tenuredCommitted +
                ", tenuredMax=" + tenuredMax +
                ", tenuredUsed=" + tenuredUsed +
                ", pmInitial=" + pmInitial +
                ", pmCommitted=" + pmCommitted +
                ", pmMax=" + pmMax +
                ", pmUsed=" + pmUsed +
                ", queueSize=" + queueSize +
                ", consumer=" + consumer +
                ", ack=" + ack +
                ", exp=" + exp +
                '}';
    }
}

