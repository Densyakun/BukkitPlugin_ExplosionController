package org.densyakun.bukkit.explosioncontroller;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin implements Listener {
	boolean blockbreak = false;
	boolean entitydamage = true;
	boolean explosioncancel = false;
	@Override
	public void onEnable() {
		saveDefaultConfig();
		blockbreak = getConfig().getBoolean("blockbreak", false);
		entitydamage = getConfig().getBoolean("entitydamage", true);
		explosioncancel = getConfig().getBoolean("explosioncancel", false);
		getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void EntityExplode(EntityExplodeEvent e) {
		if (explosioncancel) {
			e.setCancelled(true);
		} else if (!blockbreak) {
			e.blockList().clear();
		}
	}
	@EventHandler
	public void EntityDamage(EntityDamageEvent e) {
		if ((!entitydamage || explosioncancel) && ((e.getCause() == DamageCause.BLOCK_EXPLOSION) || (e.getCause() == DamageCause.ENTITY_EXPLOSION))) {
			e.setCancelled(true);
		}
	}
}
