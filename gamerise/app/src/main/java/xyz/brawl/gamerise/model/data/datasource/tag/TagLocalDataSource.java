package xyz.brawl.gamerise.model.data.datasource.tag;

import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.TagDAO;
import xyz.brawl.gamerise.model.data.tag.Tag;

public class TagLocalDataSource extends BaseTagLocalDataSource{
    private final TagDAO tagDAO;

    public TagLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.tagDAO = gameRiseDatabase.tagDAO();
    }

    @Override
    public void getTag() {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            tagCallback.onSuccessFromLocal(tagDAO.getTag());
        });
    }

    @Override
    public void insertTag(Tag tagToInsert) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            Tag tag = tagDAO.getTag();
            if (tag != null) {
                deleteTag(tag);
            }
            tagDAO.insert(tagToInsert);
            tagCallback.onSuccessFromLocal(tagDAO.getTag());
        });
    }

    @Override
    public void deleteTag(Tag tagToDelete) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            Tag tag = tagDAO.getTag();
            if (tag != null) {
                tagDAO.delete(tagToDelete);
                tagCallback.onSuccessFromLocal(tagDAO.getTag());
            } else tagCallback.onFailureFromLocal(new Exception("Database vuoto"));
        });
    }
}
