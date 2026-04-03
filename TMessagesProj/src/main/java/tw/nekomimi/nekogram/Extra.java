package tw.nekomimi.nekogram;

import tw.nekomimi.nekogram.helpers.UserHelper;
import tw.nekomimi.nekogram.helpers.UserHelper.UserInfoBot;

public class Extra {
    public static final int APP_ID = 2865956;
    public static final String APP_HASH = "81cc755b7d9039a73e61ff8c863d0df6";
    public static final String PLAYSTORE_APP_URL = "https://play.google.com/store/apps/details?id=tw.nekomimi.nekogram";
    public static final String TWPIC_BOT_USERNAME = "twpicbot";
    public static final String TLV_URL = "https://nekogram.app/tlv/";
    public static final boolean FORCE_ANALYTICS = false;

    public static boolean isDirectApp() {
        return false;
    }

    public static boolean isTrustedBot(long botId) {
        return false;
    }

    public static UserHelper.BotInfo getHelperBot() {
        return new UserHelper.BotInfo() {
            @Override
            public long getId() {
                return 0;
            }

            @Override
            public String getUsername() {
                return "NekogramHelperBot";
            }
        };
    }

    public static UserInfoBot getUserInfoBot(boolean fallback) {
        return new UserInfoBot() {
            @Override
            public UserHelper.ParsedPeer parsePeer(String[] lines) {
                return null;
            }

            @Override
            public long getId() {
                return 0;
            }

            @Override
            public String getUsername() {
                return "NekogramHelperBot";
            }
        };
    }
}