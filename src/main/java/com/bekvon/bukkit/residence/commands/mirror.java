package com.bekvon.bukkit.residence.commands;

import com.bekvon.bukkit.cmiLib.ConfigReader;
import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.containers.CommandAnnotation;
import com.bekvon.bukkit.residence.containers.cmd;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;

public class mirror implements cmd {

    @Override
    @CommandAnnotation(simple = true, priority = 3700)
    public Boolean perform(Residence plugin, CommandSender sender, String[] args, boolean resadmin) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        if (args.length != 2)
            return false;

        plugin.getResidenceManager().mirrorPerms(player, args[1], args[0], resadmin);
        return true;
    }

    @Override
    public void getLocale() {
        ConfigReader c = Residence.getInstance().getLocaleManager().getLocaleConfig();
        c.get("Description", "Mirrors Flags");
        c.get("Info", Arrays.asList("&eUsage: &6/res mirror [Source Residence] [Target Residence]",
                "Mirrors flags from one residence to another.  You must be owner of both or a admin to do this."));
        Residence.getInstance().getLocaleManager().CommandTab.put(Collections.singletonList(this.getClass().getSimpleName()), Arrays.asList("[residence]", "[residence]"));
    }
}
