package org.avaeriandev.api.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.bukkit.util.Vector;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VectorSerializable {

    private double x;
    private double y;
    private double z;

    private VectorSerializable() {}

    public VectorSerializable(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public VectorSerializable(Vector vector) {
        this(vector.getX(), vector.getY(), vector.getZ());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector toBukkitVector() {
        return new Vector(x, y, z);
    }
}
