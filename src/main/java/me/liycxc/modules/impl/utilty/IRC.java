package me.liycxc.modules.impl.utilty;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import lombok.Setter;
import me.liycxc.NekoCat;
import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventLoadWorld;
import me.liycxc.api.tags.ModuleTag;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import me.liycxc.utils.Logger;
import me.liycxc.utils.PlayerUtils;
import me.liycxc.utils.irc.*;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static me.liycxc.utils.irc.ServerUtils.getTime;

@ModuleTag
public class IRC extends Module {
    public IRC() {
        super("IRC","Realtime Chat", ModuleCategory.Util,true);
    }

    public static ArrayList<String> sendMessages = new ArrayList<>();
    @Setter
    private static boolean serverStatus = false;
    private final ArrayList<ObjectId> locMessages = new ArrayList<>();
    private final IMSTimer timer = new IMSTimer();
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
                    if (MineUser.isNull()) {
                        qqLogin();
                        continue;
                    }
                    // Echo messages
                    if (timer.hasTimePassed(800L)) {
                        FindIterable<Document> findIterable = ServerUtils.messagesCollection.find(Filters.gte("time",timer.time - 3000));
                        MongoCursor<Document> mongoCursor = findIterable.iterator();

                        while (mongoCursor.hasNext()) {
                            Document document = mongoCursor.next();
                            if (!document.isEmpty()) {
                                if (!locMessages.contains(document.getObjectId("_id"))) {
                                    // Add message to loc array, then aren't echo again
                                    locMessages.add(document.getObjectId("_id"));
                                    // Show message to player
                                    PlayerUtils.tellPlayerIrcMessage(document.get("qqName").toString(),document.get("Message").toString());
                                }
                            }
                        }

                        mongoCursor.close();
                        timer.reset();
                    }

                    // Send loc messages, check message by here
                    if (sendMessages.size() > 0) {
                        for (String sendMessage : sendMessages) {
                            // Replace all cant sees word
                            sendMessage = sendMessage.replaceAll("\\p{C}", "");
                            // Max length is 60, about 30 chinese word
                            if(sendMessage.length() > 60) {
                                sendMessage = sendMessage.substring(0,60);
                            }
                            // Sensitive filter check
                            SensitiveFilter sensitiveFilter = new SensitiveFilter();
                            sendMessage = sensitiveFilter.filter(sendMessage);
                            // Check is null or empty
                            if (StringUtils.isNullOrEmpty(sendMessage)) {
                                return;
                            }
                            // Send to server
                            ServerUtils.messagesCollection.insertOne(new Document().append("qqNumber", MineUser.qqNumber).append("qqName", MineUser.qqName).append("Message",sendMessage).append("time",getTime()).append("times",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(getTime()))));
                            Logger.log("[IRC Send] " + sendMessage);
                        }
                        // Clean waiting messages xD
                        sendMessages.clear();
                    }
                }catch (Exception exception) {
                    // Don't give crackers clues...
                    if (NekoCat.instance.DEVELOPMENT_SWITCH)
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
        this.onLoadWorld(null);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        messageGetter.suspend();
        super.onDisable();
    }

    @EventTarget
    public void onLoadWorld(EventLoadWorld eventLoadWorld) {
        if (toggled) {
            PlayerUtils.tellPlayerIrc("IRC - Multi-Platform");
            if (MineUser.isNull()) {
                PlayerUtils.tellPlayerIrc("Not Login, Please login");
                PlayerUtils.tellPlayerIrc("Auto QQ Login...");
                qqLogin();
                if (StringUtils.isNullOrEmpty(MineUser.qqNumber)) {
                    PlayerUtils.tellPlayerIrc("Cant get QQ data, module down");
                    this.onDisable();
                    this.setToggled(false);
                } else {
                    PlayerUtils.tellPlayerIrc("QQ Name: " + MineUser.qqName);
                    PlayerUtils.tellPlayerIrc("QQ Number: " + PenguinUtils.QQNumber);
                    PlayerUtils.tellPlayerIrc("Use .i or .switchchat to send massage");
                    messageGetter.resume();
                }
            }
        } else {
            this.onDisable();
        }
    }
    /**
     * Getting QQ Name and QQ Number
     * table: MineUser
     */
    private void qqLogin() {
        if (MineUser.isNull()) {
            PenguinUtils.getQQ();
            if (!StringUtils.isNullOrEmpty(PenguinUtils.QQNumber)) {
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
                        this.setToggled(false);
                    }
                } catch (Exception e) {
                    Logger.error("We cant get qq name by qzone.qq.com");
                    this.setToggled(false);
                }
            }
        }
    }
}
