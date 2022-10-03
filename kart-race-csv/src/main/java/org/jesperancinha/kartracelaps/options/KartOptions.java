package org.jesperancinha.kartracelaps.options;

import com.opencsv.CSVWriter;
import org.jesperancinha.kartracelaps.manager.RaceManager;
import org.jesperancinha.kartracelaps.manager.RaceManagerImpl;
import org.jesperancinha.kartracelaps.results.Result;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;

import static org.jesperancinha.kartracelaps.KartRaceMain.KART;
import static org.jesperancinha.kartracelaps.KartRaceMain.PASSINGTIME;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class KartOptions implements Callable<Integer> {

    @Option(names = {"--names-comma-separated", "-n"}, required = true)
    private String names;

    @Option(names = {"--kart-ids-comma-separated", "-k"}, required = false)
    private String kartIds = null;

    @Option(names = {"--min-simulation-time", "-min"}, required = true)
    private Integer minSimulationTime;

    @Option(names = {"--max-simulation-time", "-max"}, required = true)
    private Integer maxSimulationTime;

    @Option(names = {"--number-of-laps", "-laps"}, required = true)
    private Integer nLaps;

    @Option(names = {"--track-length", "-t"}, required = false)
    private Integer trackLength;

    @Option(names = {"--output-file", "-of"}, required = true)
    private String outputFile;

    public String getKartIds() {
        return kartIds;
    }

    public String getNames() {
        return names;
    }

    public Integer getMinSimulationTime() {
        return minSimulationTime;
    }

    public Integer getMaxSimulationTime() {
        return maxSimulationTime;
    }

    public Integer getnLaps() {
        return nLaps;
    }

    public Integer getTrackLength() {
        return trackLength;
    }

    public String getOutputFile() {
        return outputFile;
    }

    @Override
    public Integer call() throws Exception {
        final String[] names = this.names.split(",");
        String[] kartIds = null;
        if (this.kartIds != null) {
            kartIds = this.kartIds.split(",");
        }

        final RaceManager raceManager = new RaceManagerImpl(
                minSimulationTime,
                maxSimulationTime,
                nLaps,
                trackLength
        );

        if (kartIds == null) {
            for (int i = 0; i < names.length; i++) {
                raceManager.addDriver(names[i], i);
            }
        } else {
            for (int i = 0; i < names.length; i++) {
                raceManager.addDriver(names[i], Integer.parseInt(kartIds[i]));
            }
        }
        raceManager.start();

        writeResultsToCsvFile(raceManager, outputFile);
        return 0;
    }

    private static void writeResultsToCsvFile(RaceManager raceManager, String outputFile) throws IOException {
        final CSVWriter writer = new CSVWriter(new FileWriter(outputFile));
        writer.writeNext(new String[]{KART, PASSINGTIME});
        for (Result result : raceManager.getResults()) {
            writer.writeNext(new String[]{result.getKartNumber().toString(), result.getTimeStampString()});
        }
        writer.flush();
        writer.close();
    }
}

