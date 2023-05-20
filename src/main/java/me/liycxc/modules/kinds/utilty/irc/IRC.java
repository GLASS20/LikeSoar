package me.liycxc.modules.kinds.utilty.irc;

import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventTick;
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

import java.nio.charset.Charset;
import java.util.ArrayList;

public class IRC extends Module {
    public IRC() {
        super("IRC","Realtime Chat", ModuleCategory.Util);
    }

    private boolean serverStatus = false;
    private ArrayList<String> locMessages = new ArrayList<>();

    @Override
    public void onEnable() {
        qqLogin();
    }

    @EventTarget
    public void onTick(EventTick tick) {
        if (!serverStatus) {

        }
        new Thread() {
            @Override
            public void run() {
                // TODO: Echo messages
                super.run();
            }
        };
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
                this.toggle();
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

                        // Someone use we cant sees untied text, so we call then "null" not null
                        MineUser.qqName = StringUtils.isNullOrEmpty(qqName.replaceAll("\\p{C}", "")) ? "null" : qqName.replaceAll("\\p{C}", "");
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
