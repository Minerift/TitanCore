package org.avaeriandev.api.util;

public enum ByteDirection {

    NORTH ((byte) 2),
    SOUTH ((byte) 3),
    WEST  ((byte) 4),
    EAST  ((byte) 5);

    private byte byteId;
    private ByteDirection(byte byteId) {
        this.byteId = byteId;
    }

    public byte getByteId() {
        return byteId;
    }

}
