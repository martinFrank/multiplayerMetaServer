package com.github.martinfrank.multiplayermetaserver.resource;

import com.codahale.metrics.annotation.Timed;
import com.github.martinfrank.multiplayermetaserver.model.Player;
import com.github.martinfrank.multiplayermetaserver.model.Players;
import com.github.martinfrank.multiplayermetaserver.model.ServerCredentials;
import com.github.martinfrank.multiplayerprotocol.meta.AreaServerCredentials;
import com.github.martinfrank.multiplayerprotocol.meta.PlayerCredentials;
import com.github.martinfrank.multiplayerprotocol.meta.PlayerMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/metadata")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerMetaDataResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerMetaDataResource.class);

    private final Players players;
    private final ServerCredentials serverCredentials;

    public PlayerMetaDataResource(Players players, ServerCredentials serverCredentials) {
        this.players = players;
        this.serverCredentials = serverCredentials;
    }

    @POST
    @Timed
    @Path("/player")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlayerMetaData getPlayerMetaData(PlayerCredentials playerCredentials) {
        LOGGER.debug("getPlayerMetaData: {}", playerCredentials);
        Player player = players.getByUserPass(playerCredentials.getName(), playerCredentials.getPass());
        return new PlayerMetaData(player.uuid, player.name, player.areaId, player.online);
    }

    @POST
    @Timed
    @Path("/areaplayer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlayerMetaData getAreaPlayerMetaData(AreaServerCredentials areaServerCredentials) {
        LOGGER.debug("getAreaPlayerMetaData: {}", areaServerCredentials);
        if(serverCredentials.areValid(areaServerCredentials)){
            Player player = players.getById(areaServerCredentials.playerId);
            return new PlayerMetaData(player.uuid, player.name, player.areaId, player.online);
        }
        throw new NotFoundException();
    }

}