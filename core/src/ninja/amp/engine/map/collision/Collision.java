package ninja.amp.engine.map.collision;

public enum Collision {
    EMPTY,
    SOLID,
    LADDER,
    PLATFORM;

    public static Collision fromOrdinal(int ordinal) {
        return Collision.values()[ordinal];
    }

}
