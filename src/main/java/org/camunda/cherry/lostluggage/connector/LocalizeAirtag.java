package org.camunda.cherry.lostluggage.connector;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.camunda.cherry.definition.AbstractWorker;
import org.camunda.cherry.definition.RunnerParameter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class LocalizeAirtag extends AbstractWorker {
    private static final Map<String, List<Double>> LOCATIONS = Map.of(
        "Statue of Liberty", Arrays.asList(40.6892494, -74.04450039999999),
        "Newark Airport", Arrays.asList(40.6895314, -74.1744624),
        "JFK Airport", Arrays.asList(40.6413113, -73.7781383),
        "Muchen Airport", Arrays.asList(48.3509684, 11.7764347),
        "Berlin Airport", Arrays.asList(52.36478700000001, 13.511414),
        "Camunda Office", Arrays.asList(52.494709, 13.3959074)
    );

    public LocalizeAirtag() {
        super("localize",
                List.of(RunnerParameter.getInstance("airtagId", "AirtagId", String.class, RunnerParameter.Level.REQUIRED, "Airtag ID")),
                List.of(
                        RunnerParameter.getInstance("lnt", "Longitude", String.class, RunnerParameter.Level.REQUIRED, "Longitude of the airtag"),
                        RunnerParameter.getInstance("lat", "Latitude", String.class, RunnerParameter.Level.REQUIRED, "Latitude of the airtag")
                ),
                Collections.emptyList()
        );
    }

    @Override
    public String getName() {
        return "AirTagLocalization";
    }

    @Override
    public String getLabel() {
        return "Air tag localization";
    }

    @Override
    public String getDescription() {
        return "From an Airtag ID, return the localization";
    }

    @Override
    public void execute(final JobClient jobClient, final ActivatedJob activatedJob, ContextExecution contextExecution) {
    }
}
