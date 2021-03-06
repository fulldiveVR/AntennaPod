package de.danoeh.antennapod.core.util.exception;

import android.util.Log;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;

public class RxJavaErrorHandlerSetup {

    private RxJavaErrorHandlerSetup() {

    }

    public static void setupRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler(e -> {
            if (e instanceof UndeliverableException) {
                // Probably just disposed because the fragment was left
                Log.d("RxJavaErrorHandler", "Ignored exception: " + Log.getStackTraceString(e));
                return;
            }
            Thread.currentThread().getUncaughtExceptionHandler()
                    .uncaughtException(Thread.currentThread(), e);
        });
    }
}
