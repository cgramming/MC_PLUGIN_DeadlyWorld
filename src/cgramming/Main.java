package cgramming;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World.Spigot;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.minecraft.network.chat.ChatBaseComponent;
import net.minecraft.server.v1_16_R2.AdvancementProgress.a;

public class Main extends JavaPlugin implements Listener{
	Plugin pl = this;
	public boolean isactive = false;
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);

		}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
				if(command.getName().equalsIgnoreCase("deadlyworld")) {
					if(isactive == false) {
						isactive = true;
						sender.sendMessage(ChatColor.GREEN + "Deadly world is now on");
					}
					else{
						isactive = false;
						sender.sendMessage(ChatColor.RED + "Deadly world is now off");
					}
			}
		
		return false;
	}
	@EventHandler
	public void blockplaced(BlockPlaceEvent event) {
		Block b = event.getBlock();
		b.setMetadata("PLACED", new FixedMetadataValue(pl, true));
	}
	@EventHandler
	public void respawn(PlayerRespawnEvent rsp) {
		Location location = rsp.getRespawnLocation();
				location.setY(location.getY() - 0.1f);
		Block b = location.getBlock();
		b.setMetadata("PLACED", new FixedMetadataValue(pl, true));
	}
	 
	@EventHandler
	public void playerMove ( PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if(isactive == true) {
			Location location = player.getLocation();
			location.setY(location.getY() - 0.1f);
			if(location.getBlock().hasMetadata("PLACED") == false && location.getBlock().getType() != Material.AIR && location.getBlock().getType() != Material.WATER) {
				player.setHealth(0);
			}
		}

	}
}
