package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.units.*;

public class TwilightImperium {

    public static void main(String[] args) {

        Player player01 = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player player02 = new Player("Pompey", "Federation of Sol", "Red");

        //player01/blue ships
        Dreadnought dreadnought01 = new Dreadnought(player01);
        Dreadnought dreadnought02 = new Dreadnought(player01);
        Destroyer destroyer01 = new Destroyer(player01);

        //create and add player01 ships to UnitList
        UnitList unitList01 = new UnitList();
        unitList01.add(dreadnought01); unitList01.add(dreadnought02); unitList01.add(destroyer01);

        //player02/red ships
        Cruiser cruiser01 = new Cruiser(player02);
        Cruiser cruiser02 = new Cruiser(player02);
        Carrier carrier01 = new Carrier(player02);

        //create and add player02 ships to UnitList
        UnitList unitList02 = new UnitList();
        unitList02.add(cruiser01); unitList02.add(cruiser02); unitList02.add(carrier01);

        //creating planets with sensible resource production
        Planet mecatolRex = new Planet("Mecatol Rex", 6);
        Planet vegaMinor = new Planet("Vega Minor", 2);
        Planet vegaMajor = new Planet("Vega Major", 5);
        Planet industrex= new Planet("Industrex", 6);
        Planet rigelOne= new Planet("Rigel I", 2);
        Planet rigelTwo= new Planet("Rigel II", 4);
        Planet mirage= new Planet("Mirage", 3);

    }
}
