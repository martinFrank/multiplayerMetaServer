package com.github.martinfrank.multiplayermetaserver.model;

import com.github.martinfrank.multiplayerprotocol.area.Position;
import com.github.martinfrank.multiplayerprotocol.meta.PlayerData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Notice: this class is for internal database storage and not meant to be exposed to any clients - use the Protocol for data exchanges!
public class Players {

    private List<Player> players = new ArrayList<>();

    public Players(){
        Player player = new Player();
        player.uuid = "3f5af5c6-537a-4a0a-8458-0e1efb5c9833";
        player.name = "Mosh";
        player.email = "some.mail@some.domain";
        player.pass = "swordFish";
        player.online = false;
        player.areaId = "templeTest";
        player.position = new Position(20,20);
        players.add(player);
    }

    public Player get(String name, String pass) {
        return players.stream().filter(p -> p.matchCredentials(name,pass)).findAny().orElse(null);
    }

    public PlayerData getPlayerData(String playerId) {
        Player player = players.stream().filter(p -> p.matchId(playerId)).findAny().orElse(null);
        if(player != null) {
            PlayerData playerData = new PlayerData();
            playerData.position = player.position;
            playerData.id = playerId;
            return playerData;
        }
        return null;
    }
}
