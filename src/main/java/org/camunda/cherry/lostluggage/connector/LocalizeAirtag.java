package org.camunda.cherry.lostluggage.connector;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.camunda.cherry.definition.AbstractWorker;
import org.camunda.cherry.definition.RunnerParameter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class LocalizeAirtag extends AbstractWorker {
    private static final String AIRTAG_ID = "airtagId";
    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lon";

    private static final Map<String, Map<String, Double>> LOCATIONS = Map.of(
            "Statue of Liberty", Map.of(LATITUDE, 40.6892494, LONGITUDE, -74.04450039999999),
            "Newark Airport", Map.of(LATITUDE, 40.6895314, LONGITUDE, -74.1744624),
            "JFK Airport", Map.of(LATITUDE, 40.6413113, LONGITUDE, -73.7781383),
            "Muchen Airport", Map.of(LATITUDE, 48.3509684, LONGITUDE, 11.7764347),
            "Berlin Airport", Map.of(LATITUDE, 52.36478700000001, LONGITUDE, 13.511414),
            "Camunda Office", Map.of(LATITUDE, 52.494709, LONGITUDE, 13.3959074)
    );

    public LocalizeAirtag() {
        super("localize",
                List.of(RunnerParameter.getInstance(AIRTAG_ID, "AirtagId", String.class, RunnerParameter.Level.REQUIRED, "Airtag ID")),
                List.of(
                        RunnerParameter.getInstance(LATITUDE, "Latitude", String.class, RunnerParameter.Level.REQUIRED, "Latitude of the airtag"),
                        RunnerParameter.getInstance(LONGITUDE, "Longitude", String.class, RunnerParameter.Level.REQUIRED, "Longitude of the airtag")
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
        String airtagId = getInputStringValue(AIRTAG_ID, null, activatedJob);

        Map<String, Double> location = LOCATIONS.get(airtagId);
        setValue(LATITUDE, Double.toString(location.get(LATITUDE)), contextExecution);
        setValue("lon", Double.toString(location.get("lon")), contextExecution);
    }
}
