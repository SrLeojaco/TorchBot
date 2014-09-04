package me.woder.network;

import java.io.IOException;

import me.woder.bot.Client;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
public class KeepAlive00 extends Packet{
    public KeepAlive00(Client c) {
        super(c);
    }
    
    @Override
    public void read(Client c, int len, ByteArrayDataInputWrapper buf) throws IOException{
        int temp = buf.readInt();
        ByteArrayDataOutput buff = ByteStreams.newDataOutput();
        writeVarInt(buff, 0);
        buff.writeInt(temp);
        c.net.sendPacket(buff, c.out);
    }

}
