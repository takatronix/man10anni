package red.man10;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.*;


public final class man10anni extends JavaPlugin implements Listener {


    String mysql_ip = this.getConfig().getString("mysql_ip");
    String mysql_port = this.getConfig().getString("mysql_port");
    String mysql_id = this.getConfig().getString("mysql_id");
    String mysql_pass = this.getConfig().getString("mysql_pass");
    String db_name = this.getConfig().getString("db_name");

    String ssh_port = this.getConfig().getString("ssh_port");
    String ssh_id = this.getConfig().getString("ssh_id");
    String ssh_pass = this.getConfig().getString("ssh_pass");
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
        getLogger().info("annixp has been disabled");
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        configReload();
        p.sendMessage("manni test");
        /*
        if (cmd.getName().equalsIgnoreCase("annitop")) {
            //action annitop command

        }else

        */
     //   p.sendMessage(cmd.getName());
        if(cmd.getName().equalsIgnoreCase("manni")){

            //      test
            this.serverTest(p);
/*
            if(args.length >= 1){
                if(args[0].equalsIgnoreCase("help")){
                    //action manni help command

                }else if(args[0].equalsIgnoreCase("reload")){
                    configReload();
                    p.sendMessage("reloaded config");

                }

            }else{
                p.sendMessage("wrong command usage do /manni help for a list of commands");
            }*/
        }
        return true;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        getLogger().info("logged in");
    }
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {

        getLogger().info("logged quit");
    }

    public void configReload(){
        this.reloadConfig();
        mysql_ip = this.getConfig().getString("mysql_ip");
        mysql_port = this.getConfig().getString("mysql_port");
        mysql_id = this.getConfig().getString("mysql_id");
        mysql_pass = this.getConfig().getString("mysql_pass");
        ssh_port = this.getConfig().getString("ssh_port");
        ssh_id = this.getConfig().getString("ssh_id");
        ssh_pass = this.getConfig().getString("ssh_pass");
        item_xp = this.getConfig().getString("item_xp");
        db_name = this.getConfig().getString("db_name");
        getLogger().info("reloaded");

        getLogger().info(db_name);
    }
    Connection con = null;
    public void serverTest(Player p){

        //          MySQLに接続
        try{
            String target =  "jdbc:mysql://" + mysql_ip + ":" + mysql_port + "/" + db_name;
            p.sendMessage(target);
            getLogger().info("target" + target);
            con = DriverManager.getConnection(target, mysql_id, mysql_pass);
            getLogger().info("MySQL Connected");
        } catch (SQLException e) {
            getLogger().warning("Could not connect to MySQL");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    getLogger().warning("Failed to close MySQL");
                }
            }
        }
    }

}
