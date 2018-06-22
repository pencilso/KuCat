package io.javac.blockcat;

/**
 * Created by Pencilso on 2018/6/15/015.
 *
 * @author Pencilso
 */
public class AppContext extends android.support.multidex.MultiDexApplication implements Thread.UncaughtExceptionHandler {
    private static AppContext appContext;

    public static AppContext getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e != null) e.printStackTrace();
    }
}
