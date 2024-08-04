package org.domi.function.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DomiGUI {
    String title();
    int line();
    boolean justLook();
}
