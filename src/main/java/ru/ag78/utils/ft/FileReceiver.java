package ru.ag78.utils.ft;

import ru.ag78.api.helpers.OptionsHelper;
import ru.ag78.api.utils.SafeArrays;
import ru.ag78.api.utils.SafeTypes;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileReceiver {

    public void receive(OptionsHelper opts) {

        System.out.println(String.format("Receiving a file..."));
        try {
            startReceiver(getPort(opts));
        } catch (Exception e) {
            throw new FileTransferException("Failed to receive file", e);
        }
    }

    private void startReceiver(int port) throws Exception {

        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.submit(new ReceiveFileTask(socket));
        }

    }

    private int getPort(OptionsHelper opts) {

        String address = opts.getOption(FileTransfer.Opts.ADDRESS);
        String[] tokens = address.split(":");
        return Integer.parseInt(SafeArrays.getSafeItem(tokens, 1, "unknown_port"));
    }
}
