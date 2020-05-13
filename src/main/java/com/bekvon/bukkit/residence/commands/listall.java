package com.bekvon.bukkit.residence.commands;

import com.bekvon.bukkit.cmiLib.ConfigReader;
import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.containers.CommandAnnotation;
import com.bekvon.bukkit.residence.containers.cmd;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;

public class listall implements cmd {

    @Override
    @CommandAnnotation(simple = true, priority = 4200)
    public Boolean perform(Residence plugin, CommandSender sender, String[] args, boolean resadmin) {

        int page = 1;
        World world = null;

        c:
        for (int i = 1; i < args.length; i++) {
            try {
                page = Integer.parseInt(args[i]);
                if (page < 1)
                    page = 1;
                continue;
            } catch (Exception ex) {
            }

            if (args[i].equalsIgnoreCase("-a") && !(sender instanceof Player)) {
                page = -1;
                continue;
            }
            if (args[i].equalsIgnoreCase("-f") && !(sender instanceof Player)) {
                page = -2;
                continue;
            }

            for (World w : Bukkit.getWorlds()) {
                if (w.getName().equalsIgnoreCase(args[i])) {
                    world = w;
                    continue c;
                }
            }
        }

        plugin.getResidenceManager().listAllResidences(sender, page, resadmin, world);
        return true;
    }

    @Override
    public void getLocale() {
        ConfigReader c = Residence.getInstance().getLocaleManager().getLocaleConfig();
        c.get("Description", "List All Residences");
        c.get("Info", Arrays.asList("&eUsage: &6/res listall <page> <worldName> <-a/-f>", "Lists all residences"));
        Residence.getInstance().getLocaleManager().CommandTab.put(Collections.singletonList(this.getClass().getSimpleName()), Collections.singletonList("[worldname]"));
    }
}
