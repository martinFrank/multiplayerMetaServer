package com.github.martinfrank.multiplayermetaserver.resource;

import com.codahale.metrics.annotation.Timed;
import com.github.martinfrank.multiplayermetaserver.model.Player;
import com.github.martinfrank.multiplayerprotocol.meta.PlayerCredentials;
import com.github.martinfrank.multiplayerprotocol.meta.PlayerMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;


@Path("/metadata")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerMetaDataResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerMetaDataResource.class);

    private final Player player;

    public PlayerMetaDataResource() {
        player = new Player();
        player.uuid = UUID.fromString("3f5af5c6-537a-4a0a-8458-0e1efb5c9833");
        player.name = "Mosh";
        player.email = "some.mail@some.domain";
        player.passwordHash = "-882706713";
        player.online = false;
        player.areaId = "templeTest";
    }

    @POST
    @Timed
    @Path("/player")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlayerMetaData getPlayerMetaData(PlayerCredentials playerCredentials) {
        LOGGER.debug("Logon: {}", playerCredentials);
        return new PlayerMetaData(player.uuid.toString(), player.name, player.areaId);
    }

    @POST
    @Timed
    @Path("/online")
    public Response online() {
        player.online = true;
        Response.ResponseBuilder responseBuilder =
                Response.noContent().status(Response.Status.ACCEPTED);
        return responseBuilder.build();
    }

    @POST
    @Timed
    @Path("/offline")
    public Response offline(@QueryParam("userId") String userId) {
        LOGGER.debug("user ID: {}", userId);
        player.online = false;
        Response.ResponseBuilder responseBuilder =
                Response.noContent().status(Response.Status.ACCEPTED);

        return responseBuilder.build();
    }

}