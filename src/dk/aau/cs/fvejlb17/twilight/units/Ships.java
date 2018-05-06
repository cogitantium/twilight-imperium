package dk.aau.cs.fvejlb17.twilight.units;

import dk.aau.cs.fvejlb17.twilight.players.Player;

public abstract class Ships implements Units {

    private final Player owner;
    private final int resourceCost;
    private final int combatValue;
    private final int movementSpeed;
    private final int capacity;

    Ships(Player owner, int resourceCost, int combatValue, int movementSpeed, int capacity) {
        this.owner = owner;
        this.resourceCost = resourceCost;
        this.combatValue = combatValue;
        this.movementSpeed = movementSpeed;
        this.capacity = capacity;
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
}
