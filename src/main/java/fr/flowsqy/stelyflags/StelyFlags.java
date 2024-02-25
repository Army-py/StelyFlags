package fr.flowsqy.stelyflags;

import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;

public class StelyFlags {

    public static StateFlag SPAWN_SPAWNER;
    public static StateFlag INTERACT_VILLAGER;
    public static StateFlag USE_ASCEND_COMMAND;
    public static StateFlag USE_DESCEND_COMMAND;

    public static void load(FlagRegistry flagRegistry) {
        if (SPAWN_SPAWNER == null)
            SPAWN_SPAWNER = registerFlag(flagRegistry, "spawn-spawner", true);
        if (INTERACT_VILLAGER == null)
            INTERACT_VILLAGER = registerFlag(flagRegistry, "interact-villager", false);
        if (USE_ASCEND_COMMAND == null)
            USE_ASCEND_COMMAND = registerFlag(flagRegistry, "use-ascend-command", true);
        if (USE_DESCEND_COMMAND == null)
            USE_DESCEND_COMMAND = registerFlag(flagRegistry, "use-descend-command", true);
    }

    private static StateFlag registerFlag(FlagRegistry flagRegistry, String flagName, boolean defaultValue) {
        final Flag<?> flag = flagRegistry.get(flagName);
        if (flag == null) {
            final StateFlag finalFlag = new StateFlag(flagName, defaultValue);
            flagRegistry.register(finalFlag);
            return finalFlag;
        }
        if (flag instanceof StateFlag)
            return (StateFlag) flag;

        throw new RuntimeException("Can not register the flag '" + flagName + "', another exist with the same name but can not be used");
    }

}
