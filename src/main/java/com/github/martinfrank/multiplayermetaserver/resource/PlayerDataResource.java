package com.github.martinfrank.multiplayermetaserver.resource;

import com.codahale.metrics.annotation.Timed;
import com.github.martinfrank.multiplayermetaserver.model.Players;
import com.github.martinfrank.multiplayermetaserver.model.ServerCredentials;
import com.github.martinfrank.multiplayerprotocol.meta.AreaServerCredentials;
import com.github.martinfrank.multiplayerprotocol.meta.PlayerCredentials;
import com.github.martinfrank.multiplayerprotocol.meta.PlayerData;
import com.github.martinfrank.multiplayerprotocol.meta.PlayerMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playerdata")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerDataResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDataResource.class);

    private final Players players;
    private final ServerCredentials serverCredentials;

    public PlayerDataResource(Players players, ServerCredentials serverCredentials) {
        this.players = players;
        this.serverCredentials = serverCredentials;
    }

    @POST
    @Timed
    @Path("/online")
    public Response online(AreaServerCredentials areaServerCredentials) {
//        player.online = true;
        Response.ResponseBuilder responseBuilder =
                Response.noContent().status(Response.Status.ACCEPTED);
        return responseBuilder.build();
    }

    @GET
    @Timed
    @Path("/onlineStatus")
    public Response onlineStatus(AreaServerCredentials areaServerCredentials) {
//        player.online = true;
        Response.ResponseBuilder responseBuilder =
                Response.noContent().status(Response.Status.ACCEPTED);
        return responseBuilder.build();
    }

    @POST
    @Timed
    @Path("/player")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlayerData playerData(AreaServerCredentials areaServerCredentials) {
        if(serverCredentials.areValid(areaServerCredentials)){
            return players.getPlayerData(areaServerCredentials.playerId);
        }
        throw new NotFoundException();
    }

    @POST
    @Timed
    @Path("/offline")
    public Response offline(AreaServerCredentials AreaServerCredentials) {
        LOGGER.debug("user AreaServerCredentials: {}", AreaServerCredentials);
//        player.online = false;
        Response.ResponseBuilder responseBuilder =
                Response.noContent().status(Response.Status.ACCEPTED);

        return responseBuilder.build();
    }

}