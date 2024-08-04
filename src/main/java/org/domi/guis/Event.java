package org.domi.guis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.domi.function.interfaces.DomiGUI;

public class Event implements Listener {
    private GUIManager guiManager;

    @EventHandler
    public void onOpenInventory(InventoryOpenEvent event) {
        if (event.getPlayer() instanceof Player player) {
            GUIManager inv = GUIData.get(player);
            if (inv == null) {
                return;
            }
            inv.onOpen(event);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        GUIManager inv = GUIData.get(player);
        if (inv == null) {
            return;
        }
        inv.onClose(event);
        GUIData.remove(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        GUIManager inv = GUIData.get(player);
        if (inv == null) {
            return;
        }
        GUIData.remove(player);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getWhoClicked() instanceof Player p) {
            GUIManager inv = GUIData.get(p);
            if (inv == null) {
                return;
            }
            inv.onDrag(event);
        }
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        DomiGUI guiInfo = guiManager.getClass().getAnnotation(DomiGUI.class);
        Player player = (Player) event.getWhoClicked();
        GUIManager inv = GUIData.get(player);
        if (inv == null) {
            return;
        }
        if (guiInfo.justLook()) {
            event.setCancelled(true);
        }
        if (event.getClickedInventory() == null || event.getCurrentItem() == null) {
            inv.onClickOutside(event);
            return;
        }
        inv.onClick(event);
    }


}
