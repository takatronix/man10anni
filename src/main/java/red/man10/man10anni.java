package red.man10;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class man10anni extends JavaPlugin implements Listener {

    String server_ip = this.getConfig().getString("server_ip");
    String server_port = this.getConfig().getString("server_port");
    String ssh_port = this.getConfig().getString("ssh_port");
    String ssh_id = this.getConfig().getString("ssh_id");
    String ssh_pass = this.getConfig().getString("ssh_pass");
    String mysql_id = this.getConfig().getString("mysql_id");
    String mysql_pass = this.getConfig().getString("mysql_pass");
    String item_xp = this.getConfig().getString("item_xp");


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
            //action annitop command

        }else if(cmd.getName().equalsIgnoreCase("manni")){
            if(args.length >= 1){
                if(args[0].equalsIgnoreCase("help")){
                    //action manni help command


                }else if(args[0].equalsIgnoreCase("reload")){
                    configReload();
                    p.sendMessage("reloaded config");

                }

            }else{
                p.sendMessage("wrong command usage do /manni help for a list of commands");
            }
        }
        return true;
    }

    public void configReload(){
        this.reloadConfig();
        server_ip = this.getConfig().getString("server_ip");
        server_port = this.getConfig().getString("server_port");
        ssh_port = this.getConfig().getString("ssh_port");
        ssh_id = this.getConfig().getString("ssh_id");
        ssh_pass = this.getConfig().getString("ssh_pass");
        mysql_id = this.getConfig().getString("mysql_id");
        mysql_pass = this.getConfig().getString("mysql_pass");
        item_xp = this.getConfig().getString("item_xp");
    }


}
