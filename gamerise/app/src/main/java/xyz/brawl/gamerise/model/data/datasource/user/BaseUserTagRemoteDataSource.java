package xyz.brawl.gamerise.model.data.datasource.user;

import xyz.brawl.gamerise.model.data.user.GoogleUser;
import xyz.brawl.gamerise.model.repository.user.UserCallBack;

public abstract class BaseUserTagRemoteDataSource {
        protected UserCallBack userResponseCallback;

        public void setUserResponseCallback(UserCallBack userResponseCallback) {
            this.userResponseCallback = userResponseCallback;
        }

        public abstract void saveUserDataOnFirebaseDB(GoogleUser googleUser);

        public abstract void getUserTag(String sessionId);

        public abstract void saveUserTag(String tag, String sessionId);
    }

