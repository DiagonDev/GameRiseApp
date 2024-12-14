package xyz.brawl.gamerise.model.data.brawler;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// Model class for BattleLogEntry
public class BrawlerEntry {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("gadgets")
    private List<Gadget> gadgets;

    @SerializedName("starPowers")
    private List<StarPower> starPowers;

    private int brawlerPin;

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Gadget> getGadgets() {
        return gadgets;
    }

    public void setGadgets(List<Gadget> gadgets) {
        this.gadgets = gadgets;
    }

    public List<StarPower> getStarPowers() {
        return starPowers;
    }

    public void setStarPowers(List<StarPower> starPowers) {
        this.starPowers = starPowers;
    }

    public int getBrawlerPin() {
        return brawlerPin;
    }

    public void setBrawlerPin(int brawlerPin) {
        this.brawlerPin = brawlerPin;
    }

    // Gadget class
    public static class Gadget {
        @SerializedName("id")
        private long id;

        @SerializedName("name")
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // StarPower class
    public static class StarPower {
        @SerializedName("id")
        private long id;

        @SerializedName("name")
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
