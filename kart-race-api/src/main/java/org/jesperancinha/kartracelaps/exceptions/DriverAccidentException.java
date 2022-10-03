package org.jesperancinha.kartracelaps.exceptions;

import java.text.MessageFormat;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class DriverAccidentException extends Exception{

    private Integer driverId;

    public DriverAccidentException(Integer driverId){
        this.driverId = driverId;
    }

    @Override
    public String getMessage() {
        return MessageFormat.format("Driver {0} has had an accident!", driverId);
    }
}
