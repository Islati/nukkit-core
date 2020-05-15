package com.levelmc.core.api.world;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import com.levelmc.core.api.player.PlayerUtils;
import com.levelmc.core.api.utils.NumberUtil;

import java.util.*;


public class LocationUtils {

    private static final double TRUE_CIRLE = 0.5;
    private static final int FALSE_CIRCLE = 0;

    public static boolean USE_TRUE_CIRCLE = false;

    public static Location getRandomLocationInArea(Location loc1, Location loc2) {
        if (!loc1.getLevel().getName().equals(loc2.getLevel().getName())) {
            return null;
        }

        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(loc1.getY(), loc2.getY());
        double minZ = Math.min(loc1.getZ(), loc2.getZ());

        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(loc1.getY(), loc2.getY());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        return new Location(NumberUtil.randomDouble(minX, maxX), NumberUtil.randomDouble(minY, maxY), NumberUtil.randomDouble(minZ, maxZ), loc1.getLevel());
    }

    public static boolean isInsideArea(Location loc, Location areaPointA, Location areaPointB, boolean checkY) {
        String mainLocWorldName = loc.getLevel().getName();
        //If they're not all in the same world then we don't check.
        if (!mainLocWorldName.equals(areaPointA.getLevel().getName()) || !mainLocWorldName.equals(areaPointB.getLevel().getName())) {
            return false;
        }

        //Check the X Coordinate.
        if ((loc.getX() >= areaPointA.getX() && loc.getX() <= areaPointB.getX()) || (loc.getX() <= areaPointA.getX() && loc.getX() >= areaPointB.getX())) {
            //Check the Z
            if ((loc.getZ() >= areaPointA.getZ() && loc.getZ() <= areaPointB.getZ()) || (loc.getZ() <= areaPointA.getZ() && loc.getZ() >= areaPointB.getZ())) {

                /* If we're not checking the y coordinate then it's inside the loc */
                if (!checkY) {
                    return true;
                }

                /* Otherwise return the results from the final check as the result. */
                return ((loc.getY() >= areaPointA.getY() && loc.getY() <= areaPointB.getY()) || (loc.getY() <= areaPointA.getY() && loc.getY() >= areaPointB.getY()));

            }
        }
        return false;
    }

    public static Set<Player> getPlayersInRadius(Location location, double radius) {
        Set<Player> playerInRadius = new HashSet<>();
        Collection<Player> onlinePlayers = Server.getInstance().getOnlinePlayers().values();
        double radiusSquared = radius * radius;
        Level centerWorld = location.getLevel();
        for (Player onlinePlayer : onlinePlayers) {
            Location playerLoc = onlinePlayer.getLocation();

            if (!playerLoc.getLevel().equals(centerWorld)) {
                continue;
            }

            if (playerLoc.distanceSquared(location) <= radiusSquared) {
                playerInRadius.add(onlinePlayer);
            }
        }
        return playerInRadius;
    }

    public static boolean isPlayerInRadius(Location location, double radius, Player player) {
        return isEntityInRadius(location, radius, player);
    }

    public static boolean isEntityInRadius(Location center, double radius, Entity entity) {
        return isInRadius(center, entity.getLocation(), radius);
    }

    public static boolean isInRadius(Location center, Location loc, double radius) {
        /*
		If the world of the 2 locations isn't the same,
		then they're clearly not in the radius!
		 */
        if (!loc.getLevel().equals(center.getLevel())) {
            return false;
        }

        return center.distanceSquared(loc) <= (radius * radius);
    }

    public static Location getRandomLocation(Location locationCenter, double radius) {
        Random rand = new Random();
        double angle = rand.nextDouble() * 360; //Generate a random angle
        double x = locationCenter.getX() + (rand.nextDouble() * radius * Math.cos(Math.toRadians(angle)));
        double z = locationCenter.getZ() + (rand.nextDouble() * radius * Math.sin(Math.toRadians(angle)));
        double y = locationCenter.getLevel().getHighestBlockAt((int) x, (int) z);
        return new Location(x, y, z, locationCenter.getLevel());
    }

    public static int[] getXYZ(Location location) {
        int x = (int) location.getX();
        int y = (int) location.getY();
        int z = (int) location.getZ();
        return new int[]{x, y, z};
    }

    public static Location getLocation(double x, double y, double z, Level level) {
        return new Location(x, y, z, level);
    }

    public static Location getLocation(double x, double y, double z, String worldName) {
        return new Location(x, y, z, Server.getInstance().getLevelByName(worldName));
    }

    public static Location getNormalizedLocation(Location location) {
        return getLocation(location.getX(), location.getY() + 1, location.getZ(), location.getLevel());
    }

    public static Location getRoundedCompassLocation(Location location, int round) {
        int x = location.getX();
        int z = location.getZ();
        z = Math.round(z / round) * round;
        x = Math.round(x / round) * round;
        return new Location(location.getLevel(), x, 0.0, z);
    }

    public static List<Location> getParticlesCircle(Location center, float radius, float distanceBetweenParticles) {
        List<Location> locs = new ArrayList<>();
        for (float i = 0F; i < 360F; ) {
            locs.add(new Location(center.getLevel(), center.getX() + Math.cos((double) i) / radius, center.getY(), center.getZ() + Math.sin((double) i) / radius));
            i = i + distanceBetweenParticles;
        }
        return locs;
    }

    public static List<Location> getSpiral(Location center, Float degrees, double centerRadius, float radius, float distanceBetweenParticles) {
        List<Location> locs = new ArrayList<>();
        for (float i = 0F; i < degrees; ) {
            locs.add(new Location(center.getLevel(), center.getX() + Math.sin((double) i) / radius, center.getY() + i / centerRadius, center.getZ() + Math.cos((double) i) / radius));
            i = i + distanceBetweenParticles;
        }
        return locs;
    }


    /**
     * Get a full circle (not just the parameter) around the radius!
     *
     * @param center center of the circle selection.
     * @param radius radius of the circle
     * @return a list of locations that were in the circle.
     */
    public static List<Location> getFullCircle(Location center, int radius) {
        List<Location> locs = new ArrayList<>();

        World world = center.getLevel();


        double circleSize = USE_TRUE_CIRCLE ? TRUE_CIRLE : FALSE_CIRCLE;
        final double radiusSquared = (radius + circleSize) * (radius * circleSize);

        final Vector centerPoint = center.toVector();
        final Vector currentPoint = centerPoint.clone();


        for (int x = -radius; x <= radius; x++) {
            currentPoint.setX(centerPoint.getX() + x);

            for (int z = -radius; z <= radius; z++) {
                currentPoint.setZ(centerPoint.getZ() + z);

                //If the point is within the bounds of the radius, then it's part of the circle!
                if (centerPoint.distanceSquared(currentPoint) <= radiusSquared) {
                    locs.add(currentPoint.toLocation(world));
                }
            }
        }

        return locs;
    }

    /**
     * Returns a list of all the blocks in a circle within a certain radius of a location.
     * <p>
     * <p>Author: ArthurMaker</p>
     *
     * @param centerLoc center Location
     * @param radius    radius of the circle
     * @return list of blocks
     */
    public static List<Location> getCircle(Location centerLoc, int radius) {
        List<Location> circle = new ArrayList<>();
        World world = centerLoc.getLevel();
        int x = 0;
        int z = radius;
        int error = 0;
        int d = 2 - 2 * radius;
        while (z >= 0) {
            circle.add(new Location(world, centerLoc.getX() + x, centerLoc.getY(), centerLoc.getZ() + z));
            circle.add(new Location(world, centerLoc.getX() - x, centerLoc.getY(), centerLoc.getZ() + z));
            circle.add(new Location(world, centerLoc.getX() - x, centerLoc.getY(), centerLoc.getZ() - z));
            circle.add(new Location(world, centerLoc.getX() + x, centerLoc.getY(), centerLoc.getZ() - z));
            error = 2 * (d + z) - 1;
            if ((d < 0) && (error <= 0)) {
                x++;
                d += 2 * x + 1;
            } else {
                error = 2 * (d - x) - 1;
                if ((d > 0) && (error > 0)) {
                    z--;
                    d += 1 - 2 * z;
                } else {
                    x++;
                    d += 2 * (x - z);
                    z--;
                }
            }
        }
        return circle;
    }


    public static List<Location> getPlain(Location position1, Location position2) {
        List<Location> plain = new ArrayList<>();
        if (position1 == null) {
            return plain;
        }
        if (position2 == null) {
            return plain;
        }
        for (int x = Math.min(position1.getX(), position2.getX()); x <= Math.max(position1.getX(), position2.getX()); x++) {
            for (int z = Math.min(position1.getZ(), position2.getZ()); z <= Math.max(position1.getZ(), position2.getZ()); z++) {
                plain.add(new Location(position1.getLevel(), x, position1.getY(), z));
            }
        }
        return plain;
    }

    /**
     * Returns a list of all the blocks in a diagonal line between two locations.
     * <p>
     * <p>ArthurMaker</p>
     *
     * @param position1 first position
     * @param position2 second position
     * @return list of blocks
     */
    public static List<Location> getLine(Location position1, Location position2) {
        List<Location> line = new ArrayList<>();
        int dx = Math.max(position1.getX(), position2.getX()) - Math.min(position1.getX(), position2.getX());
        int dy = Math.max(position1.getY(), position2.getY()) - Math.min(position1.getY(), position2.getY());
        int dz = Math.max(position1.getZ(), position2.getZ()) - Math.min(position1.getZ(), position2.getZ());
        int x1 = position1.getX();
        int x2 = position2.getX();
        int y1 = position1.getY();
        int y2 = position2.getY();
        int z1 = position1.getZ();
        int z2 = position2.getZ();
        int x = 0;
        int y = 0;
        int z = 0;
        int i = 0;
        int d = 1;
        switch (getHighest(dx, dy, dz)) {
            case 1:
                i = 0;
                d = 1;
                if (x1 > x2) {
                    d = -1;
                }
                x = position1.getX();
                do {
                    i++;
                    y = y1 + (x - x1) * (y2 - y1) / (x2 - x1);
                    z = z1 + (x - x1) * (z2 - z1) / (x2 - x1);
                    line.add(new Location(position1.getLevel(), x, y, z));
                    x += d;
                } while (i <= Math.max(x1, x2) - Math.min(x1, x2));
                break;
            case 2:
                i = 0;
                d = 1;
                if (y1 > y2) {
                    d = -1;
                }
                y = position1.getY();
                do {
                    i++;
                    x = x1 + (y - y1) * (x2 - x1) / (y2 - y1);
                    z = z1 + (y - y1) * (z2 - z1) / (y2 - y1);
                    line.add(new Location(position1.getLevel(), x, y, z));
                    y += d;
                } while (i <= Math.max(y1, y2) - Math.min(y1, y2));
                break;
            case 3:
                i = 0;
                d = 1;
                if (z1 > z2) {
                    d = -1;
                }
                z = position1.getZ();
                do {
                    i++;
                    y = y1 + (z - z1) * (y2 - y1) / (z2 - z1);
                    x = x1 + (z - z1) * (x2 - x1) / (z2 - z1);
                    line.add(new Location(position1.getLevel(), x, y, z));
                    z += d;
                } while (i <= Math.max(z1, z2) - Math.min(z1, z2));
        }
        return line;
    }

    private static int getHighest(int x, int y, int z) {
        if ((x >= y) && (x >= z)) {
            return 1;
        }
        if ((y >= x) && (y >= z)) {
            return 2;
        }
        return 3;
    }

    public static boolean isBehind(LivingEntity entityToCheck, LivingEntity entityBehind) {
        return Math.abs(Math.toDegrees(entityToCheck.getEyeLocation().getDirection().angle(entityBehind.getLocation().getDirection()))) < 45;
    }
}