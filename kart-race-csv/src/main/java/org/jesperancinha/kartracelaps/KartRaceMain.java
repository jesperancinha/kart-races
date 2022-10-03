package org.jesperancinha.kartracelaps;

import org.jesperancinha.kartracelaps.options.KartOptions;
import picocli.CommandLine;

/**
 *
 */
public class KartRaceMain {

    public static final String PASSINGTIME = "passingtime";
    public static final String KART = "kart";

    public static void main(String[] args) {
        new CommandLine(new KartOptions()).execute(args);
    }
}
