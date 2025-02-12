package xyz.brawl.gamerise.model.player;

public class ClubEntry {
    private String name;

    public String getName() {
        if(name == null){
            return "No club";
        }
        return name;
    }
    public void setName(String name) { this.name = name; }
}
