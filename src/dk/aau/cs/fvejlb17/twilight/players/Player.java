package dk.aau.cs.fvejlb17.twilight.players;

import java.util.Objects;

//TODO numPlayers must be at least 2 and at most 6, colour and race must be unique
public class Player {

    private final String name;
    private final String race;
    private final String colour;

    public Player(String name, String race, String colour) {
        this.name = name;
        this.race = race;
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(race, player.race) &&
                Objects.equals(colour, player.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, race, colour);
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
