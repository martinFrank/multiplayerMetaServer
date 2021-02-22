package com.github.martinfrank.multiplayermetaserver;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class MultiplayerMetaServerConfiguration extends Configuration {

    @JsonProperty
    public MapConfiguration[] mapConfigurations;

    public static class MapConfiguration{
        @JsonProperty
        public String mapId;
        @JsonProperty
        public String filename;
        @JsonProperty
        public String version;
        @JsonProperty
        public String address;
    }

}
