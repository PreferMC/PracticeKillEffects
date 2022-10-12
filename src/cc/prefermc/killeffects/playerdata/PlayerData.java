package cc.prefermc.killeffects.playerdata;

public class PlayerData {
    private boolean killSlowly, autoDropEmptyItem, autoL, typeItemDrop, spawnBloodOnDamage, skullBloodSpawn;
    private final String player;

    public PlayerData(String player, boolean killSlowly, boolean autoDropEmptyItem, boolean autoL, boolean typeItemDrop, boolean spawnBloodOnDamage, boolean skullBloodSpawn) {
        this.killSlowly = killSlowly;
        this.autoDropEmptyItem = autoDropEmptyItem;
        this.autoL = autoL;
        this.typeItemDrop = typeItemDrop;
        this.spawnBloodOnDamage = spawnBloodOnDamage;
        this.player = player;
        this.skullBloodSpawn = skullBloodSpawn;
    }

    public boolean isKillSlowly() {
        return killSlowly;
    }

    public void setKillSlowly(boolean killSlowly) {
        this.killSlowly = killSlowly;
    }

    public boolean isAutoDropEmptyItem() {
        return autoDropEmptyItem;
    }

    public void setAutoDropEmptyItem(boolean autoDropEmptyItem) {
        this.autoDropEmptyItem = autoDropEmptyItem;
    }

    public boolean isAutoL() {
        return autoL;
    }

    public void setAutoL(boolean autoL) {
        this.autoL = autoL;
    }

    public boolean isTypeItemDrop() {
        return typeItemDrop;
    }

    public void setTypeItemDrop(boolean typeItemDrop) {
        this.typeItemDrop = typeItemDrop;
    }

    public String getPlayer() {
        return player;
    }

    public boolean isSpawnBloodOnDamage() {
        return spawnBloodOnDamage;
    }

    public void setSpawnBloodOnDamage(boolean spawnBloodOnDamage) {
        this.spawnBloodOnDamage = spawnBloodOnDamage;
    }

    public boolean isSkullBloodSpawn() {
        return skullBloodSpawn;
    }

    public void setSkullBloodSpawn(boolean skullBloodSpawn) {
        this.skullBloodSpawn = skullBloodSpawn;
    }
}
