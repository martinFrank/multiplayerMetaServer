package com.github.martinfrank.multiplayermetaserver.model;

import com.github.martinfrank.multiplayerprotocol.area.Position;

import javax.tools.DocumentationTool;
import java.util.UUID;

//Notice: this class is for internal database storage and not meant to be exposed to any clients - use the Protocol for data exchanges!
public class Player {

    public UUID uuid;
    public String email;
    public String name;
    public String passwordHash;
    public boolean online;
    public String areaId;

    public Position position = new Position(20,20);

}
