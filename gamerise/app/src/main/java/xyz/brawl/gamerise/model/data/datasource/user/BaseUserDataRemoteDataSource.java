package xyz.brawl.gamerise.model.data.datasource.user;

import xyz.brawl.gamerise.model.data.user.User;
import xyz.brawl.gamerise.model.repository.user.UserCallBack;

public abstract class BaseUserDataRemoteDataSource {
        protected UserCallBack userResponseCallback;

        public void setUserResponseCallback(UserCallBack userResponseCallback) {
            this.userResponseCallback = userResponseCallback;
        }

        public abstract void saveUserData(User user);

        public abstract void getUserTag(String sessionId);

        public abstract void saveUserTag(String tag, String sessionId);
    }

