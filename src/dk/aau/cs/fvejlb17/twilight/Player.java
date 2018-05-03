package dk.aau.cs.fvejlb17.twilight;

import java.util.Objects;

public class Player {

    private String name;
    private String race;
    private String colour;

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
