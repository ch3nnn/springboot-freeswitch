package cn.ch3nnn;

import org.freeswitch.esl.client.outbound.SocketClient;

public class OutboundApplication {

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            SocketClient socketClient = new SocketClient(8040, new PipelineFactory());
            socketClient.start();
        }).start();

        while (true) {
            Thread.sleep(500);
        }
    }


}
