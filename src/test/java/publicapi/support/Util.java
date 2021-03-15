package publicapi.support;

import java.util.Random;

/**
 * @author jussaragranja
 * Class with generic methods
 */

public class Util {

    public static int randomValue(int max) {
        int valor = new Random().nextInt(max);
        return valor;
    }

}
