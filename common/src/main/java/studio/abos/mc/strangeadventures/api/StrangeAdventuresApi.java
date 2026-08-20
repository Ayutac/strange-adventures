package studio.abos.mc.strangeadventures.api;

import java.lang.reflect.InvocationTargetException;

public class StrangeAdventuresApi {

    public static final String MOD_ID = "strangeadventures";

    private static final InternalMethods __internalMethods;

    static {
        try {
            __internalMethods = (InternalMethods) Class.forName("studio.abos.mc.strangeadventures.InternalMethodsImpl").getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
