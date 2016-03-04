

1、 com.android.mobile.base.fn.db >> 导出为数据库框架， KevinSDK-Android-DB-Core-1.0.1.jar

2. 
2.1 com.android.mobile.base.fn.imageloader >> 导出为图片缓存加载框架， KevinSDK-Android-ImageLoader-1.0.1.jar
2.2 使用参考: mImageLoader = ImageLoader.getInstance(3, Type.LIFO);  
    mImageLoader.loadImage(getItem(position), imageview, true);


################################################################################
##### 数据加载逻辑 -- START 
#####
################################################################################
1  初始化加载的时候(刷新)判断是否有网络
1.1 有网络,执行网络加载，
    1） 网络初次加载无数据，那么从数据库加载数据，并且不允许再次网络加载，并标记数据来源为数据库
    2） 网络初次加载有数据。那么允许加载更多，并且标记数据来源为网络。
      2.1 当加载更多时，也要判断网络。如果没有网络，则提示"网络不可用",不执行加载请求。否则加载更多。


1.2 无网络，执行数据库加载。
    1） 直接从数据加载数据，并且不允许再次网络加载，并标记数据来源为数据库
    


#------------JNI开发|START|---------------
#------------JNI开发|START|---------------
#------------JNI开发|START|---------------
Step 1: 指定NDK的root路径：
Windows环境下添加环境变量： NDK_ROOT=D:\Software_Java\Android\android-ndk64-r10-windows-x86_64\android-ndk-r10


Step: 3.javah生成c头文件
点击"View->Tool Windows->Terminal"，即在Studio中进行终端命令行工具.执行如下命令生成c语言头文件。
这里需要注意的是要进入 <Project>\app\src\main的目录下执行javah命令，为的是生成的 .h 文件同样是
在<Project>\app\src\main路径下，可以在Studio的工程结构中直接看到。
操作命令：
javah -d jni -classpath <SDK_android.jar>;<APP_classes> lab.sodino.jnitest.MainActivity
具体操作图如下：

javah -d jni -classpath D:\Software_Java\Android\android-sdk_r24.0.2-windows\platforms\android-4.4.2\android.jar;
..\..\build\intermediates\classes\debug  com.android.demo.jni.JNISecurity

Step: 4.编辑c文件
在main.c文件中实现头文件中的方法，具体功能为直接return回一个String，并且使用android_log打印出相关日志。
代码如下：

Step: 5.配置NDK
这一步包括两个动作：
1.指明ndk路径
2. 修改build.gradle配置
    工程中共有两个build.gradle配置文件，我们要修改的是在<Project>\app\build.gradle这个文件。为其在defaultConfig分支中增加上
5

D:/workspace/android/workspace_git/Code/Android/workspace_studio/AndroidStudio/AndroidMobile_01_base/build/intermediates/ndk/debug/lib




#------------Windows环境 cmd下使用ndk编译JNI项目 |START|---------------

C:\Users\Administrator>ndk-build  --help
Usage: make.exe [options] [target] ...
Options:
  -b, -m                      Ignored for compatibility.
  -B, --always-make           Unconditionally make all targets.
  -C DIRECTORY, --directory=DIRECTORY
                              Change to DIRECTORY before doing anything.
  -d                          Print lots of debugging information.
  --debug[=FLAGS]             Print various types of debugging information.
  -e, --environment-overrides
                              Environment variables override makefiles.
  -f FILE, --file=FILE, --makefile=FILE
                              Read FILE as a makefile.
  -h, --help                  Print this message and exit.
  -i, --ignore-errors         Ignore errors from commands.
  -I DIRECTORY, --include-dir=DIRECTORY
                              Search DIRECTORY for included makefiles.
  -j [N], --jobs[=N]          Allow N jobs at once; infinite jobs with no arg.
  -k, --keep-going            Keep going when some targets can't be made.
  -l [N], --load-average[=N], --max-load[=N]
                              Don't start multiple jobs unless load is below N.
  -L, --check-symlink-times   Use the latest mtime between symlinks and target.
  -n, --just-print, --dry-run, --recon
                              Don't actually run any commands; just print them.
  -o FILE, --old-file=FILE, --assume-old=FILE
                              Consider FILE to be very old and don't remake it.
  -p, --print-data-base       Print make's internal database.
  -q, --question              Run no commands; exit status says if up to date.
  -r, --no-builtin-rules      Disable the built-in implicit rules.
  -R, --no-builtin-variables  Disable the built-in variable settings.
  -s, --silent, --quiet       Don't echo commands.
  -S, --no-keep-going, --stop
                              Turns off -k.
  -t, --touch                 Touch targets instead of remaking them.
  -v, --version               Print the version number of make and exit.
  -w, --print-directory       Print the current directory.
  --no-print-directory        Turn off -w, even if it was turned on implicitly.
  -W FILE, --what-if=FILE, --new-file=FILE, --assume-new=FILE
                              Consider FILE to be infinitely new.
  --warn-undefined-variables  Warn when an undefined variable is referenced.

#-----------------运行ndk-build NDK_LOG=1 |START|-----------------------------------------------------------------------
C:\Users\Administrator>ndk-build NDK_LOG=1
Android NDK: NDK installation path auto-detected: 'D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10'
Android NDK: GNU Make version 3.81 detected
Android NDK: Host OS was auto-detected: windows
Android NDK:  Host operating system detected: windows
Android NDK: Host CPU was auto-detected: x86
Android NDK: HOST_TAG set to windows
Android NDK: Host tools prebuilt directory: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/prebuilt/windows-x86_64/bin
Android NDK: Host 'echo' tool: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/prebuilt/windows-x86_64/bin/echo.exe
Android NDK: Host 'echo -n' tool: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/prebuilt/windows-x86_64/bin/echo.exe -n
Android NDK: Host 'cmp' tool: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/prebuilt/windows-x86_64/bin/cmp.exe
Android NDK: Host 'awk' tool: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/prebuilt/windows-x86_64/bin/awk.exe
Android NDK: Host 'awk' test returned: Pass
Android NDK: Found platform root directory: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/platforms
Android NDK: Found supported platforms: android-L
Android NDK: PLATFORM android-L supports: arm arm64 mips mips64 x86 x86_64
Android NDK:   ABI arm sysroot is: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/platforms/android-L/arch-arm
Android NDK:   ABI arm64 sysroot is: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/platforms/android-L/arch-arm64
Android NDK:   ABI mips sysroot is: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/platforms/android-L/arch-mips
Android NDK:   ABI mips64 sysroot is: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/platforms/android-L/arch-mips64
Android NDK:   ABI x86 sysroot is: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/platforms/android-L/arch-x86
Android NDK:   ABI x86_64 sysroot is: D:/Software_Java/Android/android-ndk64-r10-windows-x86_64/android-ndk-r10/platforms/android-L/arch-x86_64
Android NDK: Found stable platform levels: L
Android NDK: Found max platform level: L
Android NDK:  This NDK supports the following target architectures and ABIS:
Android NDK:    arm: armeabi armeabi-v7a armeabi-v7a-hard
Android NDK:    arm64: arm64-v8a
Android NDK:    mips: mips
Android NDK:    mips64: mips64
Android NDK:    x86: x86
Android NDK:    x86_64: x86_64
Android NDK:  This NDK supports the following toolchains and target ABIs:
Android NDK:    aarch64-linux-android-4.9:  arm64-v8a
Android NDK:    aarch64-linux-android-clang3.4:  arm64-v8a
Android NDK:    arm-linux-androideabi-4.9:  armeabi armeabi-v7a armeabi-v7a-hard
Android NDK:    mips64el-linux-android-4.9:  mips64
Android NDK:    mips64el-linux-android-clang3.4:  mips64
Android NDK:    mipsel-linux-android-4.9:  mips
Android NDK:    x86-4.9:  x86
Android NDK:    x86_64-4.9:  x86_64
Android NDK:    x86_64-clang3.4:  x86_64
Android NDK: Found project path: D:\workspace\android\workspace_git\Code\Android\workspace_studio\AndroidStudio\AndroidMobile_01_base\src\main\
Android NDK: Ouput path for intermediate files: D:\workspace\android\workspace_git\Code\Android\workspace_studio\AndroidStudio\AndroidMobile_01_base\src\main\/obj
Android NDK: Ouput path for generated library files: D:\workspace\android\workspace_git\Code\Android\workspace_studio\AndroidStudio\AndroidMobile_01_base\src\main\/libs
Android NDK: Parsing D:\workspace\android\workspace_git\Code\Android\workspace_studio\AndroidStudio\AndroidMobile_01_base\src\main\/jni/Application.mk

----------------------------------------------------------------------------------------

Step1:  添加环境变量： NDK_ROOT=D:\Software_Java\Android\android-ndk64-r10-windows-x86_64\android-ndk-r10
Step2:

 配置  Android.mk 文件  ---------------------------

# Copyright (C) 2009 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := security
LOCAL_SRC_FILES :=    security-AES.c \
                      security-AES-JNI.c

include $(BUILD_SHARED_LIBRARY)

配置  Application.mk 文件  ---------------------------
APP_ABI := all


Step3:  执行编译命令：
> ndk-build NDK_LOG=1  -w -s  -i

或者执行编译指令:  记录日志输出
ndk-build   -w  -i  -B  1>build-log.txt 2>build-log2.txt






#-----------------AndroidStudio打包so文件解决办法-------------------------------------
#-----------------AndroidStudio打包so文件解决办法-------------------------------------
分类： Android学习 AndroidStudio 2014-01-17 17:54 3089人阅读 评论(3) 收藏 举报
Androidandroid开发AndroidStudio
PS(2014-01-23)：如果你的AndroidStudio是0.4.2或者更新，你可以参考这里，这个方法更简单。

AndroidStudio到现在也不支持so文件打包，网上有好多解决办法，但是我试过之后发现，没有一个能用的。可能是AndroidStudio的开发还在初期，版本的不断变化影响的。
今天尝试着用AndroidStudio打包包含so的项目，花了整整一天的时间，终于尝试成功了，现在把结果记录一下。
我用的是目前AndroidStudio的最新版0.4.2，gradle的版本我真心不知道怎么看，但是我的build.gradle中是这样写的：
dependencies {
        classpath 'com.android.tools.build:gradle:0.7.+'
    }

并且gradle-wrapper-properties文件内容为：
#Fri Jan 17 12:09:27 CST 2014
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
distributionUrl=http\://services.gradle.org/distributions/gradle-1.9-all.zip

进入正题，我的项目包含一个第三方的library工程，所以在打包so的时候，需要把第三方library工程的so库也要打包进去。参考了如下的方法：
http://www.w3c.com.cn/%E5%9C%A8android-studio-%E4%B8%AD%E5%8A%A0%E5%85%A5jar-%E5%92%8C-so-%E6%96%87%E4%BB%B6
http://blog.csdn.net/ft2028739/article/details/14163481
http://stackoverflow.com/questions/16683775/include-so-library-in-apk-in-android-studio
然后不断的修改尝试，最终成功打包so文件。
主要思想：
1：将所有的so文件打包进一个jar文件；
2：将这个jar文件作为依赖文件；
我不会写Goovy的代码，下面这些都是我从上面那些参考页面抄的，大概意思能看懂，如果有问题，请指正，谢谢!
代码
task nativeLibsToJar(type: Zip, description: 'create a jar archive of the native libs') {
        destinationDir file("$buildDir/native-libs")
        baseName 'native-libs'
        extension 'jar'
        from(new File(project(':MyProject').getProjectDir(), 'libs')) { include '**/*.so' }
        into 'lib/'
        from(new File(project(':library').getProjectDir(), 'libs')) { include '**/*.so' }
        into 'lib/'
    }

    tasks.withType(Compile) {
        compileTask -> compileTask.dependsOn(nativeLibsToJar)
    }


上面代码中，第五行的“MyProject”是我的项目名称，而第七行的“library”是第三方的库工程名字。
第一段代码的功能是将MyProject和library项目中libs下的.so文件都打包压缩成zip包，放在build/native-libs目录下，名字叫做native-libs,后缀是jar，即native-libs.jar。这里要注意，打包so的时候，目录结构是不变的，而且增加了lib/目录，意思就是native-libs.jar用压缩文件打开的话，目录结构为lib/armeabi/xx.so。
第二段代码我不知道啥意思，我猜测应该是将第一段代码作为编译命令的依赖，必须要提前于compile执行。
上面的代码执行结果就是会在build/native-libs目录下生成native-jars.jar文件，接下来我们就要将这个jar文件作为我们项目的依赖，需要增加下面这句代码：
dependencies {
    compile fileTree(dir: 'build/native-libs',include:'native-libs.jar')
}
这里需要注意上面dir的部分。我看到的资料中都是这样写的：$buildDir/native-libs，但是这样写不会将native-libs.jar引进项目，我不知道原因，我也是不断测试发现的。
好了，到这里你就可以尝试重新编译你的项目，看看生成的apk中时候包含了lib目录。
我对AndroidStudio不是很了解，如果有错误的地方，请指正。


