package xyz.brawl.gamerise.model.data.stat;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import xyz.brawl.gamerise.model.data.player.ClubEntry;
import xyz.brawl.gamerise.model.data.tag.Tag;

/**
 * Classe bivalente: Modello di dominio, Entity di Room
 * Modello di dominio: usata per popolare le views in StatFragment e i grafici di dispersione
 * Entity: nel Database Room, in relazione uno a uno con player (non inglobata dentro Tag per separare le responsabilità)
 */
@Entity(foreignKeys = {
        @ForeignKey(
                entity = Tag.class,
                parentColumns = "tag",
                childColumns = "tag",
                onDelete = ForeignKey.CASCADE
        )
},
        indices = {@androidx.room.Index(value = "tag")}
)
public class Stat {

    @PrimaryKey @NonNull
    public String tag;

    @SerializedName("3vs3Victories") // annotation di Retrofit per il parsing gson
    public int _3vs3Victories;
    public int trophies;
    public int expLevel;

    //Room non riesce a "usare" gli oggetti, quindi dobbiamo usare Embedded per wrappare
    //diventa un tipo primitivo (String) club_clubName
    @Embedded(prefix = "club_")
    public ClubEntry club;
    public int highestTrophies;
    public int rank;
    public int soloVictories;
    public int duoVictories;

    // Costruttore vuoto necessario per Room
    public Stat() {
        this.tag = "";
    }

    @NonNull
    public String getTag() {
        return tag;
    }

    public void setTag(@NonNull String tag) {
        this.tag = tag;
    }

    public Stat(StatBuilder statBuilder) {
        this.tag = statBuilder.tag;
        this._3vs3Victories = statBuilder._3vs3Victories;
        this.trophies = statBuilder.trophies;
        this.expLevel = statBuilder.expLevel;
        this.club = statBuilder.club;
        this.highestTrophies = statBuilder.highestTrophies;
        this.rank = statBuilder.rank;
        this.soloVictories = statBuilder.soloVictories;
        this.duoVictories = statBuilder.duoVictories;
    }

    public static class StatBuilder {
        private String tag;
        private int _3vs3Victories;
        private int trophies;
        private int expLevel;
        private ClubEntry club;
        private int highestTrophies;
        private int rank;
        private int soloVictories;
        private int duoVictories;

        public StatBuilder tag(String tag) {
            this.tag = tag;
            return this;
        }
        public StatBuilder _3vs3Victories(int _3vs3Victories) {
            this._3vs3Victories = _3vs3Victories;
            return this;
        }

        public StatBuilder trophies(int trophies) {
            this.trophies = trophies;
            return this;
        }

        public StatBuilder expLevel(int expLevel) {
            this.expLevel = expLevel;
            return this;
        }

        public StatBuilder club(ClubEntry club) {
            this.club = club;
            return this;
        }

        public StatBuilder highestTrophies(int highestTrophies) {
            this.highestTrophies = highestTrophies;
            return this;
        }

        public StatBuilder rank(int rank) {
            this.rank = rank;
            return this;
        }

        public StatBuilder soloVictories(int soloVictories) {
            this.soloVictories = soloVictories;
            return this;
        }

        public StatBuilder duoVictories(int duoVictories) {
            this.duoVictories = duoVictories;
            return this;
        }

        public Stat build() {
            return new Stat(this);
        }
    }
}
