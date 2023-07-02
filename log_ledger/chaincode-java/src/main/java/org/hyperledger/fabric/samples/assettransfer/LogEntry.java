/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.hyperledger.fabric.samples.assettransfer;

import java.util.Objects;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import com.owlike.genson.annotation.JsonProperty;

@DataType()
public final class LogEntry {
    @Property()
    private String logId;
    @Property()
    private String logData;


    public LogEntry(@JsonProperty("logId") final String logId,
                    @JsonProperty("logData") final String logData)
   {
        this.logId = logId;
        this.logData = logData;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }

    public String toJSONString() {
        return "{\"logId\":\"" + logId + "\",\"logData\":\"" + logData + "\"}";
    }
}
