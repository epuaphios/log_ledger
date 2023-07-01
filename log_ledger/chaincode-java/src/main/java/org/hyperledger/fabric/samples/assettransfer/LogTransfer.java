/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.hyperledger.fabric.samples.assettransfer;

import java.util.ArrayList;
import java.util.List;


import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;


@Default
public final class LogTransfer implements ContractInterface {
    @Transaction
    public void storeLog(Context ctx, String logId, String logData) {
        // Log girişini doğrula
        boolean isValid = validateLogSource(logId);
        if (!isValid) {
            throw new RuntimeException("XXXX not found");
        }

        // Log verisini kaydetme işlemi
        LogEntry logEntry = new LogEntry(logId, logData);
        ctx.getStub().putState(logId, logEntry.toJSONString().getBytes());
    }

    private boolean validateLogSource(String logId) {
        return true;
    }

    @Transaction
    public String readLog(Context ctx, String logId) {
        byte[] data = ctx.getStub().getState(logId);
        if (data != null && data.length > 0) {
            return new String(data);
        }
        return null;
    }
}
