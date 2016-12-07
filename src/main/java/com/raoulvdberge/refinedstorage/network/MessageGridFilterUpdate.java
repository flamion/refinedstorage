package com.raoulvdberge.refinedstorage.network;

import com.raoulvdberge.refinedstorage.container.ContainerGridFilter;
import com.raoulvdberge.refinedstorage.item.ItemGridFilter;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageGridFilterUpdate extends MessageHandlerPlayerToServer<MessageGridFilterUpdate> implements IMessage {
    private int compare;
    private int mode;

    public MessageGridFilterUpdate() {
    }

    public MessageGridFilterUpdate(int compare, int mode) {
        this.compare = compare;
        this.mode = mode;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        compare = buf.readInt();
        mode = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(compare);
        buf.writeInt(mode);
    }

    @Override
    public void handle(MessageGridFilterUpdate message, EntityPlayerMP player) {
        if (player.openContainer instanceof ContainerGridFilter) {
            ItemGridFilter.setCompare(((ContainerGridFilter) player.openContainer).getStack(), message.compare);
            ItemGridFilter.setMode(((ContainerGridFilter) player.openContainer).getStack(), message.mode);
        }
    }
}
