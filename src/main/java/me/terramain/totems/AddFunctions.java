package me.terramain.totems;

import org.bukkit.Location;

public class AddFunctions {


    public static double getDistance(Location loc1, Location loc2){
        double xr = loc1.getX()-loc2.getX();
        double yr = loc1.getY()-loc2.getY();
        double zr = loc1.getZ()-loc2.getZ();

        double summa = (xr*xr)+(yr*yr)+(zr*zr);

        return Math.sqrt(summa);
    }

    public static Location theAngleFromAAlongTheAbscissaAxisToB(Location a, Location b){
        double Ax = a.x();
        double Ay = a.y();
        double Az = a.z();

        double Bx = b.x();
        double By = b.y();
        double Bz = b.z();



        double l1 = Math.max(Ax,Bx) - Math.min(Ax,Bx);
        double l2 = Math.max(Az,Bz) - Math.min(Az,Bz);
        double t = l2/l1;
        double yaw = ( ( Math.atan(t) ) * 180 ) / Math.PI;

        if (Bx < Ax) yaw = 180-yaw; //90+(90-yaw)
        if (Bz < Az) yaw = -yaw;




        l1 = Math.max(Ay,By) - Math.min(Ay,By);
        l2 = Math.max(Ax,Bx) - Math.min(Ax,Bx);
        t = l2/l1;
        double pitchOfx = ( ( Math.atan(t) ) * 180 ) / Math.PI;

        if (Bx < Ax) pitchOfx = 180-pitchOfx; //90+(90-yaw)
        if (By < Ay) pitchOfx = -pitchOfx;


        l1 = Math.max(Ay,By) - Math.min(Ay,By);
        l2 = Math.max(Az,Bz) - Math.min(Az,Bz);
        t = l2/l1;
        double pitchOfz = ( ( Math.atan(t) ) * 180 ) / Math.PI;

        if (Bz < Az) pitchOfz = 180-pitchOfz; //90+(90-yaw)
        if (By < Ay) pitchOfz = -pitchOfz;


        double pitch = (pitchOfx+pitchOfz)/2;

        a.setYaw( (float)yaw );
        a.setPitch( (float)pitch );
        return a;
    }
}
