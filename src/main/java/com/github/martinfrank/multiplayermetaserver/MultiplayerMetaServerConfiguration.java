package com.github.martinfrank.multiplayermetaserver;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class MultiplayerMetaServerConfiguration extends Configuration {

//    @JsonProperty
//    public PwmSetting pwmSetting;
//
    @JsonProperty
    public MapConfiguration[] mapConfigurations;

//    public static class MapConfigurations {
//
//        @JsonProperty
//        public MapConfiguration[] mapConfiguration;
//    }

    public static class MapConfiguration{
        @JsonProperty
        public String mapId;
        @JsonProperty
        public String filename;
        @JsonProperty
        public String version;
    }
//
//    @JsonProperty
//    public DeviceConfiguration[] devices;
//
//    public static class DeviceConfiguration {
//
//        @JsonProperty
//        public String name;
//        @JsonProperty
//        public String unit;
//        @JsonProperty
//        public String notes;
//
//        @JsonProperty
//        public SoftServoConfiguration softServoConfiguration;
//
//        @JsonProperty
//        public HardwarePwmConfiguration hardwarePwmConfiguration;
//
//        @JsonProperty
//        public DigitalOutConfiguration digitalOutConfiguration;
//
//        @JsonProperty
//        public SoftMotorConfiguration softMotorConfiguration;
//
//        @JsonProperty
//        public SoftServoConfiguration hardwareServoConfiguration;
//
//    }
//

//
//    public static class HardwareServoConfiguration {
//        @JsonProperty
//        public String pin;
//        @JsonProperty
//        public int min;
//        @JsonProperty
//        public int max;
//        @JsonProperty
//        public boolean invert;
//    }
//
//    public static class SoftMotorConfiguration {
//        @JsonProperty
//        public String drivePin;
//        @JsonProperty
//        public String directionalPin;
//        @JsonProperty
//        public boolean invert;
//    }
//
//    public static class HardwarePwmConfiguration {
//        @JsonProperty
//        public String pin;
//        @JsonProperty
//        public int min;
//        @JsonProperty
//        public int max;
//        @JsonProperty
//        public boolean invert;
//    }
//
//    public static class DigitalOutConfiguration {
//        @JsonProperty
//        public String pin;
//        @JsonProperty
//        public boolean invert;
//    }
//


}
