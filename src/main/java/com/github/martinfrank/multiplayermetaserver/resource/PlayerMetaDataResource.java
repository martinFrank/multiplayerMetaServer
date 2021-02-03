package com.github.martinfrank.multiplayermetaserver.resource;

import com.codahale.metrics.annotation.Timed;
import com.github.martinfrank.multiplayermetaserver.model.Player;
import com.github.martinfrank.multiplayermetaserver.model.Players;
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

    private final Players players;

    public PlayerMetaDataResource(Players players) {
        this.players = players;
    }

    @POST
    @Timed
    @Path("/player")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlayerMetaData getPlayerMetaData(PlayerCredentials playerCredentials) {
        LOGGER.debug("Logon: {}", playerCredentials);
        Player player = players.get(playerCredentials.getName(), playerCredentials.getPass());
        return new PlayerMetaData(player.uuid.toString(), player.name, player.areaId);
    }


}