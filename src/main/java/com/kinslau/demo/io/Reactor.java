package com.kinslau.demo.io;

import java.net.ServerSocket;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

public class Reactor implements Runnable {

    Selector selector;
    ServerSocketChannel serverSocket;

    Reactor() throws ClosedChannelException {
        SelectionKey sk = serverSocket.register(selector,SelectionKey.OP_ACCEPT);
        sk.attach(new AcceptorHandler());
    }

    @Override
    public void run() {
        while (true){
           Set set =selector.keys();

        }
    }

    class AcceptorHandler implements Runnable{


        @Override
        public void run() {

        }
    }
}
