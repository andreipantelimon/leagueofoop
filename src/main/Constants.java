package main;

public final class Constants {
    // Constants class.
    private Constants() { }
    public static final int BASE_XP = 200;
    public static final int LEVEL_XP = 40;
    public static final int LEVELUP_BASE_XP = 250;
    public static final int LEVELUP_LEVEL_XP = 50;
    public static final int PERCENT_DIVISOR = 100;

    public static final int P_MAX_HP = 500;
    public static final int K_MAX_HP = 900;
    public static final int W_MAX_HP = 400;
    public static final int R_MAX_HP = 600;

    public static final int P_LEVEL_HP = 50;
    public static final int K_LEVEL_HP = 80;
    public static final int W_LEVEL_HP = 30;
    public static final int R_LEVEL_HP = 40;

    public static final float DESERT_MOD = 0.1f;
    public static final float LAND_MOD = 0.15f;
    public static final float VOLCANIC_MOD = 0.25f;
    public static final float WOODS_MOD = 0.15f;

    public static final float P_FIREBLAST_R_MOD = -0.2f;
    public static final float P_FIREBLAST_K_MOD = 0.2f;
    public static final float P_FIREBLAST_P_MOD = -0.1f;
    public static final float P_FIREBLAST_W_MOD = 0.05f;

    public static final float P_IGNITE_R_MOD = -0.2f;
    public static final float P_IGNITE_K_MOD = 0.2f;
    public static final float P_IGNITE_P_MOD = -0.1f;
    public static final float P_IGNITE_W_MOD = 0.05f;

    public static final float K_EXECUTE_R_MOD = 0.15f;
    public static final float K_EXECUTE_K_MOD = 0;
    public static final float K_EXECUTE_P_MOD = 0.1f;
    public static final float K_EXECUTE_W_MOD = -0.2f;

    public static final float K_SLAM_R_MOD = -0.2f;
    public static final float K_SLAM_K_MOD = 0.2f;
    public static final float K_SLAM_P_MOD = -0.1f;
    public static final float K_SLAM_W_MOD = 0.05f;

    public static final int EXECUTE_BASE_DMG = 200;
    public static final int EXECUTE_LEVEL_DMG = 30;
    public static final int EXECUTE_HP_LIMIT = 20;
    public static final int EXECUTE_HP_MAX = 40;

    public static final int SLAM_BASE_DMG = 100;
    public static final int SLAM_LEVEL_DMG = 40;

    public static final float W_DRAIN_R_MOD = -0.2f;
    public static final float W_DRAIN_K_MOD = 0.2f;
    public static final float W_DRAIN_P_MOD = -0.1f;
    public static final float W_DRAIN_W_MOD = 0.05f;

    public static final float W_DEFLECT_R_MOD = 0.2f;
    public static final float W_DEFLECT_K_MOD = 0.4f;
    public static final float W_DEFLECT_P_MOD = 0.3f;

    public static final float R_BACKSTAB_R_MOD = 0.2f;
    public static final float R_BACKSTAB_K_MOD = -0.1f;
    public static final float R_BACKSTAB_PW_MOD = 0.25f;

    public static final float R_PARALYSIS_R_MOD = -0.1f;
    public static final float R_PARALYSIS_K_MOD = -0.2f;
    public static final float R_PARALYSIS_P_MOD = 0.2f;
    public static final float R_PARALYSIS_W_MOD = 0.25f;
    public static final int R_PARALYSIS_BASE_ROUND = 3;
    public static final int R_PARALYSIS_EXT_ROUND = 6;

    public static final int FIREBLAST_BASE_DMG = 350;
    public static final int FIREBLAST_LEVEL_DMG = 50;
    public static final int IGNITE_BASE_DMG = 150;
    public static final int IGNITE_LEVEL_DMG = 20;
    public static final int IGNITE_ROUND_BASE_DMG = 50;
    public static final int IGNITE_ROUND_LEVEL_DMG = 30;

    public static final int BACKSTAB_BASE_DMG = 200;
    public static final int BACKSTAB_LEVEL_DMG = 20;
    public static final int PARALYSIS_BASE_DMG = 40;
    public static final int PARALYSIS_LEVEL_DMG = 10;
    public static final float ROGUE_CRIT = 1.5f;

    public static final float DRAIN_BASE_DMG = 0.2f;
    public static final float DRAIN_LEVEL_DMG = 0.05f;
    public static final float DRAIN_HP_MOD = 0.3f;
    public static final float DEFLECT_BASE_DMG = 0.35f;
    public static final float DEFLECT_LEVEL_DMG = 0.02f;
    public static final float DEFLECT_MOD = 0.7f;

    public static final float DAMAGE_ANGEL_KNIGHT = 0.15f;
    public static final float DAMAGE_ANGEL_PYRO = 0.2f;
    public static final float DAMAGE_ANGEL_ROGUE = 0.3f;
    public static final float DAMAGE_ANGEL_WIZARD = 0.4f;

    public static final float LEVELUP_ANGEL_KNIGHT = 0.1f;
    public static final float LEVELUP_ANGEL_PYRO = 0.2f;
    public static final float LEVELUP_ANGEL_ROGUE = 0.15f;
    public static final float LEVELUP_ANGEL_WIZARD = 0.25f;

    public static final float DRACULA_KNIGHT = -0.2f;
    public static final float DRACULA_PYRO = -0.3f;
    public static final float DRACULA_ROGUE = -0.1f;
    public static final float DRACULA_WIZARD = -0.4f;

    public static final float SMALL_ANGEL_KNIGHT = 0.1f;
    public static final float SMALL_ANGEL_PYRO = 0.15f;
    public static final float SMALL_ANGEL_ROGUE = 0.05f;
    public static final float SMALL_ANGEL_WIZARD = 0.1f;

    public static final float GOOD_BOY_KNIGHT = 0.4f;
    public static final float GOOD_BOY_PYRO = 0.5f;
    public static final float GOOD_BOY_ROGUE = 0.4f;
    public static final float GOOD_BOY_WIZARD = 0.3f;

    public static float getStrategyEmpowerMin(String type) {
        switch (type) {
            case "K": return 1/3f;
            case "P":
            case "W":
                return 1/4f;
            case "R": return 1/7f;
            default: return 0f;
        }
    }

    public static float getStrategyEmpowerMax(String type) {
        switch (type) {
            case "K":
            case "W":
                return 1/2f;
            case "P": return 1/3f;
            case "R": return 1/5f;
            default: return 0f;
        }
    }

    public static float getStrategyEmpowerHp(String type) {
        switch (type) {
            case "K": return 1/5f;
            case "W": return 1/10f;
            case "P": return 1/4f;
            case "R": return 1/7f;
            default: return 0f;
        }
    }

    public static float getStrategyEmpowerPercent(String type) {
        switch (type) {
            case "K": return 0.5f;
            case "W": return 0.6f;
            case "P": return 0.7f;
            case "R": return 0.4f;
            default: return 0f;
        }
    }

    public static float getStrategyHealMax(String type) {
        switch (type) {
            case "K": return 1/3f;
            case "W":
            case "P":
                return 1/4f;
            case "R": return 1/7f;
            default: return 0f;
        }
    }

    public static float getStrategyHealHp(String type) {
        switch (type) {
            case "K": return 1/4f;
            case "W": return 1/5f;
            case "P": return 1/3f;
            case "R": return 1/2f;
            default: return 0f;
        }
    }

    public static float getStrategyHealPercent(String type) {
        switch (type) {
            case "K":
            case "W":
                return 0.2f;
            case "P": return 0.3f;
            case "R": return 0.1f;
            default: return 0f;
        }
    }

    public static int getLifeGiverHp(String type) {
        switch (type) {
            case "K": return 100;
            case "W": return 120;
            case "P": return 80;
            case "R": return 90;
            default: return 0;
        }
    }

    public static int getDraculaHp(String type) {
        switch (type) {
            case "K": return 60;
            case "W": return 20;
            case "P": return 40;
            case "R": return 35;
            default: return 0;
        }
    }

    public static int getXpAngelXp(String type) {
        switch (type) {
            case "K": return 45;
            case "W": return 60;
            case "P": return 50;
            case "R": return 40;
            default: return 0;
        }
    }

    public static int getSmallAngelHp(String type) {
        switch (type) {
            case "K": return 10;
            case "W": return 25;
            case "P": return 15;
            case "R": return 20;
            default: return 0;
        }
    }

    public static int getGoodBoyHp(String type) {
        switch (type) {
            case "K": return 20;
            case "W": return 50;
            case "P": return 30;
            case "R": return 40;
            default: return 0;
        }
    }

    public static int getDarkAngelHp(String type) {
        switch (type) {
            case "K": return 40;
            case "W": return 20;
            case "P": return 30;
            case "R": return 10;
            default: return 0;
        }
    }

    public static int getSpawnerHp(String type) {
        switch (type) {
            case "K": return 200;
            case "W": return 120;
            case "P": return 150;
            case "R": return 180;
            default: return 0;
        }
    }
}
