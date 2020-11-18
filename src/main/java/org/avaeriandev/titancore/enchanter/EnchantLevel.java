package org.avaeriandev.titancore.enchanter;

public enum EnchantLevel {
    L1_L6   (1, 3, 6),
    L7_L12  (7, 9, 12),
    L13_L18 (13, 15, 18),
    L19_L24 (19, 21, 24),
    L25_L30 (25, 27, 30);

    private int[] levels = new int[3];
    EnchantLevel(int firstLevel, int secondLevel, int thirdLevel) {
        this.levels[0] = firstLevel;
        this.levels[1] = secondLevel;
        this.levels[2] = thirdLevel;
    }

    public int getLevel(int index) {
        return levels[index];
    }
}
