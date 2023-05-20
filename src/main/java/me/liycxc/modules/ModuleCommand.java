package me.liycxc.modules;

import lombok.Getter;
import me.liycxc.NekoCat;
import me.liycxc.modules.kinds.utilty.irc.utils.ServerUtils;
import me.liycxc.utils.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;

public class ModuleCommand {
    @Getter
    private static String prefix = ".";
    private static final ArrayList<String> commands = new ArrayList<>();

    public ModuleCommand() {
        commands.add("i");
        commands.add("SwitchChat");
        commands.add("Say");
    }

    public static void runCommand(String command) {
        String preCommand = command.contains(" ") ? command.substring(0,command.indexOf(" ")) : command;
        System.out.println(preCommand);
        try {
            switch (preCommand.toLowerCase()) {
                case "i": {
                    if (NekoCat.instance.moduleManager.getModule("IRC").getToggled()) {
                        ServerUtils.sendMessage(command.substring(2));
                    } else {
                        PlayerUtils.tellPlayer("IRC module is down, you cant send any messages");
                    }
                    break;
                }
                case "switchchat": {
                    if (!NekoCat.instance.moduleManager.getModule("IRC").getToggled()) {
                        PlayerUtils.tellPlayer("IRC module is down, you cant switch irc chat.");
                        break;
                    } else {
                        GuiScreen.setStillIrc(!GuiScreen.isStillIrc());
                        PlayerUtils.tellPlayer("Chat switch to " + (GuiScreen.isStillIrc() ? "IRC" : "World"));
                    }
                    break;
                }
                case "say": {
                    if (command.equalsIgnoreCase("say")) {
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(".say");
                        Minecraft.getMinecraft().thePlayer.sendChatMessage(".say");
                    } else {
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(command.substring(4));
                        Minecraft.getMinecraft().thePlayer.sendChatMessage(command.substring(4));
                    }
                }
                default: {
                    PlayerUtils.tellPlayer("Unknown command, What are you saying?");
                    break;
                }
            }
        } catch (Exception exception) {
           exception.printStackTrace();
        }
    }
}
