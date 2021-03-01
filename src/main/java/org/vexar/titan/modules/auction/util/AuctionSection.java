package org.vexar.titan.modules.auction.util;

import org.avaeriandev.api.util.ByteDirection;

public class AuctionSection {

    private ByteDirection chestDirection;

    private int minX;
    private int minZ;

    private int maxX;
    private int maxZ;

    private int initialY;
    private int offsetY;
    private int rowCount;

    public AuctionSection(ByteDirection chestDirection, int minX, int minZ, int maxX, int maxZ, int initialY, int offsetY, int rowCount) {
        this.chestDirection = chestDirection;
        this.minX = minX;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxZ = maxZ;
        this.initialY = initialY;
        this.offsetY = offsetY;
        this.rowCount = rowCount;
    }

    public ByteDirection getChestDirection() {
        return chestDirection;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinZ() {
        return minZ;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public int getInitialY() {
        return initialY;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getRowCount() {
        return rowCount;
    }
}
