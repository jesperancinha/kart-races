package org.jesperancinha.kartracelaps.exception;

import java.text.MessageFormat;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class NameKartMismatchException extends Exception {
    private String names;
    private String karIds;

    public NameKartMismatchException(String names, String karIds) {
        this.names = names;
        this.karIds = karIds;
    }

    @Override
    public String getMessage() {
        return MessageFormat.format("Missmatch between names: {0} and kartIds {1}", names, karIds);
    }
}
