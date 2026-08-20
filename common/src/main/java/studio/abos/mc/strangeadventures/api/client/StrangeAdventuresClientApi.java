package studio.abos.mc.strangeadventures.api.client;

import java.lang.reflect.InvocationTargetException;

public class StrangeAdventuresClientApi {

    private static final InternalClientMethods __internalMethods;

    static {
        try {
            __internalMethods = (InternalClientMethods) Class.forName("studio.abos.mc.strangeadventures.client.InternalClientMethodsImpl").getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
