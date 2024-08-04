package org.domi.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.domi.function.interfaces.DomiGUI;
import org.domi.function.interfaces.GUICreater;

public abstract class GUIManager {
    protected Inventory inv;

    /**
     * GUIManager 생성자
     */
    public GUIManager(GUICreater guiInstance) {
        Class<?> clazz = guiInstance.getClass();
        if (clazz.isAnnotationPresent(DomiGUI.class)) {
            DomiGUI guiInfo = clazz.getAnnotation(DomiGUI.class);
            inv = Bukkit.createInventory(null, guiInfo.line() * 9, guiInfo.title());
        }
    }

    /**
     * GUIManager 생성자
     *
     * @param title       GUI 타이틀 수정
     * @param guiInstance GUI 인스턴스
     */
    public GUIManager(String title, GUICreater guiInstance) {
        Class<?> clazz = guiInstance.getClass();
        if (clazz.isAnnotationPresent(DomiGUI.class)) {
            DomiGUI guiInfo = clazz.getAnnotation(DomiGUI.class);
            inv = Bukkit.createInventory(null, guiInfo.line() * 9, title);
        }
    }

    /**
     * 아이템 배치 메소드
     *
     * @param x x좌표 (1부터 시작) (1~9)
     * @param y y좌표 (1부터 시작) (1~6)
     */
    public void setupItems(int x, int y) {
        int slot = (x - 1) + y * 9;
        inv.setItem(0, null);
    }

    public final ItemStack getItem(int x, int y) {
        return inv.getItem((x - 1) + y * 9);
    }

    public Inventory getPlayerInventory(Player player) {
        return (Inventory) GUIData.get(player);
    }

    public void openGUI(Player player) {
        player.closeInventory();
        GUIData.add(player, this);
        player.openInventory(inv);
    }

    protected abstract void onOpen(InventoryOpenEvent event);

    protected abstract void onClose(InventoryCloseEvent event);

    protected abstract void onClick(InventoryClickEvent event);
    protected abstract void onClickOutside(InventoryClickEvent e);

    protected abstract void onDrag(InventoryDragEvent event);

}
