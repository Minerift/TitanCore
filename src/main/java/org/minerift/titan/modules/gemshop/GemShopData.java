package org.minerift.titan.modules.gemshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.minerift.titan.TitanPlugin;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GemShopData {

    @JsonIgnore
    private GemShopModule MODULE = TitanPlugin.getModule(GemShopModule.class);

    private int version = 0;
    private GemShopOffer[] slots = new GemShopOffer[MODULE.getSlotCount()];
    private long expireTimestamp;

    GemShopData() {}

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public GemShopOffer[] getSlots() {
        return slots;
    }

    public void setSlots(GemShopOffer[] slots) {
        this.slots = slots;
    }

    public long getExpireTimestamp() {
        return expireTimestamp;
    }

    public void setExpireTimestamp(long expireTimestamp) {
        this.expireTimestamp = expireTimestamp;
    }
}
