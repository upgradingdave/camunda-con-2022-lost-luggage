package org.camunda.community.lostluggage.connector;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.camunda.cherry.definition.AbstractWorker;
import org.camunda.cherry.definition.RunnerParameter;

import java.util.Arrays;
import java.util.Collections;

public class LocalizeAirtag extends AbstractWorker {

    public LocalizeAirtag() {
        super("localize",
                Arrays.asList(
                        RunnerParameter.getInstance("airtagId", "AirtagId", String.class, RunnerParameter.Level.REQUIRED, "Airtag ID")
                ),
                Arrays.asList(
                        RunnerParameter.getInstance("lnt", "Longitude", String.class, RunnerParameter.Level.REQUIRED, "Longitude of the airtag"),
                        RunnerParameter.getInstance("lat", "Latitude", String.class, RunnerParameter.Level.REQUIRED, "Latitude of the airtag")),
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
