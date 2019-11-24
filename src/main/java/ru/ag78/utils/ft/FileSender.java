package ru.ag78.utils.ft;

import ru.ag78.api.helpers.OptionsHelper;
import ru.ag78.api.utils.SafeArrays;
import ru.ag78.api.utils.SafeTypes;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSender {

    public void send(OptionsHelper opts) {

        String file = opts.getOption(FileTransfer.Opts.FILE);
        String address = opts.getOption(FileTransfer.Opts.ADDRESS);

        System.out.println(String.format("Sending file %s to %s", file, address));

        try (Socket socket = connect(address);
             OutputStream os = socket.getOutputStream();
             BufferedOutputStream bos = new BufferedOutputStream(os)) {

            sendFile(bos, file);
        } catch (Exception e) {
            throw new FileTransferException("Failed to send file", e);
        }
    }

    private void sendFile(BufferedOutputStream bos, String file) throws Exception {

        Path path = Paths.get(file);

        OutputStreamWriter pw = new OutputStreamWriter(bos, "UTF-8");
        pw.write(path.getFileName().toString());
        pw.flush();
        bos.write(0);

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);) {
            final int BUFFER_SIZE = 0x400;
            int size = 0;
            byte buffer[] = new byte[BUFFER_SIZE];

            while ((size = bis.read(buffer, 0, BUFFER_SIZE)) > 0) {
                bos.write(buffer, 0, size);
            }
            bos.flush();
        }
    }

    private Socket connect(String address) throws Exception {

        String[] tokens = address.split(":");
        String host = SafeArrays.getSafeItem(tokens, 0, "unkonwn");
        int port = SafeTypes.parseSafeInt(SafeArrays.getSafeItem(tokens, 1), 0);

        return new Socket(host, port);
    }
}
