package com.knowmad.airportmanagement.repository.migration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

@ChangeLog(order = "001")
public class DbChangeLog001 {

    @ChangeSet(order = "001", id = "changeWithoutArgs", author = "Miguel Maquieira")
    public void changeWithoutArgs() {
        // method without arguments can do some non-db changes
    }
}
