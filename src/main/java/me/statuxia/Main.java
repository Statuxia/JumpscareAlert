package me.statuxia;

import me.statuxia.config.ConfigurationModule;
import me.statuxia.jumpscare.JumpScareImage;
import me.statuxia.jumpscare.JumpScareSound;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final ClassLoader cl = Main.class.getClassLoader();

    public static void main(String[] args) throws InterruptedException, IOException {

        ConfigurationModule module = ConfigurationModule.init();
        int[] delay = module.getDelay();
        List<JFrame> images = getImages(module.getJumpScare());
        JumpScareSound sound = new JumpScareSound(cl.getResourceAsStream(String.format("sounds/%s.wav", module.getJumpScare())));
        sound.setVolume(module.getVolume());

        System.out.printf("Настройка завершена! Скример запущен и будет появляться в период от %1$d до %2$d%n сек.",
                delay[0], delay[1]);

        while (true) {
            int i = ThreadLocalRandom.current().nextInt(1000 * delay[0], 1000 * delay[1]);
            Thread.sleep(i);
            for (JFrame image : images) {
                image.setVisible(true);
                image.setAlwaysOnTop(true);
            }
            sound.play();
            sound.join();
            for (JFrame image : images) {
                image.setVisible(false);
                image.setAlwaysOnTop(false);
            }
        }
    }

    public static List<JFrame> getImages(String filename) {
        List<JFrame> images = new ArrayList<>();
        GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        for (GraphicsDevice device : devices) {
            try {
                images.add(new JumpScareImage(cl.getResourceAsStream(String.format("images/%s.png", filename)), device));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return images;
    }
}