package red.man10;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public final class man10anni extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("annixp has been enabled");
        this.saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        getLogger().info("annixp hjas been disabled");
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("annitop")) {
            PermissionUser user = PermissionsEx.getUser(p);
            if(user.has("man10.annixp.test")){
                //権限があったらここに来る
                p.sendMessage("you have permission");
            }else{
                //無かったらこっち
                p.sendMessage("you don't have permission");
            }
        }else if(cmd.getName().equalsIgnoreCase("configreload")){
            this.reloadConfig();
            p.sendMessage("config reloaded");
        }
        return true;
    }



}
