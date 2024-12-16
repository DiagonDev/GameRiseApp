package xyz.brawl.gamerise.util;

import android.content.Context;

import java.util.List;

import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.model.data.battle.BattleMapper;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.battlelog.IBattleLogRepository;

/**
 * Applicazione pattern Façade
 * Questa classe incapsula tutti i metodi relativi alla logica di fetch da API e di salvataggio nel database
 * Verrà chiamata dai ViewModel e userà i metodi dei repository
 */
public class DownloadDataFacade implements ResponseCallback{
    private final Context context;
    private GameRiseDatabase database;
    private IBattleLogRepository battleLogRepository;

    public DownloadDataFacade(Context context) {
        this.context = context;
        database = GameRiseDatabase.getDatabase(context);
    }

    public void downloadAndSaveData(Tag tag) {

        battleLogRepository = new BattleLogRepository(context, this);
        battleLogRepository.fetchBattleLog(Constants.tagTeo);

        // Salva il tag nel database ed elimina i meno recenti se necessario
        if (database != null) {
            GameRiseDatabase.databaseWriteExecutor.execute(() -> {
                // Controlla se il tag esiste già nel database

                if (database.tagDao().findTagByName(tag.getTag()) != null) {
                    // Rimuovi il tag esistente dalla vecchia posizione
                    database.tagDao().delete(tag);
                }
                //prima di inserire il tag devo fetchare
                database.tagDao().insertAll(tag); // Inserisci il nuovo tag nel database

                // Controlla se ci sono più di 3 tag nel database
                List<Tag> allTags = database.tagDao().getRecentTags();
                if (allTags.size() > 3) {
                    // Rimuovi il tag più vecchio
                    Tag oldestTag = allTags.get(allTags.size() - 1); // Ultimo nella lista
                    database.tagDao().delete(oldestTag);
                    allTags.remove(oldestTag);
                }
            });
        }
    }

    //TODO: implementare altre fetchApi e ritornare il Battleid
    @Override
    public void onSuccess(Object o, long lastUpdate) {
        List<BattleLogEntry> list = (List<BattleLogEntry>) o;
        if(list != null){
            if (database != null && database.tagDao().findTagByName(GameAccountSingleton.getInstance().getUserTag()) != null) {
                // Eseguiamo l'operazione di scrittura in background
                GameRiseDatabase.databaseWriteExecutor.execute(() -> {
                    database.battleDAO().insertAll(BattleMapper.mapToBattles(list));
                });
            }
        }
    }

    @Override
    public void onFailure(String errorMessage) {

    }
}
