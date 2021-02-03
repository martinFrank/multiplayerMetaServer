package com.github.martinfrank.multiplayermetaserver.model;

import com.github.martinfrank.multiplayerprotocol.meta.AreaServerCredentials;

public class ServerCredential {

    public String user;
    public String pass;

    public boolean areValid(AreaServerCredentials areaServerCredentials) {
        return areaServerCredentials.user.equals(user) && areaServerCredentials.pass.equals(pass);
    }
}
