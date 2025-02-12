package xyz.brawl.gamerise.model.battle;


public class BattleLogEntry {
    private BattleEntry battle;
    private String battleTime;
    private EventEntry event;

    public BattleEntry getBattle() {
        return battle;
    }

    public String getBattleTime() {
        return battleTime;
    }

    public void setBattleTime(String battleTime) {
        this.battleTime = battleTime;
    }

    public void setBattle(BattleEntry battle) {
        this.battle = battle;
    }

    public EventEntry getEvent() {
        return event;
    }

    public void setEvent(EventEntry event) {
        this.event = event;
    }
}
