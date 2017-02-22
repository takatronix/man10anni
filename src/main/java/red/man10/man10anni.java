package red.man10;


import com.jcraft.jsch.JSch;

import com.jcraft.jsch.Session;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public final class man10anni extends JavaPlugin implements Listener {

    //general config
    String prefix = this.getConfig().getString("pluginprefix").replace("&", "§");

    //mysql config
    String mysql_ip = this.getConfig().getString("server_config.mysql_ip");
    String mysql_port = this.getConfig().getString("server_config.mysql_port");
    String mysql_id = this.getConfig().getString("server_config.mysql_id");
    String mysql_pass = this.getConfig().getString("server_config.mysql_pass");
    String db_name = this.getConfig().getString("server_config.db_name");

    //ssh config
    String ssh_port = this.getConfig().getString("server_config.ssh_port");
    String ssh_ip = this.getConfig().getString("server_config.ssh_ip");
    String ssh_id = this.getConfig().getString("server_config.ssh_id");
    String ssh_pass = this.getConfig().getString("server_config.ssh_pass");
    String item_xp = this.getConfig().getString("server_config.item_xp");

    //item config
    int id = this.getConfig().getInt("reward_item.itemID");
    List<String> coloredLore = new ArrayList<String>();
    List<String> lore = this.getConfig().getStringList("reward_item.item_lore");
    String displayname = this.getConfig().getString("reward_item.display_name");

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("annixp has been enabled");
        this.saveDefaultConfig();
        itemConfigReload();
        configReload();
    }
    @Override
    public void onDisable() {
        getLogger().info("annixp has been disabled");
        // Plugin shutdown logic
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        //p.sendMessage("manni test");
        /*
        if (cmd.getName().equalsIgnoreCase("annitop")) {
            //action annitop command
        }else
        */
        //   p.sendMessage(cmd.getName());
        if(cmd.getName().equalsIgnoreCase("manni")) {
            //      test
            // this.sshTest(p);
            // this.mysqlTest(p);
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    //action manni help command
                    p.sendMessage("§d=§f=§a=§d=§f=§a=§d=§f=§a=§f[§9§lMan10 anni§f]§d=§f=§a=§d=§f=§a=§d=§f=§a=");
                    p.sendMessage("");
                    p.sendMessage("§d/manni help コマンド一覧");
                    p.sendMessage("§d/manni reload コンフィグの再読み込み");
                    p.sendMessage("§d/manni rget <ammount> コンフィグに書かれているアイテムを渡す"); //開発段階用コマンド
                    p.sendMessage("");
                    p.sendMessage("§d=§f=§a=§d=§f=§a=§d=§f=§a=§d=§f=§a=§d=§f=§a=§d=§f=§a=§d=§f=§a=§d=§f=§a=§d=§f=§a=§d=§f=§a=");
                } else if (args[0].equalsIgnoreCase("reload")) {

                    configReload();
                    itemConfigReload();
                    p.sendMessage(prefix + " リロードが完了しました。");
                } else if (args[0].equalsIgnoreCase("rget")) {
                    if (args.length == 2) {
                        try {
                            int ammount = Integer.parseInt(args[1]);
                            giveItem(p, id, ammount);
                        } catch (NumberFormatException e) {
                            p.sendMessage(prefix + " §d<ammount>には数字を入力してください。");
                            return true;
                        }
                    } else {

                        p.sendMessage(prefix + "§dコマンドの使い方が間違ってます /manni rget <個数>");
                    }
                } else {
                    p.sendMessage(prefix + " §d/manni help コマンドの一覧");
                }
            }
            if(args.length == 0){
                p.sendMessage(prefix + " §d/manni help コマンドの一覧");

            }
        }
        return true;
    }

    public void giveItem(Player p,int id, int ammount){
        //ItemStack で新規にアイテムを作成
        Material m = Material.getMaterial(id);
        ItemStack item = new ItemStack(m, ammount);
        ItemMeta itemim = item.getItemMeta();
        //エンチャントはのちのち追加します。
        //ItemMetaを設定
        itemim.setDisplayName(displayname);
        itemim.setLore(coloredLore);
        //ItemMeta を ItemStackに関連付け
        item.setItemMeta(itemim);
        //アイテムをプレイヤーインベントリに追加
        p.getInventory().addItem(item);
    }

    public void configReload(){
        this.reloadConfig();
        prefix = this.getConfig().getString("pluginprefix").replace("&", "§");
        mysql_ip = this.getConfig().getString("server_config.mysql_ip");
        mysql_port = this.getConfig().getString("server_config.mysql_port");
        mysql_id = this.getConfig().getString("server_config.mysql_id");
        mysql_pass = this.getConfig().getString("server_config.mysql_pass");
        ssh_ip = this.getConfig().getString("server_config.ssh_ip");
        ssh_port = this.getConfig().getString("server_config.ssh_port");
        ssh_id = this.getConfig().getString("server_config.ssh_id");
        ssh_pass = this.getConfig().getString("server_config.ssh_pass");
        item_xp = this.getConfig().getString("server_config.item_xp");
        db_name = this.getConfig().getString("server_config.db_name");
        getLogger().info("reloaded");
        // getLogger().info(db_name);
    }
    public void itemConfigReload(){
        lore.clear();
        coloredLore.clear();

        id = this.getConfig().getInt("reward_item.itemID");
        lore = this.getConfig().getStringList("reward_item.item_lore");
        displayname = this.getConfig().getString("reward_item.display_name");
        displayname = ChatColor.translateAlternateColorCodes('&', displayname);
        for(String s : lore){
            coloredLore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        getLogger().info("logged in");
    }
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        getLogger().info("logged quit");
    }


    //以下DB処理


    Connection con = null;          //  mysql connection
    Session session = null;         //  ssh session

    public void sshTest(Player p) {

        p.sendMessage("ssh connecting");
        final JSch jsch = new JSch();
        try{
            session = jsch.getSession(ssh_id, ssh_ip, Integer.parseInt(ssh_port));
            session.setPassword(ssh_pass);

            final Properties config = new Properties();
            //config.put("StrictHostKeyChecking", "no");
            //session.setConfig(config);

            session.connect();
            //      ポートフォワーディング
            session.setPortForwardingL(Integer.parseInt(mysql_port), mysql_ip, Integer.parseInt(mysql_port));
            p.sendMessage("ssh connected");
        } catch(Exception ex) {
            p.sendMessage("ssh connect error");
        }
        finally {

            p.sendMessage("ssh connect error");
        }


    }
    public void mysqlTest(Player p){

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
