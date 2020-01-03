package main;

public final class Constants {
    // Constants class.
    private Constants() { }
    public static final int SPLIT_LIMIT = 3;
    public static final float FLOAT_ERROR = 0.0001f;

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

    public static final float EMPOWER_K_MIN = 1 / 3f;
    public static final float EMPOWER_W_MIN = 1 / 4f;
    public static final float EMPOWER_R_MIN = 1 / 7f;
    public static float getStrategyEmpowerMin(final String type) {
        switch (type) {
            case "K": return EMPOWER_K_MIN;
            case "P":
            case "W":
                return EMPOWER_W_MIN;
            case "R": return EMPOWER_R_MIN;
            default: return 0f;
        }
    }

    public static final float EMPOWER_K_MAX = 1 / 2f;
    public static final float EMPOWER_P_MAX = 1 / 3f;
    public static final float EMPOWER_R_MAX = 1 / 5f;
    public static float getStrategyEmpowerMax(final String type) {
        switch (type) {
            case "K":
            case "W":
                return EMPOWER_K_MAX;
            case "P": return EMPOWER_P_MAX;
            case "R": return EMPOWER_R_MAX;
            default: return 0f;
        }
    }

    public static final float EMPOWER_K_HP = 1 / 5f;
    public static final float EMPOWER_W_HP = 1 / 10f;
    public static final float EMPOWER_P_HP = 1 / 4f;
    public static final float EMPOWER_R_HP = 1 / 7f;
    public static float getStrategyEmpowerHp(final String type) {
        switch (type) {
            case "K": return EMPOWER_K_HP;
            case "W": return EMPOWER_W_HP;
            case "P": return EMPOWER_P_HP;
            case "R": return EMPOWER_R_HP;
            default: return 0f;
        }
    }

    public static final float EMPOWER_K_PERCENT = 0.5f;
    public static final float EMPOWER_W_PERCENT = 0.6f;
    public static final float EMPOWER_P_PERCENT = 0.7f;
    public static final float EMPOWER_R_PERCENT = 0.4f;
    public static float getStrategyEmpowerPercent(final String type) {
        switch (type) {
            case "K": return EMPOWER_K_PERCENT;
            case "W": return EMPOWER_W_PERCENT;
            case "P": return EMPOWER_P_PERCENT;
            case "R": return EMPOWER_R_PERCENT;
            default: return 0f;
        }
    }

    public static final float HEAL_K_MAX = 1 / 3f;
    public static final float HEAL_P_MAX = 1 / 4f;
    public static final float HEAL_R_MAX = 1 / 7f;
    public static float getStrategyHealMax(final String type) {
        switch (type) {
            case "K": return HEAL_K_MAX;
            case "W":
            case "P":
                return HEAL_P_MAX;
            case "R": return HEAL_R_MAX;
            default: return 0f;
        }
    }

    public static final float HEAL_K_HP = 1 / 4f;
    public static final float HEAL_W_HP = 1 / 5f;
    public static final float HEAL_P_HP = 1 / 3f;
    public static final float HEAL_R_HP = 1 / 2f;
    public static float getStrategyHealHp(final String type) {
        switch (type) {
            case "K": return HEAL_K_HP;
            case "W": return HEAL_W_HP;
            case "P": return HEAL_P_HP;
            case "R": return HEAL_R_HP;
            default: return 0f;
        }
    }

    public static final float HEAL_W_PERCENT = 0.2f;
    public static final float HEAL_P_PERCENT = 0.3f;
    public static final float HEAL_R_PERCENT = 0.1f;
    public static float getStrategyHealPercent(final String type) {
        switch (type) {
            case "K":
            case "W":
                return HEAL_W_PERCENT;
            case "P": return HEAL_P_PERCENT;
            case "R": return HEAL_R_PERCENT;
            default: return 0f;
        }
    }

    public static final int LIFEGIVER_K_HP = 100;
    public static final int LIFEGIVER_W_HP = 120;
    public static final int LIFEGIVER_P_HP = 80;
    public static final int LIFEGIVER_R_HP = 90;
    public static int getLifeGiverHp(final String type) {
        switch (type) {
            case "K": return LIFEGIVER_K_HP;
            case "W": return LIFEGIVER_W_HP;
            case "P": return LIFEGIVER_P_HP;
            case "R": return LIFEGIVER_R_HP;
            default: return 0;
        }
    }

    public static final int DRACULA_K_HP = 60;
    public static final int DRACULA_W_HP = 20;
    public static final int DRACULA_P_HP = 40;
    public static final int DRACULA_R_HP = 35;
    public static int getDraculaHp(final String type) {
        switch (type) {
            case "K": return DRACULA_K_HP;
            case "W": return DRACULA_W_HP;
            case "P": return DRACULA_P_HP;
            case "R": return DRACULA_R_HP;
            default: return 0;
        }
    }

    public static final int XPANGEL_K_XP = 45;
    public static final int XPANGEL_W_XP = 60;
    public static final int XPANGEL_P_XP = 50;
    public static final int XPANGEL_R_XP = 40;
    public static int getXpAngelXp(final String type) {
        switch (type) {
            case "K": return XPANGEL_K_XP;
            case "W": return XPANGEL_W_XP;
            case "P": return XPANGEL_P_XP;
            case "R": return XPANGEL_R_XP;
            default: return 0;
        }
    }

    public static final int SMALLANGEL_K_HP = 10;
    public static final int SMALLANGEL_W_HP = 25;
    public static final int SMALLANGEL_P_HP = 15;
    public static final int SMALLANGEL_R_HP = 20;
    public static int getSmallAngelHp(final String type) {
        switch (type) {
            case "K": return SMALLANGEL_K_HP;
            case "W": return SMALLANGEL_W_HP;
            case "P": return SMALLANGEL_P_HP;
            case "R": return SMALLANGEL_R_HP;
            default: return 0;
        }
    }

    public static final int GOODBOY_K_HP = 20;
    public static final int GOODBOY_W_HP = 50;
    public static final int GOODBOY_P_HP = 30;
    public static final int GOODBOY_R_HP = 40;
    public static int getGoodBoyHp(final String type) {
        switch (type) {
            case "K": return GOODBOY_K_HP;
            case "W": return GOODBOY_W_HP;
            case "P": return GOODBOY_P_HP;
            case "R": return GOODBOY_R_HP;
            default: return 0;
        }
    }

    public static final int DARKANGEL_K_HP = 40;
    public static final int DARKANGEL_W_HP = 20;
    public static final int DARKANGEL_P_HP = 30;
    public static final int DARKANGEL_R_HP = 10;
    public static int getDarkAngelHp(final String type) {
        switch (type) {
            case "K": return DARKANGEL_K_HP;
            case "W": return DARKANGEL_W_HP;
            case "P": return DARKANGEL_P_HP;
            case "R": return DARKANGEL_R_HP;
            default: return 0;
        }
    }

    public static final int SPAWNER_K_HP = 200;
    public static final int SPAWNER_W_HP = 120;
    public static final int SPAWNER_P_HP = 150;
    public static final int SPAWNER_R_HP = 180;
    public static int getSpawnerHp(final String type) {
        switch (type) {
            case "K": return SPAWNER_K_HP;
            case "W": return SPAWNER_W_HP;
            case "P": return SPAWNER_P_HP;
            case "R": return SPAWNER_R_HP;
            default: return 0;
        }
    }
}
