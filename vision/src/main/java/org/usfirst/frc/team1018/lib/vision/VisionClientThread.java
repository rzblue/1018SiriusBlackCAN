package org.usfirst.frc.team1018.lib.vision;

/**
 * @author Ryan Blue
 */
public class VisionClientThread extends Thread {
    VisionClient client;
    VisionProvider provider;

    public VisionClientThread(VisionClient client, VisionProvider provider) {
        this.client = client;
        this.provider = provider;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            client.putDataPacket(provider.getLatestData());
        }
    }
}
