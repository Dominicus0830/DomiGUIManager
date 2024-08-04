package org.domi.guis;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GUIData {
    private static final Map<Player, GUIManager> playerInventories = new HashMap<>();

    public static void add(Player player, GUIManager guiManager) {
        playerInventories.put(player, guiManager);
    }

    public static GUIManager get(Player player) {
        return playerInventories.get(player);
    }

    public static void remove(Player player) {
        playerInventories.remove(player);
    }
}
