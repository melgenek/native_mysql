1) Create fat jar:
```
sbt clean assembly
```
2)

native-image -J-Xmx8G -H:+ReportExceptionStackTraces  \
             --delay-class-initialization-to-runtime="com.mysql.cj.jdbc.AbandonedConnectionCleanupThread,com.mysql.cj.jdbc.NonRegisteringDriver,com.mysql.cj.jdbc.MysqlDataSource,java.sql.DriverManager"               \
             --verbose --no-server -jar ./target/scala-2.12/fat.jar

Fails with:

```
Executing [
/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/bin/java \
-XX:+UnlockExperimentalVMOptions \
-XX:+EnableJVMCI \
-XX:-UseJVMCICompiler \
-Dtruffle.TrustAllTruffleRuntimeProviders=true \
-Dtruffle.TruffleRuntime=com.oracle.truffle.api.impl.DefaultTruffleRuntime \
-Dgraalvm.locatorDisabled=true \
-d64 \
-XX:-UseJVMCIClassLoader \
-Xss10m \
-Xms1g \
-Xmx13176179912 \
-Duser.country=US \
-Duser.language=en \
-Dgraalvm.version=1.0.0-rc14 \
-Dorg.graalvm.version=1.0.0-rc14 \
-Dcom.oracle.graalvm.isaot=true \
-Djvmci.class.path.append=/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/jvmci/graal.jar \
-Xmx8G \
-Xbootclasspath/a:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/boot/graal-sdk.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/boot/graaljs-scriptengine.jar \
-cp \
/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/builder/svm.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/builder/pointsto.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/builder/objectfile.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/jvmci/jvmci-hotspot.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/jvmci/graal-management.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/jvmci/graal.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/jvmci/jvmci-api.jar \
com.oracle.svm.hosted.NativeImageGeneratorRunner \
-watchpid \
13360 \
-imagecp \
/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/boot/graal-sdk.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/boot/graaljs-scriptengine.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/builder/svm.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/builder/pointsto.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/builder/objectfile.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/jvmci/jvmci-hotspot.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/jvmci/graal-management.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/jvmci/graal.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/jvmci/jvmci-api.jar:/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/library-support.jar:/home/yevhenii/myproj/native_mysql/target/scala-2.12/fat.jar \
-H:Path=/home/yevhenii/myproj/native_mysql \
-H:+ReportExceptionStackTraces \
-H:DelayClassInitialization=com.mysql.cj.jdbc.AbandonedConnectionCleanupThread,com.mysql.cj.jdbc.NonRegisteringDriver,com.mysql.cj.jdbc.MysqlDataSource,java.sql.DriverManager \
-H:Class=sample.Main \
-H:Name=fat \
-H:CLibraryPath=/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/clibraries/linux-amd64
]
[fat:13361]    classlist:   4,561.69 ms
[fat:13361]        (cap):   1,457.29 ms
[fat:13361]        setup:   3,062.97 ms
[fat:13361]   (typeflow):  10,388.67 ms
[fat:13361]    (objects):  10,994.66 ms
[fat:13361]   (features):     410.24 ms
[fat:13361]     analysis:  22,257.97 ms
[fat:13361]     universe:     494.39 ms
[fat:13361]      (parse):   4,469.08 ms
[fat:13361]     (inline):   3,199.51 ms
[fat:13361]    (compile):  15,911.35 ms
[fat:13361]      compile:  24,800.88 ms
[fat:13361]        image:   1,878.40 ms
[fat:13361]        write:     223.37 ms
Fatal error: java.lang.RuntimeException: java.lang.RuntimeException: host C compiler or linker does not seem to work: java.lang.RuntimeException: returned 1

Running command: cc -v -o /home/yevhenii/myproj/native_mysql/fat -z noexecstack -Wl,--defsym -Wl,main=IsolateEnterStub__JavaMainWrapper__run__5087f5482cc9a6abc971913ece43acb471d2631b__a61fe6c26e84dd4037e4629852b5488bfcc16e7e -L/tmp/SVM-7909071594339454095 -L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib -L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/clibraries/linux-amd64 /tmp/SVM-7909071594339454095/fat.o -lffi -ldl -lpthread -lpthread -ldl -lz -lpthread -lstrictmath -ljvm -llibchelper -lcrypt -lrt -lrt -lrt -lrt -lrt

Using built-in specs.
COLLECT_GCC=cc
COLLECT_LTO_WRAPPER=/usr/lib/gcc/x86_64-linux-gnu/8/lto-wrapper
OFFLOAD_TARGET_NAMES=nvptx-none
OFFLOAD_TARGET_DEFAULT=1
Target: x86_64-linux-gnu
Configured with: ../src/configure -v --with-pkgversion='Ubuntu 8.2.0-7ubuntu1' --with-bugurl=file:///usr/share/doc/gcc-8/README.Bugs --enable-languages=c,ada,c++,go,brig,d,fortran,objc,obj-c++ --prefix=/usr --with-gcc-major-version-only --program-suffix=-8 --program-prefix=x86_64-linux-gnu- --enable-shared --enable-linker-build-id --libexecdir=/usr/lib --without-included-gettext --enable-threads=posix --libdir=/usr/lib --enable-nls --with-sysroot=/ --enable-clocale=gnu --enable-libstdcxx-debug --enable-libstdcxx-time=yes --with-default-libstdcxx-abi=new --enable-gnu-unique-object --disable-vtable-verify --enable-libmpx --enable-plugin --enable-default-pie --with-system-zlib --with-target-system-zlib --enable-objc-gc=auto --enable-multiarch --disable-werror --with-arch-32=i686 --with-abi=m64 --with-multilib-list=m32,m64,mx32 --enable-multilib --with-tune=generic --enable-offload-targets=nvptx-none --without-cuda-driver --enable-checking=release --build=x86_64-linux-gnu --host=x86_64-linux-gnu --target=x86_64-linux-gnu
Thread model: posix
gcc version 8.2.0 (Ubuntu 8.2.0-7ubuntu1)
COMPILER_PATH=/usr/lib/gcc/x86_64-linux-gnu/8/:/usr/lib/gcc/x86_64-linux-gnu/8/:/usr/lib/gcc/x86_64-linux-gnu/:/usr/lib/gcc/x86_64-linux-gnu/8/:/usr/lib/gcc/x86_64-linux-gnu/
LIBRARY_PATH=/usr/lib/gcc/x86_64-linux-gnu/8/:/usr/lib/gcc/x86_64-linux-gnu/8/../../../x86_64-linux-gnu/:/usr/lib/gcc/x86_64-linux-gnu/8/../../../../lib/:/lib/x86_64-linux-gnu/:/lib/../lib/:/usr/lib/x86_64-linux-gnu/:/usr/lib/../lib/:/usr/lib/gcc/x86_64-linux-gnu/8/../../../:/lib/:/usr/lib/
COLLECT_GCC_OPTIONS='-v' '-o' '/home/yevhenii/myproj/native_mysql/fat' '-z' 'noexecstack' '-L/tmp/SVM-7909071594339454095' '-L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib' '-L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/clibraries/linux-amd64' '-mtune=generic' '-march=x86-64'
 /usr/lib/gcc/x86_64-linux-gnu/8/collect2 -plugin /usr/lib/gcc/x86_64-linux-gnu/8/liblto_plugin.so -plugin-opt=/usr/lib/gcc/x86_64-linux-gnu/8/lto-wrapper -plugin-opt=-fresolution=/tmp/ccxpAcmp.res -plugin-opt=-pass-through=-lgcc -plugin-opt=-pass-through=-lgcc_s -plugin-opt=-pass-through=-lc -plugin-opt=-pass-through=-lgcc -plugin-opt=-pass-through=-lgcc_s --sysroot=/ --build-id --eh-frame-hdr -m elf_x86_64 --hash-style=gnu --as-needed -dynamic-linker /lib64/ld-linux-x86-64.so.2 -pie -z now -z relro -o /home/yevhenii/myproj/native_mysql/fat -z noexecstack /usr/lib/gcc/x86_64-linux-gnu/8/../../../x86_64-linux-gnu/Scrt1.o /usr/lib/gcc/x86_64-linux-gnu/8/../../../x86_64-linux-gnu/crti.o /usr/lib/gcc/x86_64-linux-gnu/8/crtbeginS.o -L/tmp/SVM-7909071594339454095 -L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib -L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/clibraries/linux-amd64 -L/usr/lib/gcc/x86_64-linux-gnu/8 -L/usr/lib/gcc/x86_64-linux-gnu/8/../../../x86_64-linux-gnu -L/usr/lib/gcc/x86_64-linux-gnu/8/../../../../lib -L/lib/x86_64-linux-gnu -L/lib/../lib -L/usr/lib/x86_64-linux-gnu -L/usr/lib/../lib -L/usr/lib/gcc/x86_64-linux-gnu/8/../../.. --defsym main=IsolateEnterStub__JavaMainWrapper__run__5087f5482cc9a6abc971913ece43acb471d2631b__a61fe6c26e84dd4037e4629852b5488bfcc16e7e /tmp/SVM-7909071594339454095/fat.o -lffi -ldl -lpthread -lpthread -ldl -lz -lpthread -lstrictmath -ljvm -llibchelper -lcrypt -lrt -lrt -lrt -lrt -lrt -lgcc --push-state --as-needed -lgcc_s --pop-state -lc -lgcc --push-state --as-needed -lgcc_s --pop-state /usr/lib/gcc/x86_64-linux-gnu/8/crtendS.o /usr/lib/gcc/x86_64-linux-gnu/8/../../../x86_64-linux-gnu/crtn.o
/usr/bin/ld: /home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/clibraries/linux-amd64/libjvm.a(JvmFuncs.o): in function `JVM_FindLibraryEntry':
/b/b/e/main/substratevm/src/com.oracle.svm.native.jvm.posix/src/JvmFuncs.c:112: undefined reference to `dlsym'
collect2: error: ld returned 1 exit status

        at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
        at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
        at java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:593)
        at java.util.concurrent.ForkJoinTask.get(ForkJoinTask.java:1005)
        at com.oracle.svm.hosted.NativeImageGenerator.run(NativeImageGenerator.java:433)
        at com.oracle.svm.hosted.NativeImageGeneratorRunner.buildImage(NativeImageGeneratorRunner.java:284)
        at com.oracle.svm.hosted.NativeImageGeneratorRunner.build(NativeImageGeneratorRunner.java:406)
        at com.oracle.svm.hosted.NativeImageGeneratorRunner.main(NativeImageGeneratorRunner.java:106)
Caused by: java.lang.RuntimeException: host C compiler or linker does not seem to work: java.lang.RuntimeException: returned 1

Running command: cc -v -o /home/yevhenii/myproj/native_mysql/fat -z noexecstack -Wl,--defsym -Wl,main=IsolateEnterStub__JavaMainWrapper__run__5087f5482cc9a6abc971913ece43acb471d2631b__a61fe6c26e84dd4037e4629852b5488bfcc16e7e -L/tmp/SVM-7909071594339454095 -L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib -L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/clibraries/linux-amd64 /tmp/SVM-7909071594339454095/fat.o -lffi -ldl -lpthread -lpthread -ldl -lz -lpthread -lstrictmath -ljvm -llibchelper -lcrypt -lrt -lrt -lrt -lrt -lrt

Using built-in specs.
COLLECT_GCC=cc
COLLECT_LTO_WRAPPER=/usr/lib/gcc/x86_64-linux-gnu/8/lto-wrapper
OFFLOAD_TARGET_NAMES=nvptx-none
OFFLOAD_TARGET_DEFAULT=1
Target: x86_64-linux-gnu
Configured with: ../src/configure -v --with-pkgversion='Ubuntu 8.2.0-7ubuntu1' --with-bugurl=file:///usr/share/doc/gcc-8/README.Bugs --enable-languages=c,ada,c++,go,brig,d,fortran,objc,obj-c++ --prefix=/usr --with-gcc-major-version-only --program-suffix=-8 --program-prefix=x86_64-linux-gnu- --enable-shared --enable-linker-build-id --libexecdir=/usr/lib --without-included-gettext --enable-threads=posix --libdir=/usr/lib --enable-nls --with-sysroot=/ --enable-clocale=gnu --enable-libstdcxx-debug --enable-libstdcxx-time=yes --with-default-libstdcxx-abi=new --enable-gnu-unique-object --disable-vtable-verify --enable-libmpx --enable-plugin --enable-default-pie --with-system-zlib --with-target-system-zlib --enable-objc-gc=auto --enable-multiarch --disable-werror --with-arch-32=i686 --with-abi=m64 --with-multilib-list=m32,m64,mx32 --enable-multilib --with-tune=generic --enable-offload-targets=nvptx-none --without-cuda-driver --enable-checking=release --build=x86_64-linux-gnu --host=x86_64-linux-gnu --target=x86_64-linux-gnu
Thread model: posix
gcc version 8.2.0 (Ubuntu 8.2.0-7ubuntu1)
COMPILER_PATH=/usr/lib/gcc/x86_64-linux-gnu/8/:/usr/lib/gcc/x86_64-linux-gnu/8/:/usr/lib/gcc/x86_64-linux-gnu/:/usr/lib/gcc/x86_64-linux-gnu/8/:/usr/lib/gcc/x86_64-linux-gnu/
LIBRARY_PATH=/usr/lib/gcc/x86_64-linux-gnu/8/:/usr/lib/gcc/x86_64-linux-gnu/8/../../../x86_64-linux-gnu/:/usr/lib/gcc/x86_64-linux-gnu/8/../../../../lib/:/lib/x86_64-linux-gnu/:/lib/../lib/:/usr/lib/x86_64-linux-gnu/:/usr/lib/../lib/:/usr/lib/gcc/x86_64-linux-gnu/8/../../../:/lib/:/usr/lib/
COLLECT_GCC_OPTIONS='-v' '-o' '/home/yevhenii/myproj/native_mysql/fat' '-z' 'noexecstack' '-L/tmp/SVM-7909071594339454095' '-L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib' '-L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/clibraries/linux-amd64' '-mtune=generic' '-march=x86-64'
 /usr/lib/gcc/x86_64-linux-gnu/8/collect2 -plugin /usr/lib/gcc/x86_64-linux-gnu/8/liblto_plugin.so -plugin-opt=/usr/lib/gcc/x86_64-linux-gnu/8/lto-wrapper -plugin-opt=-fresolution=/tmp/ccxpAcmp.res -plugin-opt=-pass-through=-lgcc -plugin-opt=-pass-through=-lgcc_s -plugin-opt=-pass-through=-lc -plugin-opt=-pass-through=-lgcc -plugin-opt=-pass-through=-lgcc_s --sysroot=/ --build-id --eh-frame-hdr -m elf_x86_64 --hash-style=gnu --as-needed -dynamic-linker /lib64/ld-linux-x86-64.so.2 -pie -z now -z relro -o /home/yevhenii/myproj/native_mysql/fat -z noexecstack /usr/lib/gcc/x86_64-linux-gnu/8/../../../x86_64-linux-gnu/Scrt1.o /usr/lib/gcc/x86_64-linux-gnu/8/../../../x86_64-linux-gnu/crti.o /usr/lib/gcc/x86_64-linux-gnu/8/crtbeginS.o -L/tmp/SVM-7909071594339454095 -L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib -L/home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/clibraries/linux-amd64 -L/usr/lib/gcc/x86_64-linux-gnu/8 -L/usr/lib/gcc/x86_64-linux-gnu/8/../../../x86_64-linux-gnu -L/usr/lib/gcc/x86_64-linux-gnu/8/../../../../lib -L/lib/x86_64-linux-gnu -L/lib/../lib -L/usr/lib/x86_64-linux-gnu -L/usr/lib/../lib -L/usr/lib/gcc/x86_64-linux-gnu/8/../../.. --defsym main=IsolateEnterStub__JavaMainWrapper__run__5087f5482cc9a6abc971913ece43acb471d2631b__a61fe6c26e84dd4037e4629852b5488bfcc16e7e /tmp/SVM-7909071594339454095/fat.o -lffi -ldl -lpthread -lpthread -ldl -lz -lpthread -lstrictmath -ljvm -llibchelper -lcrypt -lrt -lrt -lrt -lrt -lrt -lgcc --push-state --as-needed -lgcc_s --pop-state -lc -lgcc --push-state --as-needed -lgcc_s --pop-state /usr/lib/gcc/x86_64-linux-gnu/8/crtendS.o /usr/lib/gcc/x86_64-linux-gnu/8/../../../x86_64-linux-gnu/crtn.o
/usr/bin/ld: /home/yevhenii/.local/share/graalvm-ce-1.0.0-rc14/jre/lib/svm/clibraries/linux-amd64/libjvm.a(JvmFuncs.o): in function `JVM_FindLibraryEntry':
/b/b/e/main/substratevm/src/com.oracle.svm.native.jvm.posix/src/JvmFuncs.c:112: undefined reference to `dlsym'
collect2: error: ld returned 1 exit status

        at com.oracle.svm.hosted.image.NativeBootImageViaCC.write(NativeBootImageViaCC.java:307)
        at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:625)
        at com.oracle.svm.hosted.NativeImageGenerator.lambda$run$0(NativeImageGenerator.java:416)
        at java.util.concurrent.ForkJoinTask$AdaptedRunnableAction.exec(ForkJoinTask.java:1386)
        at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
        at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
        at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
        at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
Error: Image building with exit status 1
```