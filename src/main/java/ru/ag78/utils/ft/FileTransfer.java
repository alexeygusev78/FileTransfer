package ru.ag78.utils.ft;

import org.apache.commons.cli.Options;
import ru.ag78.api.helpers.OptionsHelper;
import ru.ag78.api.helpers.OptionsInitializer;

/**
 * Entry-point of FileTransfer utility.
 */
public class FileTransfer implements OptionsInitializer {

    private OptionsHelper helper;

    public static class Opts {
        public static final String FILE = "file";
        public static final String ADDRESS = "address";
        public static final String SEND = "send";
        public static final String RECEIVE = "receive";
    }

    public static void main(String[] args) {

        try {
            System.out.println("FileTransfer is here...");
            new FileTransfer().start(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void start(String[] args) throws Exception {

        helper = new OptionsHelper(args, this);
        if (helper.isHelp()) {
            showHelp();
            return;
        }

        if (helper.isOption(Opts.SEND)) {
            new FileSender().send(helper);
            return;
        }

        if (helper.isOption(Opts.RECEIVE)) {
            new FileReceiver().receive(helper);
        }
    }

    @Override
    public void initOptions(Options opt) {

        opt.addOption(Opts.SEND, false, "Sends file");
        opt.addOption(Opts.RECEIVE, false, "Receives file");
        opt.addOption(Opts.FILE, true, "Set file name for receive or send");
        opt.addOption(Opts.ADDRESS, true, "Set target or accept port address for send file. Example: localhost:9000 for sending, :9000 for receiving.");
    }

    private void showHelp() {

        helper.showHelp("[OPTIONS] [FILES]", "FileTransfer utiltity", "Alexey Gusev 2019");
    }
}
