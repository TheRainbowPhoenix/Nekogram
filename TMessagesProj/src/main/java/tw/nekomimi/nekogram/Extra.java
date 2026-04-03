package tw.nekomimi.nekogram;

import org.lsposed.lsparanoid.Obfuscate;
import org.telegram.messenger.BuildConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tw.nekomimi.nekogram.helpers.InlineBotHelper;
import tw.nekomimi.nekogram.helpers.UserHelper;
import tw.nekomimi.nekogram.helpers.remote.BaseRemoteHelper;
import tw.nekomimi.nekogram.helpers.remote.ConfigHelper;

@Obfuscate
public class Extra {

    public static int APP_ID = 442495;
    public static String APP_HASH = "873ffaceba76e791ff2491224a3cdb49";
    public static boolean FORCE_ANALYTICS = false;
    public static String TWPIC_BOT_USERNAME = "TwPicBot";
    public static String SENTRY_DSN = "";
    public static String PLAYSTORE_APP_URL = "https://play.google.com/store/apps/details?id=tw.nekomimi.nekogram";
    public static String TLV_URL = "https://nekogram.app/tlv/";
    private static final UserHelper.BotInfo HELPER_BOT = new UserHelper.BotInfo() {
        @Override
        public long getId() {
            return 1190800416;
        }

        @Override
        public String getUsername() {
            return "CMS_Monitor_Bot";
        }
    };

    public static List<ConfigHelper.News> getDefaultNews() {
        var news = new ArrayList<ConfigHelper.News>();

        return news;
    }

    public static UserHelper.BotInfo getHelperBot() {
        syncNumbers();
        return HELPER_BOT;
    }

    private static boolean synced = true;

    public static void syncNumbers() {

    }

    private static class USInfoBot extends UserHelper.UserInfoBot {

        @Override
        public long getId() {
            return 189165596;
        }

        @Override
        public String getUsername() {
            return "usinfobot";
        }

        @Override
        public UserHelper.ParsedPeer parsePeer(String[] lines) {
            var peer = new UserHelper.ParsedPeer();
            for (var line : lines) {
                line = line.replaceAll("\\p{C}", "").trim();
                if (line.startsWith("👤")) {
                    var id = Utilities.parseLong(line.replace("👤", "").trim());
                    if (id > 0) {
                        peer.id = id;
                    }
                } else if (line.startsWith("👦🏻")) {
                    peer.first_name = line.replace("👦🏻", "").trim();
                } else if (line.startsWith("👪")) {
                    peer.last_name = line.replace("👪", "").trim();
                } else if (line.startsWith("🌐")) {
                    peer.username = line.replace("🌐", "").replace("@", "").trim();
                } else if (line.startsWith("👥")) {
                    var id = Utilities.parseLong(line.replace("👥", "").trim());
                    if (id < 0) {
                        if (id < -1000000000000L) {
                            peer.id = -1000000000000L - id;
                        } else {
                            peer.id = -id;
                        }
                    }
                } else if (line.startsWith("🏷")) {
                    peer.title = line.replace("🏷", "").trim();
                }
            }
            return peer;
        }
    }

    private static class TGDBBot extends UserHelper.UserInfoBot {

        @Override
        public long getId() {
            return 7424190611L;
        }

        @Override
        public String getUsername() {
            return "tgdb_search_bot";
        }

        @Override
        public UserHelper.ParsedPeer parsePeer(String[] lines) {
            var peer = new UserHelper.ParsedPeer();
            for (var line : lines) {
                line = line.replaceAll("\\p{C}", "").trim();
                if (line.startsWith("🆔 ID:")) {
                    var id = Utilities.parseLong(line.replace("🆔 ID:", "").trim());
                    if (id != 0) {
                        peer.id = id;
                    }
                } else if (line.startsWith("🏷 Title:")) {
                    var title = line.replace("🏷 Title:", "").trim();
                    peer.last_name = title;
                    peer.title = title;
                } else if (line.startsWith("📧 Username:")) {
                    peer.username = line.replace("📧 Username:", "").replace("@", "").trim();
                }
            }
            return peer;
        }
    }

    public static UserHelper.UserInfoBot getUserInfoBot(boolean fallback) {
        //if (BuildConfig.DEBUG) {
        //    Log.e("Extra", "getUserInfoBot fallback = " + fallback);
        //}
        // default to tgdb because usinfo requires starting the bot
        if (fallback) {
            return new USInfoBot();
        } else {
            return new TGDBBot();
        }
    }

    public static boolean isDirectApp() {
        return "release".equals(BuildConfig.BUILD_TYPE) || "debug".equals(BuildConfig.BUILD_TYPE);
    }

    public static boolean isTrustedBot(long id) {
        return id == 6371744499L || id == 1190800416L;
    }
}