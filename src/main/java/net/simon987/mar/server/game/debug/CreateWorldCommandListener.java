package net.simon987.mar.server.game.debug;

import net.simon987.mar.server.GameServer;
import net.simon987.mar.server.event.DebugCommandEvent;
import net.simon987.mar.server.event.GameEvent;
import net.simon987.mar.server.event.GameEventListener;
import net.simon987.mar.server.game.world.World;

public class CreateWorldCommandListener implements GameEventListener {

    @Override
    public Class getListenedEventType() {
        return DebugCommandEvent.class;
    }

    @Override
    public void handle(GameEvent event) {

        DebugCommandEvent e = (DebugCommandEvent) event;

        if (e.getName().equals("createWorld")) {

            World world = GameServer.INSTANCE.getUniverse().getWorld(e.getInt("worldX"), e.getInt("worldY"),
                    true, e.getString("dimension"));

            if (world != null) {

                e.reply("Success");

            } else {
                e.reply("Couldn't create world");
            }
        }
    }

}
