# SlauthAndroid

inside your root project `build.gradle` file add

```
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url 'https://devolutions.bintray.com/maven'
        }
    }
}
```

then in your dependencies add:
 ```
 implementation 'devolutions:slauth:0.1.0'
 ```
