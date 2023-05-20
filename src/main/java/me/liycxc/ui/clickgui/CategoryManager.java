package me.liycxc.ui.clickgui;

import me.liycxc.ui.clickgui.impl.FeatureCategory;
import me.liycxc.ui.clickgui.impl.features.CombatModules;
import me.liycxc.ui.clickgui.impl.features.MovementModules;
import me.liycxc.ui.clickgui.impl.features.RenderModules;
import me.liycxc.ui.clickgui.impl.features.UtiltyModules;

import java.util.ArrayList;

public class CategoryManager {
    private ArrayList<Category> categories = new ArrayList<Category>();

    public CategoryManager() {
        categories.add(new FeatureCategory());
        categories.add(new CombatModules());
        categories.add(new MovementModules());
        categories.add(new RenderModules());
        categories.add(new UtiltyModules());
//        categories.add(new EditHUDCategory());
//        categories.add(new ConfigCategory());
//        categories.add(new CosmeticCategory());
//        categories.add(new EditHUDCategory());
//        categories.add(new MusicPlayerCategory());
//        categories.add(new SettingsCategory());
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public Category getCategoryByName(String name) {
        return categories.stream().filter(category -> category.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Category getCategoryByClass(Class<?> categoryClass) {
        return categories.stream().filter(category -> category.getClass().equals(categoryClass)).findFirst().orElse(null);
    }

    public boolean isModule(Category category,CategoryManager categoryManager) {
        return (category.equals(categoryManager.getCategoryByClass(FeatureCategory.class)) || category.equals(categoryManager.getCategoryByClass(CombatModules.class)) || category.equals(categoryManager.getCategoryByClass(MovementModules.class)) || category.equals(categoryManager.getCategoryByClass(RenderModules.class)) || category.equals(categoryManager.getCategoryByClass(UtiltyModules.class)));
    }
}
