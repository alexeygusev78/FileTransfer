package ru.ag78.utils.ft;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ReceiveFileTask implements Callable<Long> {

    private Socket socket;

    public ReceiveFileTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Long call() throws Exception {

        System.out.println("Receiving file...");

        try (Socket s = socket;
             InputStream is = s.getInputStream();
             BufferedInputStream bis = new BufferedInputStream(is)) {

            String filename = receiveFilename(bis);
            System.out.println("Receiving file " + filename + "...");

            long fileSize = writeFile(bis, filename);
            System.out.println("Receive file " + filename + " completed. Bytes received: " + fileSize);

            return fileSize;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private long writeFile(BufferedInputStream bis, String filename) throws Exception {

        final int BUFFER_SIZE = 0x400;
        long totalSize = 0;
        int size = 0;
        byte buffer[] = new byte[BUFFER_SIZE];

        try (FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            while ((size = bis.read(buffer, 0, BUFFER_SIZE)) > 0) {
                bos.write(buffer, 0, size);
                totalSize += size;
            }
        }
        return totalSize;
    }

    /**
     * Read filename first.
     * Reading until meet 0.
     * @param bis
     * @return
     * @throws Exception
     */
    private String receiveFilename(BufferedInputStream bis) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int ch = 0;
        while ((ch = bis.read()) > 0) {
            baos.write(ch);
        }

        return new String(baos.toByteArray(), "UTF-8");
    }
}
