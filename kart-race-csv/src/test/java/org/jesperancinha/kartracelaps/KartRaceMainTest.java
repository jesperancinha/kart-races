package org.jesperancinha.kartracelaps;

import org.junit.Test;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class KartRaceMainTest {
    @Test
    public void main() {
        KartRaceMain.main(new String[]{
                "--names-comma-separated",
                "racer1,racer2,racer3,racer4,racer5",
                "--kart-ids-comma-separated",
                "1,2,3,4,5",
                "--min-simulation-time",
                "100",
                "--max-simulation-time",
                "150",
                "--number-of-laps",
                "10",
                "--track-length",
                "1000",
                "--output-file",
                "/tmp/results.csv"//
        });
    }

}