package lctest.java

import org.junit.Test

class JavaSystemProperties {
    @Test
    fun run() {
        println("java.version"                  .padEnd(30)+ ": " + System.getProperty("java.version"                 ))
        println("java.version.date"             .padEnd(30)+ ": " + System.getProperty("java.version.date"            ))
        println("java.vendor"                   .padEnd(30)+ ": " + System.getProperty("java.vendor"                  ))
        println("java.vendor.version"           .padEnd(30)+ ": " + System.getProperty("java.vendor.version"          ))
        println("java.vendor.url"               .padEnd(30)+ ": " + System.getProperty("java.vendor.url"              ))
        println("java.vendor.url.bug"           .padEnd(30)+ ": " + System.getProperty("java.vendor.url.bug"          ))
        println("java.specification.name"       .padEnd(30)+ ": " + System.getProperty("java.specification.name"      ))
        println("java.specification.vendor"     .padEnd(30)+ ": " + System.getProperty("java.specification.vendor"    ))
        println("java.specification.version"    .padEnd(30)+ ": " + System.getProperty("java.specification.version"   ))
        println("java.vm.name"                  .padEnd(30)+ ": " + System.getProperty("java.vm.name"                 ))
        println("java.vm.vendor"                .padEnd(30)+ ": " + System.getProperty("java.vm.vendor"               ))
        println("java.vm.version"               .padEnd(30)+ ": " + System.getProperty("java.vm.version"              ))
        println("java.vm.info"                  .padEnd(30)+ ": " + System.getProperty("java.vm.info"                 ))
        println("java.vm.specification.name"    .padEnd(30)+ ": " + System.getProperty("java.vm.specification.name"   ))
        println("java.vm.specification.vendor"  .padEnd(30)+ ": " + System.getProperty("java.vm.specification.vendor" ))
        println("java.vm.specification.version" .padEnd(30)+ ": " + System.getProperty("java.vm.specification.version"))
        println("java.runtime.name"             .padEnd(30)+ ": " + System.getProperty("java.runtime.name"            ))
        println("java.runtime.version"          .padEnd(30)+ ": " + System.getProperty("java.runtime.version"         ))
        println("java.class.version"            .padEnd(30)+ ": " + System.getProperty("java.class.version"           ))
        println("jdk.debug"                     .padEnd(30)+ ": " + System.getProperty("jdk.debug"                    ))
        println("sun.java.launcher"             .padEnd(30)+ ": " + System.getProperty("sun.java.launcher"            ))
        println("sun.management.compiler"       .padEnd(30)+ ": " + System.getProperty("sun.management.compiler"      ))
    }
}