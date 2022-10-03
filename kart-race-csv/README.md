## Kart race laps CSV generator

This is the way to run the race simulator.

To build it:

```
$ mvn clean install
$ cd kart-race-laps-csv
$ mvn clean compile assembly:single
```

The following is an example on how to run from the command line:

```
java -jar target/kart-race-laps-csv-1.0.0-SNAPSHOT-jar-with-dependencies.jar -n racer1,racer2,racer3,racer4,racer5 -k 1,2,3,4,5 -min 1000 -max 1500 -laps 10 -t 1000 -of /tmp/results.csv
```