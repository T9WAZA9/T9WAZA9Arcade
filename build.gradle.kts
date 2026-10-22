// Archivo de nivel raiz: solo declara los plugins que usaran los modulos.
// No contiene configuracion de Android: eso vive en app/build.gradle.kts
plugins {
    id("com.android.application") version "8.11.0" apply false
    id("org.jetbrains.kotlin.android") version "2.4.10" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.10" apply false
}
