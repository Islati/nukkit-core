package com.levelmc.core.api.player;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.item.Item;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.PlayerSkinPacket;
import cn.nukkit.utils.SerializedImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PlayerUtils {

    public static Item getItemInHand(Player player, HandSlot slot) {
        Item item = null;

        if (slot == HandSlot.OFF_HAND) {
            item = player.getOffhandInventory().getItem(0);
        } else {
            item = player.getInventory().getItemInHand();
        }

        return item;
    }

    public static boolean handIsEmpty(Player player, HandSlot slot) {
        Item item = getItemInHand(player,slot);
        return item != null && item.getId() != Item.AIR;
    }

    /*
    public static void setCape(final Player p) {
        Skin s = p.getSkin();
        try {
            BufferedImage si = ImageIO.read(new File(String.valueOf(Server.getInstance().getPluginPath()) + "/LobbyNK", "example.png"));
            BufferedImage sk = ImageIO.read(new File(String.valueOf(Server.getInstance().getPluginPath()) + "/LobbyNK", "skin.png"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(si, "png", baos);
            baos.flush();
            byte[] bts = baos.toByteArray();
            baos.close();
            SerializedImage img = new SerializedImage(200, 150, bts);
            s.setSkinData(sk);
            s.setCapeOnClassic(true);
            s.setCapeId("example");
            s.setCapeData(img);
            s.setPremium(true);
            Skin os = p.getSkin();
            p.setSkin(s);
            PlayerSkinPacket psp = new PlayerSkinPacket();
            psp.skin = s;
            psp.oldSkinName = os.getSkinId();
            psp.newSkinName = s.getSkinId();
            psp.uuid = p.getUniqueId();
            System.out.print(psp);
            Server.broadcastPacket(Server.getInstance().getOnlinePlayers().values(), (DataPacket)psp);
            p.hidePlayer(p);
            Server.getInstance().getScheduler().scheduleDelayedTask(new Runnable() {
                public void run() {
                    p.showPlayer(p);
                }
            },  15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */
}
