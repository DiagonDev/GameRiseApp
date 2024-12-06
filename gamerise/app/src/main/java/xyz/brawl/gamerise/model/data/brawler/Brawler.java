package xyz.brawl.gamerise.model.data.brawler;

import java.util.List;

public class Brawler {
    public int brawlerPin;
    public int id;
    public String name;
    public List<StarPower> starPowers;
    public List<Gadget> gadgets;

    // Costruttore principale
    public Brawler(int brawlerPin, int id, String name, List<StarPower> starPowers, List<Gadget> gadgets) {
        this.brawlerPin = brawlerPin;
        this.id = id;
        this.name = name;
        this.starPowers = starPowers;
        this.gadgets = gadgets;
    }

    // Builder Pattern
    public static class BrawlerBuilder {
        private int brawlerPin;
        private int id;
        private String name;
        private List<StarPower> starPowers;
        private List<Gadget> gadgets;

        public BrawlerBuilder brawlerPin(int brawlerPin) {
            this.brawlerPin = brawlerPin;
            return this;
        }

        public BrawlerBuilder id(int id) {
            this.id = id;
            return this;
        }

        public BrawlerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BrawlerBuilder starPowers(List<StarPower> starPowers) {
            this.starPowers = starPowers;
            return this;
        }

        public BrawlerBuilder gadgets(List<Gadget> gadgets) {
            this.gadgets = gadgets;
            return this;
        }

        public Brawler build() {
            return new Brawler(brawlerPin, id, name, starPowers, gadgets);
        }
    }
}
