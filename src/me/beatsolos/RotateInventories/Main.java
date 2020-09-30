package me.beatsolos.RotateInventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;

public class Main extends JavaPlugin {
    public static Plugin plugin;

    public void onEnable() {
        plugin = this;

        new BukkitRunnable() {
            @Override
            public void run() {

                if(Bukkit.getOnlinePlayers().size() > 1) {
                    ArrayList<Player> players = new ArrayList<Player>();
                    for (Player p : Bukkit.getOnlinePlayers())
                        players.add(p);

                    ItemStack[] p0inv = players.get(0).getInventory().getContents();
                    ItemStack[] p0armor = players.get(0).getInventory().getArmorContents();
                    ItemStack p0offhand = players.get(0).getInventory().getItemInOffHand();

                    for (int i = 0; i < players.size(); i++) {

                        if (i == players.size() - 1) {
                            // contents
                            players.get(i).getInventory().setContents(p0inv);
                            // armor contents
                            players.get(i).getInventory().setArmorContents(p0armor);
                            // off hand
                            players.get(i).getInventory().setItemInOffHand(p0offhand);
                        } else {
                            // contents
                            players.get(i).getInventory().setContents(players.get(i + 1).getInventory().getContents());
                            // armor contents
                            players.get(i).getInventory().setArmorContents(players.get(i + 1).getInventory().getArmorContents());
                            // off hand
                            players.get(i).getInventory().setItemInOffHand(players.get(i + 1).getInventory().getItemInOffHand());
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20*100);
    }
    public void onDisable() {
        // nothing
    }
}
