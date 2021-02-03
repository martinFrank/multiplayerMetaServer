package com.github.martinfrank.multiplayermetaserver;

import com.github.martinfrank.multiplayermetaserver.health.SimpleHealthCheck;
import com.github.martinfrank.multiplayermetaserver.model.Players;
import com.github.martinfrank.multiplayermetaserver.model.ServerCredentials;
import com.github.martinfrank.multiplayermetaserver.resource.MapDataResource;
import com.github.martinfrank.multiplayermetaserver.resource.PlayerDataResource;
import com.github.martinfrank.multiplayermetaserver.resource.PlayerMetaDataResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;

public class MultiplayerMetaServer extends Application<MultiplayerMetaServerConfiguration> {

    public static void main(String[] args) throws Exception {
        new MultiplayerMetaServer().run(args);
    }

    @Override
    public String getName() {
        return "raspi rest server";
    }

    @Override
    public void initialize(Bootstrap<MultiplayerMetaServerConfiguration> bootstrap) {

    }

    @Override
    public void run(MultiplayerMetaServerConfiguration configuration, Environment environment) throws IOException {

        ServerCredentials serverCredentials = new ServerCredentials(configuration);
        Players players = new Players();

        final PlayerMetaDataResource playerMetaDataResource = new PlayerMetaDataResource(players);
        environment.jersey().register(playerMetaDataResource);

        final PlayerDataResource playerDataResource = new PlayerDataResource(players, serverCredentials);
        environment.jersey().register(playerDataResource);

        final MapDataResource mapDataResource = new MapDataResource(configuration);
        environment.jersey().register(mapDataResource);

        final SimpleHealthCheck healthCheck =
                new SimpleHealthCheck("test");
        environment.healthChecks().register("template", healthCheck);


    }
}
