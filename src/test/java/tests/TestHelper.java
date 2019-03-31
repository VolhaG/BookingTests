package tests;

import javax.annotation.Nonnull;
import java.util.Calendar;

import static java.lang.Thread.sleep;

public class TestHelper {
    @Nonnull
    static Calendar getNextSaturday() {
        Calendar res = Calendar.getInstance();

        while (res.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            res.add(Calendar.DATE, 1);
        }
        return res;
    }

    public static void pause(int seconds) {
        try {
            sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
