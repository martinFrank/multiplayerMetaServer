package com.github.martinfrank.multiplayermetaserver.model;

import com.github.martinfrank.multiplayerprotocol.area.Position;

//Notice: this class is for internal database storage and not meant to be exposed to any clients - use the Protocol for data exchanges!
public class Player {

    public String uuid;
    public String email;
    public String name;
    public String pass;
    public boolean online;
    public String areaId;

    public Position position = new Position(20,20);

    public boolean matchCredentials(String name, String pass) {
        return this.name.equals(name) && this.pass.equals(pass);
    }

    public boolean matchId(String playerId) {
        return uuid.equals(playerId);
    }
}
