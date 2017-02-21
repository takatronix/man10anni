package red.man10;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public final class man10anni extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("annitop")) {
            //annitop 処理 by sho0
            //takatornix追加 shoありがと
            PermissionsEx e = new PermissionsEx();
            //  ビルドできたぽい -> discord通知テスト２
            //　discord 通知テスト by sho
            //　discord 通知テスト2 by sho
        }
        return true;
    }
}
