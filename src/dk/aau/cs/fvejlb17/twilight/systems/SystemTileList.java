package dk.aau.cs.fvejlb17.twilight.systems;

import dk.aau.cs.fvejlb17.twilight.units.Ships;

import java.util.ArrayList;

public class SystemTileList extends ArrayList<SystemTile> {

    public boolean moveShip(Ships ship, SystemTile sourceSystemTile, SystemTile destSystemTile) {
        //if ships movementSpeed is greater than one, it can reach all tiles with one move
        if (ship.getMovementSpeed() > 1) {
            //add ship to destination SystemTile and remove from source, utilising short-circuiting to avoid phantom ship or missing ship
            return destSystemTile.shipEnterSystemTile(ship) && sourceSystemTile.shipLeaveSystemTile(ship);

            //if sourceSystemTiles neighbours contains destSystemTiles position and ship has a movementSpeed of one move is legal,
            //ship is then moved and if both methods return true, move has successfully occurred, utilises same short-circuiting as above
        } else return sourceSystemTile.getNeighbourSystemTiles().contains(destSystemTile.getSystemPosition())
                && ship.getMovementSpeed() == 1 && destSystemTile.shipEnterSystemTile(ship) && sourceSystemTile.shipLeaveSystemTile(ship);
    }
}
