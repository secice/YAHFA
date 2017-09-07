package lab.galaxy.yahfa.demoApp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;
import lab.galaxy.yahfa.HookMain;

/**
 * Created by liuruikai756 on 30/03/2017.
 */

public class MainApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        try {
        /*
        Build and put the demoPlugin apk in sdcard before running the demoApp
         */
//            ClassLoader classLoader = getClassLoader();

//            DexClassLoader dexClassLoader = new DexClassLoader("/sdcard/demoPlugin-debug.apk",
//                    getCodeCacheDir().getAbsolutePath(), null, classLoader);
//            HookMain.doHookDefault(dexClassLoader, classLoader);
//            HookMain.doHookItemDefault(
//                    classLoader,
//                    "lab.galaxy.yahfa.internalPlugin.Hook_ClassWithVirtualMethod_tac",
//                    classLoader
//            );
            Method hook = null;
            Method backup = null;
            Class obj_class = null;
            try {
                Class[] pareTyple = {Object.class, String.class, String.class, String.class, String.class};
                obj_class = Class.forName("lab.galaxy.yahfa.internalPlugin.Hook_ClassWithVirtualMethod_tac");
                hook = obj_class.getMethod("hook",pareTyple);
                backup = obj_class.getMethod("origin",pareTyple);

                obj_class = Class.forName("lab.galaxy.yahfa.demoApp.ClassWithVirtualMethod");
                HookMain.findAndBackupAndHook(
                        obj_class,
                        "tac",
                        "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
                        hook,
                        backup
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
