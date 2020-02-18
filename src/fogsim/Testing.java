package testing;

import logical.Tuple;
import physical.FogNode;

public class Testing {
    public static void main(String[] args){
        Tuple t1 = new Tuple(1,900.0,"camera1","tv1",false);
        Tuple t2 = new Tuple(2,200.0,"camera2","tv2",false);
        Tuple t3 = new Tuple(3,300.0,"camera3","tv3",false);
        Tuple t4 = new Tuple(4,400.0,"camera4","tv4",false);
        Tuple t5 = new Tuple(5,500.0,"camera5","tv5",false);


        // low level fognode
        FogNode sS_FOG = new FogNode();
        sS_FOG.setId(1);
        sS_FOG.setName("S-FOG");
        sS_FOG.setRAM(1024);
        sS_FOG.setMIPS(1000);
        sS_FOG.setDownloadBW(10);
        sS_FOG.setUplinkBW(10);
        sS_FOG.setTuple(t1);

        // high level fognode
        FogNode suzukiFog = new FogNode();
        suzukiFog.setId(2);
        suzukiFog.setName("SUZUKI-FOG");
        suzukiFog.setRAM(2024);
        suzukiFog.setMIPS(7000);
        suzukiFog.setDownloadBW(10);
        suzukiFog.setUplinkBW(10);

        sS_FOG.sendTuple(t1,suzukiFog);

        System.out.println(suzukiFog);



    }
}
