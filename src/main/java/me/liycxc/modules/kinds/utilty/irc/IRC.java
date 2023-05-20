package me.liycxc.modules.kinds.utilty.irc;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import lombok.Setter;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import me.liycxc.utils.Logger;
import me.liycxc.utils.PlayerUtils;
import net.minecraft.util.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class IRC extends Module {
    public IRC() {
        super("IRC","Realtime Chat", ModuleCategory.Util);
    }

    @Setter
    private static boolean serverStatus = false;
    private final ArrayList<ObjectId> locMessages = new ArrayList<>();
    private final MSTimer timer = new MSTimer();
    private final Thread messageGetter = new Thread("Message Getter") {
        @Override
        public void run() {
            if (!serverStatus) {
                ServerUtils.ConnectServer();
            }
            if (!serverStatus) {
                return;
            }
            timer.reset();
            while (serverStatus) {
                try {
                    if (timer.hasTimePassed(800L)) {
                        FindIterable<Document> findIterable = ServerUtils.messagesCollection.find(Filters.gte("time",timer.time - 3000));
                        MongoCursor<Document> mongoCursor = findIterable.iterator();

                        while (mongoCursor.hasNext()) {
                            Document document = mongoCursor.next();
                            if (!document.isEmpty()) {
                                if (!locMessages.contains(document.getObjectId("_id"))) {
                                    locMessages.add(document.getObjectId("_id"));
                                    PlayerUtils.tellPlayerIrcMessage(document.get("UserName").toString(),document.get("Message").toString());
                                }
                            }
                        }

                        mongoCursor.close();
                        timer.reset();
                    }
                }catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onInitialize() {
        messageGetter.start();

        if (!this.getToggled()) {
            messageGetter.suspend();
        }
    }
    @Override
    public void onEnable() {
        qqLogin();
        messageGetter.resume();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        messageGetter.suspend();
        super.onDisable();
    }
    /**
     * Getting QQ Name and QQ Number
     * table: MineUser
     */
    private void qqLogin() {
        Logger.log("QQ Login starting");

        PlayerUtils.tellPlayerIrc("IRC - Multi-Platform");
        if (MineUser.isNull()) {
            PlayerUtils.tellPlayerIrc("Not Login, Please login");
            PlayerUtils.tellPlayerIrc("Auto QQ Login...");
            Logger.log("Getting qq of windows");
            PenguinUtils.getQQ();
            if (StringUtils.isNullOrEmpty(PenguinUtils.QQNumber)) {
                PlayerUtils.tellPlayerIrc("Cant get QQ data, module down");
                Logger.warn("No app's login account or cant get data");
                this.setToggled(false);
            } else {
                PlayerUtils.tellPlayerIrc("QQ Number: " + PenguinUtils.QQNumber);
                MineUser.qqNumber = PenguinUtils.QQNumber;

                HttpClient client = HttpClients.createDefault();
                String url = "https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=" + MineUser.qqNumber;
                HttpGet httpGet = new HttpGet(url);

                String returnData;
                try {
                    HttpResponse res = client.execute(httpGet);
                    if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        // OMG qzone return's data is GBK coding, but I think UTF-8 is better
                        returnData = EntityUtils.toString(res.getEntity(), Charset.forName("GBK"));
                        String qqName = returnData.substring(returnData.substring(0,returnData.lastIndexOf("\"")).lastIndexOf("\"") + 1,returnData.lastIndexOf("\""));

                        // Someone use we cant sees untied text, so call then's number
                        MineUser.qqName = StringUtils.isNullOrEmpty(qqName.replaceAll("\\p{C}", "")) ? MineUser.qqNumber : qqName.replaceAll("\\p{C}", "");
                    } else {
                        Logger.error("Oops, HttpStatus is " + res.getStatusLine().getStatusCode());
                    }
                } catch (Exception e) {
                    Logger.error("We cant get qq name by qzone.qq.com");
                }
                PlayerUtils.tellPlayerIrc("QQ Name: " + MineUser.qqName);
                Logger.log("QQ data got ok");
            }
        } else {
            Logger.log("QQ data is ready");
        }
    }
}
