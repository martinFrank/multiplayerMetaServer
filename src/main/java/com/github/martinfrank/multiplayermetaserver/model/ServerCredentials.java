package com.github.martinfrank.multiplayermetaserver.model;

import com.github.martinfrank.multiplayermetaserver.MultiplayerMetaServerConfiguration;
import com.github.martinfrank.multiplayerprotocol.meta.AreaServerCredentials;

import java.util.ArrayList;
import java.util.List;

public class ServerCredentials {

    private List<ServerCredential> serverCredentials = new ArrayList<>();

    //FXME credentails from configuration
    public ServerCredentials(MultiplayerMetaServerConfiguration configuration){
        ServerCredential serverCredential = new ServerCredential();
        serverCredential.user = "templeTest1";
        serverCredential.pass = "swordFish";

        serverCredentials.add(serverCredential);
    }

    public boolean areValid(AreaServerCredentials areaServerCredentials) {
        return serverCredentials.stream().anyMatch(c -> c.areValid(areaServerCredentials));
    }
}
