package xyz.brawl.gamerise.source.user;

import xyz.brawl.gamerise.model.GoogleUser;
import xyz.brawl.gamerise.repository.user.UserCallBack;

public abstract class BaseUserTagRemoteDataSource {
        protected UserCallBack userResponseCallback;

        public void setUserResponseCallback(UserCallBack userResponseCallback) {
            this.userResponseCallback = userResponseCallback;
        }

        public abstract void saveUserDataOnFirebaseDB(GoogleUser googleUser);

        public abstract void getUserTag(String sessionId);

        public abstract void saveUserTag(String tag, String sessionId);
    }

