// Archivo de nivel raiz: solo declara los plugins que usaran los modulos.
// No contiene configuracion de Android: eso vive en app/build.gradle.kts
buildscript {
    ext {
        set("compose_version", "1.5.4")
    }
}
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}

