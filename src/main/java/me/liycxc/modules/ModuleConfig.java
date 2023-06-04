package me.liycxc.modules;

import me.liycxc.NekoCat;
import me.liycxc.api.Value;
import me.liycxc.api.impl.BoolValue;
import me.liycxc.api.impl.FloatValue;
import me.liycxc.api.impl.IntValue;
import me.liycxc.api.impl.ListValue;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ModuleConfig {
    private ArrayList<File> configs = new ArrayList<File>();

    private int prevConfigs;
    private double scrollY;

    public ModuleConfig() {
        this.loadConfigs();
        this.load();
    }

    public void loadConfigs() {
        if(prevConfigs != Objects.requireNonNull(NekoCat.instance.fileManager.getNConfigDir().listFiles()).length) {

            prevConfigs = Objects.requireNonNull(NekoCat.instance.fileManager.getNConfigDir().listFiles()).length;

            configs.clear();

            scrollY = 0;

            FilenameFilter filter = (file, str) -> str.endsWith("nekocat");

            File[] fileArray = NekoCat.instance.fileManager.getNConfigDir().listFiles(filter);

            if (fileArray != null) {
                Collections.addAll(configs, fileArray);
            }
        }
    }

    public void save(File file) {

        ArrayList<String> toSave = new ArrayList<String>();

        for (Module module : NekoCat.instance.moduleManager.getModules()) {
            toSave.add("ModuleName:" + module.moduleName + ":" + module.getToggled());
            // toSave.add("ModulePos:");
        }

        for (Module module : NekoCat.instance.moduleManager.getModules()) {
            if (module.getValues().size() > 0) {
                for (Value<?> value : module.getValues()) {
                    toSave.add("SET:" + module.moduleName + ":" + value.getName() + ":" + value.get());
                }
            }
        }

        try {
            PrintWriter pw = new PrintWriter(file);
            for (String str : toSave) {
                pw.println(str);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            // Don't give crackers clues...
            if (NekoCat.instance.DEVELOPMENT_SWITCH)
                e.printStackTrace();
        }
    }

    public void load(File file) {

        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            // Don't give crackers clues...
            if (NekoCat.instance.DEVELOPMENT_SWITCH)
                e.printStackTrace();
        }

        for (String s : lines) {

            String[] args = s.split(":");

            if (s.toLowerCase().startsWith("modulename:")) {
                Module m = NekoCat.instance.moduleManager.getModule(args[1]);
                if (m != null) {
                    m.setToggled(Boolean.parseBoolean(args[2]));
                }
            } else if (s.toLowerCase().startsWith("set:")) {
                Module m = NekoCat.instance.moduleManager.getModule(args[1]);
                if (m != null) {
                    Value<?> set = m.getValueByName(args[2]);
                    if (set != null) {
                        if (set instanceof BoolValue) {
                            ((BoolValue) set).set(Boolean.parseBoolean(args[3]));
                        }
                        if (set instanceof ListValue) {
                            ((ListValue) set).set(args[3]);
                        }
                        if (set instanceof FloatValue) {
                            ((FloatValue) set).set(Float.parseFloat(args[3]));
                        }
                        if (set instanceof IntValue) {
                            ((IntValue) set).set(Integer.parseInt(args[3]));
                        }
                    }
                }
                // PlayerUtils.tellPlayer("args[1]: " + args[1] + " args[2]: " + args[2] + " args[3]: " + args[3]);
            }
        }
    }

    public void save() {
        this.save(NekoCat.instance.fileManager.getNConfigFile());
    }

    public void load() {
        this.load(NekoCat.instance.fileManager.getNConfigFile());
    }

    public ArrayList<File> getConfigs() {
        return configs;
    }

    public double getScrollY() {
        return scrollY;
    }

    public void setScrollY(double scrollY) {
        this.scrollY = scrollY;
    }
}
