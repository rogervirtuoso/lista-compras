package io.github.rogervirtuoso.listacompras.java.backend;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author Roger
 */
@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        packages("io.github.rogervirtuoso.listacompras.java.backend.controllers");
    }
}
