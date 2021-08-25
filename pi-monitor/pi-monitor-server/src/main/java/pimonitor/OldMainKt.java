package pimonitor;

import bitframe.Application;
import bitframe.server.InMemoryDaoProvider;
import bitframe.server.modules.Builders;
import bitframe.server.modules.Module;
import bitframe.server.modules.ModuleKt;
import bitframe.server.modules.authentication.DefaultAuthenticationModule;

import java.util.List;

public class OldMainKt {
    public static void main(String[] args) {
//        var provider = new InMemoryDaoProvider();
//        var authModule = new DefaultAuthenticationModule(provider);
//        var server = new Application(
//                authModule,
//                List.of(
//                        Builders.INSTANCE.moduleOf(Monitor.class),
//                        Builders.INSTANCE.moduleOf(Monitor.Business.class),
//                        Builders.INSTANCE.moduleOf(Monitored.class)
//                )
//        );
//        server.start();
    }
}
