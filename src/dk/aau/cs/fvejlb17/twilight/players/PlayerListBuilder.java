package dk.aau.cs.fvejlb17.twilight.players;

public class PlayerListBuilder {

    private final PlayerList playerList = new PlayerList();

    public PlayerListBuilder addPlayer(Player player) {
        this.playerList.add(player);
        return this;
    }

    public PlayerList build() {
        return playerList;
    }
}
