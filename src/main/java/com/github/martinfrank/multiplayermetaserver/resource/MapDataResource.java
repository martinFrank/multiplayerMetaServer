package com.github.martinfrank.multiplayermetaserver.resource;

import com.codahale.metrics.annotation.Timed;
import com.github.martinfrank.multiplayermetaserver.MultiplayerMetaServerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Path("/mapdata")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MapDataResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapDataResource.class);

    private final MultiplayerMetaServerConfiguration configuration;

    public MapDataResource(MultiplayerMetaServerConfiguration configuration) {
        this.configuration = configuration;
    }


    @GET
    @Path("/download")
    @Timed
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileWithGet(@QueryParam("mapid") String mapId) {
        LOGGER.debug("mapid: {}", mapId);
        Arrays.stream(configuration.mapConfigurations).forEach(c -> LOGGER.debug("mapId: {}",c.mapId));
        //if "TempleTest".equals(mapId){}
        Optional<MultiplayerMetaServerConfiguration.MapConfiguration> config =
                Arrays.stream(configuration.mapConfigurations).
                        filter(c -> c.mapId.equalsIgnoreCase(mapId)).findAny();
        if (config.isPresent()) {
            String filename = config.get().filename;
            File fileDownload = new File(config.get().filename);
            Response.ResponseBuilder response = Response.ok(fileDownload);
            response.header("Content-Disposition", "attachment;filename=" + filename);
            return response.build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public List<String> listFiles() {

        List<String> listFiles = new ArrayList<>();
        File fileFolder = new File("test");
        File[] list = fileFolder.listFiles();

        for (File f : list) {
            if (!f.isDirectory()) {
                listFiles.add(f.getName());
            }
        }
        return listFiles;
    }

}