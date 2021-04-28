# SlauthAndroid

inside your root project `build.gradle` file add

```
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url "https://dl.cloudsmith.io/public/devolutions/maven-public/maven/"
        }
    }
}
```

then in your dependencies add:
 ```
 implementation 'devolutions:slauth:0.1.0'
 implementation 'net.java.dev.jna:jna:5.3.1'
 ```
