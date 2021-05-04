package org.minerift.titan.modules.gemshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.minerift.titan.TitanPlugin;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GemShopData {

    @JsonIgnore
    private GemShopModule MODULE = TitanPlugin.getModule(GemShopModule.class);

    private int version = 0;
    private GemShopOffer[] slots = selectNewOffers();
    private long expireTimestamp = nextExpireTimestamp();

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

    public long nextExpireTimestamp() {
        Calendar newExpireTimestamp = Calendar.getInstance();
        newExpireTimestamp.setTime(new Date());
        newExpireTimestamp.add(Calendar.HOUR_OF_DAY, 24);

       return newExpireTimestamp.getTimeInMillis();
    }

    public GemShopOffer[] selectNewOffers() {
        GemShopOffer[] newOffers = new GemShopOffer[MODULE.getSlotCount()];
        for(int i = 0; i < newOffers.length; i++) {
            newOffers[i] = GemShopOffer.random();
        }
        return newOffers;
    }
}
