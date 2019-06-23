package org.project.study.util;

import java.util.Random;

public class RandomUtil {

    public static String randomFileName() {
        Random random = new Random();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            switch (random.nextInt(5)) {
                case 0:
                    result.append(random.nextInt(10));
                    break;
                case 1:
                case 2:
                    result.append((char) (random.nextInt(26) + 65));
                    break;
                case 3:
                default:
                    result.append((char) (random.nextInt(26) + 97));
                    break;
            }
        }
        return result.toString();
    }
}
