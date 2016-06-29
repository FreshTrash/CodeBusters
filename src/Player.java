import java.util.*;

/**
 * Send your busters out into the fog to trap ghosts and bring them home!
 **/
class Player {
    public static final int GHOST_TYPE = -1;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int bustersPerPlayer = in.nextInt(); // the amount of busters you control
        int ghostCount = in.nextInt(); // the amount of ghosts on the map
        int myTeamId = in.nextInt(); // if this is 0, your base is on the top left of the map, if it is one, on the bottom right
        System.err.println(myTeamId);
        Map<Integer, Entity> myTeam = new HashMap<>();

        // game loop
        while (true) {
            Map<Integer, Entity> ghosts = new HashMap<>();

            int entities = in.nextInt(); // the number of busters and ghosts visible to you
            for (int i = 0; i < entities; i++) {
                Entity e = Entity.fromScanner(in);
                if (e.entityType == myTeamId) {
                    myTeam.put(e.entityId, e);
                } else {
                    if (e.entityType == GHOST_TYPE) {
                        ghosts.put(e.entityId, e);
                    }
                }
            }

            printMap(myTeam);
            printMap(ghosts);

            for (int i = 0; i < bustersPerPlayer; i++) {

                // Write an action using System.out.println()
                // To debug: System.err.println("Debug messages...");

                System.out.println("MOVE 8000 4500"); // MOVE x y | BUST id | RELEASE
            }
        }
    }

    static void printMap(Map<Integer, Entity> map) {
        map.forEach((k, v) -> System.err.println(v));
    }

    static double distance(XY from, XY to) {
        return Math.sqrt(Math.pow(to.x - from.x, 2) + Math.pow(to.y - to.x, 2));
    }
}

class XY {
    int x;
    int y;

    static XY fromScanner(Scanner in) {
        XY xy = new XY();
        xy.x = in.nextInt();
        xy.y = in.nextInt();

        return xy;
    }
}

class Entity {
    int entityId;
    XY xy;
    int entityType;
    int state;
    int value;


    public static Entity fromScanner(Scanner in) {
        Entity entity = new Entity();
        entity.entityId = in.nextInt(); // buster id or ghost id
        entity.xy = XY.fromScanner(in); // position of this buster / ghost
        entity.entityType = in.nextInt(); // the team id if it is a buster, -1 if it is a ghost.
        entity.state = in.nextInt(); // For busters: 0=idle, 1=carrying a ghost.
        entity.value = in.nextInt(); // For busters: Ghost id being carried. For ghosts: number of busters attempting to trap this ghost.
        return entity;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "entityId=" + entityId +
                ", x=" + xy.x +
                ", y=" + xy.y +
                ", entityType=" + entityType +
                ", state=" + state +
                ", value=" + value +
                '}';
    }
}