package dk.aau.cs.fvejlb17.twilight.units;

import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.systems.SystemPosition;

import java.util.Objects;

public abstract class Ships implements Units {

    private final Player owner;
    private final int resourceCost;
    private final int combatValue;
    private final int movementSpeed;
    private final int capacity;
    private SystemPosition systemPosition;

    Ships(Player owner, int resourceCost, int combatValue, int movementSpeed, int capacity) {
        this.owner = owner;
        this.resourceCost = resourceCost;
        this.combatValue = combatValue;
        this.movementSpeed = movementSpeed;
        this.capacity = capacity;
    }

    public SystemPosition getSystemPosition() {
        return systemPosition;
    }

    public void setSystemPosition(SystemPosition systemPosition) {
        this.systemPosition = systemPosition;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getResourceCost() {
        return resourceCost;
    }

    @Override
    public int getCombatValue() {
        return combatValue;
    }

    @Override
    public int getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ships ships = (Ships) o;
        return getResourceCost() == ships.getResourceCost() &&
                getCombatValue() == ships.getCombatValue() &&
                getMovementSpeed() == ships.getMovementSpeed() &&
                getCapacity() == ships.getCapacity() &&
                Objects.equals(getOwner(), ships.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwner(), getResourceCost(), getCombatValue(), getMovementSpeed(), getCapacity());
    }
}
