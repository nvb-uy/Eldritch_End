package elocindev.eldritch_end.api.targeting;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class VectorHelper {
    public VectorHelper() {
    }

    public static double angleBetween(Vec3d a, Vec3d b) {
        double cosineTheta = a.dotProduct(b) / (a.length() * b.length());
        double angle = Math.acos(cosineTheta) * 57.29577951308232;
        return Double.isNaN(angle) ? 0.0 : angle;
    }

    public static double angleWithSignBetween(Vec3d a, Vec3d b, Vec3d planeNormal) {
        double cosineTheta = a.dotProduct(b) / (a.length() * b.length());
        double angle = Math.toDegrees(Math.acos(cosineTheta));
        Vec3d cross = a.crossProduct(b);
        angle *= Math.signum(cross.dotProduct(planeNormal));
        return Double.isNaN(angle) ? 0.0 : angle;
    }

    public static Vec3d distanceVector(Vec3d point, Box box) {
        double dx = 0.0;
        if (box.minX > point.x) {
            dx = box.minX - point.x;
        } else if (box.maxX < point.x) {
            dx = box.maxX - point.x;
        }

        double dy = 0.0;
        if (box.minY > point.y) {
            dy = box.minY - point.y;
        } else if (box.maxY < point.y) {
            dy = box.maxY - point.y;
        }

        double dz = 0.0;
        if (box.minZ > point.z) {
            dz = box.minZ - point.z;
        } else if (box.maxZ < point.z) {
            dz = box.maxZ - point.z;
        }

        return new Vec3d(dx, dy, dz);
    }

    public static Vec3d rotateTowards(Vec3d vector, Vec3d towards, double angleToRotate) {
        if (angleToRotate == 0.0) {
            return vector;
        } else {
            Vec3d originalVector = new Vec3d(vector.x, vector.y, vector.z);
            vector = vector.normalize();
            towards = towards.normalize();
            double angleBetween = angleWithSignBetween(vector, towards, vector.crossProduct(towards));
            if (angleBetween == 0.0) {
                return originalVector;
            } else {
                Vec3d rotated;
                if (angleBetween <= angleToRotate) {
                    rotated = towards;
                } else {
                    double towardsLength = Math.sin(Math.toRadians(angleToRotate)) / Math.cos(Math.toRadians(90.0 - angleBetween + angleToRotate));
                    Vec3d v2 = towards.multiply(towardsLength);
                    rotated = vector.add(v2).normalize();
                }

                rotated = rotated.multiply(originalVector.length());
                return rotated;
            }
        }
    }

    public static Vec3d axisFromRotation(float yaw, float pitch) {
        double yawRadians = Math.toRadians((double)(-yaw));
        double pitchRadians = Math.toRadians((double)(-pitch));
        double x = -Math.sin(yawRadians) * Math.cos(pitchRadians);
        double y = -Math.sin(pitchRadians);
        double z = Math.cos(yawRadians) * Math.cos(pitchRadians);
        return (new Vec3d(x, y, z)).normalize();
    }

    public static Vec3d rotateAround(Vec3d vector, float angleDegrees, float yaw, float pitch) {
        Vec3d axisOfRotation = axisFromRotation(yaw, pitch);
        return rotateAround(vector, axisOfRotation, (double)angleDegrees);
    }

    public static Vec3d rotateAround(Vec3d vector, Vec3d axisOfRotation, double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        double sinHalfAngle = Math.sin(angleRadians / 2.0);
        double cosHalfAngle = Math.cos(angleRadians / 2.0);
        double rx = axisOfRotation.x * sinHalfAngle;
        double ry = axisOfRotation.y * sinHalfAngle;
        double rz = axisOfRotation.z * sinHalfAngle;
        double invRx = -rx;
        double invRy = -ry;
        double invRz = -rz;
        double[] q = multiplyQuaternions(new double[]{rx, ry, rz, cosHalfAngle}, new double[]{vector.x, vector.y, vector.z, 0.0});
        double[] p = multiplyQuaternions(q, new double[]{invRx, invRy, invRz, cosHalfAngle});
        return new Vec3d(p[0], p[1], p[2]);
    }

    private static double[] multiplyQuaternions(double[] q1, double[] q2) {
        double x = q1[3] * q2[0] + q1[0] * q2[3] + q1[1] * q2[2] - q1[2] * q2[1];
        double y = q1[3] * q2[1] + q1[1] * q2[3] + q1[2] * q2[0] - q1[0] * q2[2];
        double z = q1[3] * q2[2] + q1[2] * q2[3] + q1[0] * q2[1] - q1[1] * q2[0];
        double w = q1[3] * q2[3] - q1[0] * q2[0] - q1[1] * q2[1] - q1[2] * q2[2];
        return new double[]{x, y, z, w};
    }

    public static double yawFromNormalized(Vec3d vector) {
        return Math.toDegrees(Math.atan2(-vector.x, vector.z));
    }

    public static double pitchFromNormalized(Vec3d vector) {
        return Math.toDegrees(-Math.asin(vector.y));
    }
}
