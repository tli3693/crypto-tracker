package com.troy.crypto.tracker.service.cmc;

public class CMCResponse {

    private CMCStatus status;
    private CMCData data;

    public CMCStatus getStatus() {
        return status;
    }

    public void setStatus(CMCStatus status) {
        this.status = status;
    }

    public CMCData getData() {
        return data;
    }

    public void setData(CMCData data) {
        this.data = data;
    }
}
