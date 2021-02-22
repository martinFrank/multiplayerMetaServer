package com.github.martinfrank.multiplayermetaserver.model;

import com.github.martinfrank.multiplayerprotocol.area.Position;
import com.github.martinfrank.multiplayerprotocol.meta.PlayerData;

import java.util.ArrayList;
import java.util.List;

//Notice: this class is for internal database storage and not meant to be exposed to any clients - use the Protocol for data exchanges!
public class Players {

    private List<Player> players = new ArrayList<>();

    public Players(){
        Player player1 = new Player();
        player1.uuid = "mf5af5c6-537a-4a0a-8458-0e1efb5c9833";
        player1.name = "Bosh";
        player1.email = "some.more.mail@some.domain";
        player1.pass = "swordFish";
        player1.online = false;
        player1.areaId = "templeTest";
        player1.position = new Position(10, 10);
        players.add(player1);

        Player player2 = new Player();
        player2.uuid = "3f5af5c6-537a-4a0a-8458-0e1efb5c9833";
        player2.name = "Mosh";
        player2.email = "some.mail@some.domain";
        player2.pass = "swordFish";
        player2.online = false;
        player2.areaId = "templeTest";
        player2.position = new Position(20, 10);
        players.add(player2);
    }

    public Player getByUserPass(String name, String pass) {
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

    public Player getById(String playerId) {
        return players.stream().filter(p -> p.uuid.equals(playerId)).findAny().orElse(null);
    }
}
