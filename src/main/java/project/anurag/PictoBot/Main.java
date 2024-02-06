package project.anurag.PictoBot;

import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.info.MessageInfo;
import it.auties.whatsapp.model.message.standard.ImageMessage;
import it.auties.whatsapp.model.message.standard.TextMessage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

// This is the main class of our bot
public class Main {
    public static void main(String... args) {
        // Create a new instance of WhatsappAPI
        Whatsapp.webBuilder().lastConnection().build().addLoggedInListener(() -> System.out.println("Connected!")).addNewMessageListener(Main::onMessage).connectAndAwait().join();
    }

    private static void onMessage(Whatsapp api, MessageInfo info) {

        try {
            if (!(info.message().content() instanceof TextMessage textMessage)) {
                return;
            }

            var text = textMessage.text();
            System.out.println(text);

            ProcessBuilder processBuilder = new ProcessBuilder("python3", "main.py", "\"" + text + "\"");
            Process process = processBuilder.start();
            try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

            }


            File file = new File("links.txt");
            Scanner data = new Scanner(file);
            var chat = info.senderJid();
            for (int i = 0; i < 4; i++) {
                String link = data.nextLine();
                var media = new URL(link).openStream().readAllBytes();
                var image = ImageMessage.simpleBuilder() // Create a new image message builder
                        .media(media) // Set the image of this message
                        .build(); // Create the message
                api.sendMessage(chat, image);


            }

        } catch (IOException ignored) {

        }


//        if (!textMessage.text()
//                .toLowerCase()
//                .contains("/ban")) {
//            return;
//        }

//        if (!info.chatJid().hasServer(ContactJid.Server.GROUP)) {
//            api.sendMessage(info.chatJid(), "[WhatsappBot] This command is only supported in groups", info);
//            return;
//        }

//        var quoted = info.quotedMessage();
//        if (quoted.isEmpty()) {
//            api.sendMessage(info.chatJid(),
//                    "[WhatsappBot] Please quote a message sent by the person that you want to ban", info);
//            return;
//        }

//        var victim = quoted.get()
//                .sender()
//                .orElse(null);
//        if (victim == null) {
//            api.sendMessage(info.chatJid(), "[WhatsappBot] Missing contact, cannot ban target", info);
//            return;
//        }
//        var jid = info.senderJid();
//        api.sendMessage(jid, "hello");
//        api.removeGroupParticipant(info.chat(), victim)
//                .thenRunAsync(() -> api.sendMessage(info.chatJid(), "[WhatsappBot] The contact was successfully banned", info));
// xyz
    }
}