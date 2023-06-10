package me.statuxia.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigurationModule {

    private final float volume;
    private final int[] delay = new int[2];

    private final String jumpScare;

    private ConfigurationModule() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] delayText = new String[]{"минимальное", "максимальное"};
        int min = 0;
        int readedDelay;
        for (int i = 0; i < delayText.length; i++) {
            do {
                System.out.printf("Введите %1$s значение задержки (в секундах) между скримерами от %2$d и до %3$d: ",
                        delayText[i], min, Integer.MAX_VALUE);
            } while (min > (readedDelay = Integer.parseInt(reader.readLine())));
            min = readedDelay + 1;
            delay[i] = readedDelay;
        }

        float readedVolume;
        do {
            System.out.print("Введите громкость скримера от 0.0 до 1.0: ");
        } while ((readedVolume = Float.parseFloat(reader.readLine())) < 0.0f || readedVolume > 1.0f);
        volume = readedVolume;

        String[] jumpScareTypes = new String[]{"jerry", "obunga"};
        StringBuilder sb = new StringBuilder();
        for (String str : jumpScareTypes)
            sb.append(str).append(", ");
        String jumpScareChooseText = "Выберите один из скримеров\nДоступные варианты: " +
                sb.substring(0, sb.lastIndexOf(",")) + "\n";
        String readedJumpScare;
        do {
            System.out.print(jumpScareChooseText);
        } while (!jumpScareChooseText.contains(readedJumpScare = reader.readLine()));
        jumpScare = readedJumpScare;
    }

    public static ConfigurationModule init() throws IOException {
        return new ConfigurationModule();
    }

    public float getVolume() {
        return volume;
    }

    public int[] getDelay() {
        return delay;
    }

    public String getJumpScare() {
        return jumpScare;
    }
}
