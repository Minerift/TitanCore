package org.vexar.titan.modules.auction.util;

import org.avaeriandev.api.util.ByteDirection;

import java.util.Arrays;
import java.util.List;

public enum AuctionWard {

    E(
        Arrays.asList(
            new AuctionSection(ByteDirection.WEST, 195, 1299, 195, 1327, 35, 2, 8),
            new AuctionSection(ByteDirection.EAST, 183, 1299, 183, 1327, 35, 2, 8),
            new AuctionSection(ByteDirection.NORTH, 185, 1329, 193, 1329, 35, 2, 8)
        ),
        "Eah"
    ),

    D(
        Arrays.asList(
            new AuctionSection(ByteDirection.NORTH, 327, 1319, 355, 1319, 33, 2, 8),
            new AuctionSection(ByteDirection.SOUTH, 327, 1307, 355, 1307, 33, 2, 8),
            new AuctionSection(ByteDirection.WEST, 357, 1309, 357, 1317, 33, 2, 8)
        ),
        "Dah"
    ),

    C(
        Arrays.asList(
            new AuctionSection(ByteDirection.WEST, 319, 1170, 319, 1198, 33, 2, 8),
            new AuctionSection(ByteDirection.EAST, 307, 1170, 307, 1198, 33, 2, 8),
            new AuctionSection(ByteDirection.SOUTH, 309, 1168, 317, 1168, 33, 2, 8)
        ),
        "Cah"
    ),

    B(
        Arrays.asList(
            new AuctionSection(ByteDirection.EAST, 114, 1134, 114, 1162, 38, 2, 8),
            new AuctionSection(ByteDirection.WEST, 126, 1134, 126, 1152, 38, 2, 8),
            new AuctionSection(ByteDirection.SOUTH, 116, 1132, 124, 1132, 38, 2, 8)
        ),
        "Bah"
    ),

    A(
        Arrays.asList(
            new AuctionSection(ByteDirection.WEST, 248, 1071, 248, 1079, 53, 4, 2),
            new AuctionSection(ByteDirection.EAST, 236, 1071, 236, 1079, 53, 4, 2)
        ),
        "Aah"
    );

    private List<AuctionSection> positions;
    private String regionName;
    private AuctionWard(List<AuctionSection> positions, String regionName) {
        this.positions = positions;
        this.regionName = regionName;
    }

    public List<AuctionSection> getPositions() {
        return positions;
    }
    public String getRegionName() {
        return regionName;
    }

}
