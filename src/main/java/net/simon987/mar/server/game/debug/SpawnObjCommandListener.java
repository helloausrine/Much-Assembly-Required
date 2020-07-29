package net.simon987.mar.server.game.debug;

import net.simon987.mar.server.GameServer;
import net.simon987.mar.server.event.DebugCommandEvent;
import net.simon987.mar.server.event.GameEvent;
import net.simon987.mar.server.event.GameEventListener;
import net.simon987.mar.server.game.objects.GameObject;
import net.simon987.mar.server.game.world.World;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;

public class SpawnObjCommandListener implements GameEventListener {

    @Override
    public Class getListenedEventType() {
        return DebugCommandEvent.class;
    }

    @Override
    public void handle(GameEvent event) {

        DebugCommandEvent e = (DebugCommandEvent) event;
        if (e.getName().equals("spawnObj")) {

            try {
                World world = GameServer.INSTANCE.getUniverse().getWorld(e.getInt("worldX"), e.getInt("worldY"),
                        false, e.getString("dimension"));

                Document dbObj = Document.parse(e.getString("data"));
                dbObj.put("id", new ObjectId());

                GameObject object = GameServer.INSTANCE.getRegistry().deserializeGameObject(dbObj);

                if (object != null) {
                    world.addObject(object);
                    object.setWorld(world);

                    object.initialize();

                    e.reply("Created object " + object.getObjectId());

                } else {
                    e.reply("Couldn't deserialise the object");
                }

            } catch (Exception ex) {
                String message = ex.getMessage();
                message += "\n " + Arrays.toString(ex.getStackTrace()).replaceAll(", ", "\n");
                e.reply(message);
            }
        }

    }
}
