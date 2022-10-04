package org.camunda.cherry.lostluggage.connector;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.camunda.cherry.definition.AbstractWorker;
import org.camunda.cherry.definition.BpmnError;
import org.camunda.cherry.definition.RunnerParameter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class GenerateMap extends AbstractWorker {
    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lon";
    private static final String IMAGE_URL = "imageURL";

    public GenerateMap() {

        super("sendmap",
            List.of(
                RunnerParameter.getInstance(LATITUDE, "Latitude", String.class, RunnerParameter.Level.REQUIRED, "Latitude"),
                RunnerParameter.getInstance(LONGITUDE, "Longitude", String.class, RunnerParameter.Level.REQUIRED, "Longitude")
                ),
            List.of(
                RunnerParameter.getInstance(IMAGE_URL, "Image Url", Object.class, RunnerParameter.Level.REQUIRED, "Image Url")
            ),
            Collections.emptyList()
        );
    }

    @Override
    public String getName() {
        return "GenerateMap";
    }

    @Override
    public String getLabel() {
        return "Generate Static Google Map";
    }

    @Override
    public String getDescription() {
        return "Generate Image using Static Google Maps API";
    }

    @Override
    public void execute(final JobClient jobClient, final ActivatedJob activatedJob, ContextExecution contextExecution) {
        String lat = getInputStringValue(LATITUDE, null, activatedJob);
        String lon = getInputStringValue(LONGITUDE, null, activatedJob);

        // Build google static url and call url http client to call the url to get the image.
        String size = "500x400";
        String GOOGLE_API_KEY = "AIzaSyBVkpHopbvXlup4-T0HNZcfyRGzKRfMxyY";
        String url = "https://maps.googleapis.com/maps/api/staticmap?zoom=10&center="+lat+","+lon+"&size="+size+"&key="+ GOOGLE_API_KEY;

        setValue(IMAGE_URL, url, contextExecution);
    }
}
