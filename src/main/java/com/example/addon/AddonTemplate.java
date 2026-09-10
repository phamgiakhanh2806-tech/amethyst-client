package com.example.addon;

import com.example.addon.modules.AmethystScanner;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;

public class AddonTemplate extends MeteorAddon {
    public static final Category CATEGORY = new Category("Amethyst Client");

    @Override
    public void onInitialize() {
        // Đăng ký Module AmethystScanner vào Meteor
        Modules.get().add(new AmethystScanner(CATEGORY));
    }

    @Override
    public void onRegisterCategories() {
        Modules.REGISTERED_CATEGORIES.add(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}
