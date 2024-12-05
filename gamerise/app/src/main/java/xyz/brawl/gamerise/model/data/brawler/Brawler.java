package xyz.brawl.gamerise.model.data.brawler;

public class Brawler {
    public int brawlerPin;
    public int id;
    public String name;
    public StarPower[] starPowers;
    public Gadget[] gadgets;

    // Costruttore principale
    public Brawler(int brawlerPin, int id, String name, StarPower[] starPowers, Gadget[] gadgets) {
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
        private StarPower[] starPowers;
        private Gadget[] gadgets;

        // Metodo per impostare brawlerPin
        public BrawlerBuilder brawlerPin(int brawlerPin) {
            this.brawlerPin = brawlerPin;
            return this;
        }

        // Metodo per impostare id
        public BrawlerBuilder id(int id) {
            this.id = id;
            return this;
        }

        // Metodo per impostare name
        public BrawlerBuilder name(String name) {
            this.name = name;
            return this;
        }

        // Metodo per impostare starPowers
        public BrawlerBuilder starPowers(StarPower[] starPowers) {
            this.starPowers = starPowers;
            return this;
        }

        // Metodo per impostare gadgets
        public BrawlerBuilder gadgets(Gadget[] gadgets) {
            this.gadgets = gadgets;
            return this;
        }

        // Metodo per costruire l'oggetto Brawler
        public Brawler build() {
            return new Brawler(brawlerPin, id, name, starPowers, gadgets);
        }
    }
}
