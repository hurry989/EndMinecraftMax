package net.emtips.endminecraftplusplus.main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Scanner;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.InitialDirContext;

import net.emtips.endminecraftplusplus.other.ASMInject;
import net.emtips.endminecraftplusplus.Protocol.MCForgelnject;

import static net.emtips.endminecraftplusplus.utils.mainUtils.getCo;
import static net.emtips.endminecraftplusplus.utils.mainUtils.log;

public class main {
    private static String ip;
    public static int port = 25565;

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws InterruptedException, IOException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, NamingException {
        ASMInject.inject();
        MCForgelnject.inject();
        getInfo();
        showMenu();
    }

    private static void getInfo() throws NamingException {
        log("欢迎使用EndMinecraftPlus++压测程序 协议库版本:1.15.1", "",  "=======================");
        log("请输入ip地址");
        ip = scanner.nextLine();
        if (ip.contains(":")) {
            String[] tmpip = ip.split(":");
            ip = tmpip[0];
            port = Integer.parseInt(tmpip[1]);
        } else {
            log("请输入端口(25565)");
            port = getCo(scanner.nextLine(), 25565);
        }
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        hashtable.put("java.naming.provider.url", "dns:");
        try {
            Attribute qwqre = (new InitialDirContext(hashtable)).getAttributes((new StringBuilder()).append("_Minecraft._tcp.").append(ip).toString(), new String[]{"SRV"}).get("srv");
            if (qwqre != null) {
                String[] re = qwqre.get().toString().split(" ", 4);
                log("检测到SRV记录，自动跳转到SRV记录");
                ip = re[3];
                log("ip: " + ip);
                port = Integer.parseInt(re[2]);
                log("port: " + port);
            }
        } catch (Exception e) {}
    }

    private static void showMenu() throws IOException, InterruptedException {
        Menu menu = new Menu(scanner, ip, port);
        while (true) {
            log("请输入攻击方式：", "1 : MOTD攻击", "2 : 分布式假人攻击(集群压测)");
            log("========================");
            switch (getCo(scanner.nextLine(), 2)) {
                case 1:
                    menu._1();
                    return;
                case 2:
                    menu._2();
                    return;
                default:
                    log("您的选择有误，请重新选择");
            }
        }
    }
}
