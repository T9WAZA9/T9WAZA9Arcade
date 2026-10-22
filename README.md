# T9WAZA9 ARCADE - Fase 1 (MVP)

Proyecto Android nativo (Kotlin + Jetpack Compose) pensado para escribirse
en Acode y compilarse en la nube con GitHub Actions, sin necesidad de PC.

## Que incluye esta fase

- Pantalla principal (estado vacio correcto, sin datos falsos).
- Navegacion inferior entre Inicio / Clips / Ajustes.
- Pantalla de Clips (estado vacio correcto).
- Ajustes con un interruptor de tema oscuro/claro que SI funciona
  (se guarda con SharedPreferences y cambia el color real de la app).
- Tema oscuro con acentos naranjas, Material 3.
- Icono de app original (vector, sin recursos de Omlet Arcade).

Lo que NO incluye todavia (llega en fases siguientes):
- Grabacion de pantalla (Fase 2).
- Burbuja flotante (Fase 3).
- Streaming (Fase 4).
- Comunidad online (Fase 5).

## 1. Herramientas gratuitas necesarias

| Herramienta | Para que | Donde conseguirla |
|---|---|---|
| Acode | Editar el codigo | Play Store o F-Droid |
| Termux | Git y terminal en Android | F-Droid (no la version de Play Store, esta desactualizada) |
| Cuenta de GitHub | Alojar el repo y compilar gratis | github.com |
| Un gestor de archivos con soporte ZIP | Extraer este proyecto | El que trae HyperOS sirve |

No hace falta instalar Android SDK, Gradle ni JDK en el telefono: la
compilacion ocurre en los servidores gratuitos de GitHub Actions, no en
tu Redmi 15C. Esto es importante para tu modelo con 4-8 GB de RAM: un
build de Gradle con Compose en el propio telefono es lento y propenso a
quedarse sin memoria; en la nube es gratis, estable y mucho mas rapido.

## 2. Preparar Termux (una sola vez)

```bash
pkg update -y
pkg install git -y
termux-setup-storage
```

## 3. Subir el proyecto a GitHub

1. Crea un repositorio nuevo y vacio en github.com (puede ser privado).
2. Extrae este ZIP en el almacenamiento del telefono, por ejemplo en
   `/storage/emulated/0/T9WAZA9Arcade`.
3. En Termux:

```bash
cd /storage/emulated/0/T9WAZA9Arcade
git init
git add .
git commit -m "Fase 1: MVP funcional"
git branch -M main
git remote add origin https://github.com/TU_USUARIO/TU_REPO.git
git push -u origin main
```

Termux te pedira tu usuario de GitHub y, en vez de contrasena, un
"personal access token" (se genera en GitHub: Settings > Developer
settings > Personal access tokens > Tokens classic, con permiso "repo").

4. Abre la carpeta del proyecto en Acode (menu > Open folder) para
   seguir editando los archivos desde ahi.

## 4. Compilar el APK (automatico)

En cuanto hagas `git push`, el archivo `.github/workflows/build-apk.yml`
se ejecuta solo en los servidores de GitHub. Para comprobarlo:

1. Entra a tu repositorio en github.com desde el navegador del telefono.
2. Pestana "Actions".
3. Abre la ejecucion mas reciente ("Compilar APK de depuracion").
4. Espera a que el icono se ponga en verde (unos 3-6 minutos la primera
   vez; las siguientes son mas rapidas por la cache de Gradle).
5. Baja hasta "Artifacts" y descarga `t9waza9-arcade-debug` (es un ZIP
   que contiene `app-debug.apk`).

## 5. Instalar en el Redmi 15C

1. Extrae `app-debug.apk` del ZIP descargado.
2. Abre el APK con tu gestor de archivos.
3. HyperOS pedira activar "Instalar apps desconocidas" para esa app
   (navegador o gestor de archivos): acepta esa app en concreto.
4. Instala y abre T9WAZA9 ARCADE.

## 6. Prueba concreta de que la Fase 1 funciona

- La app abre sin cerrarse sola.
- Se ven 3 pestanas abajo: Inicio, Clips, Ajustes.
- Inicio y Clips muestran un mensaje de "todavia no hay..." (esto es
  correcto: la grabacion no existe hasta la Fase 2).
- En Ajustes, el interruptor de tema cambia de verdad los colores de
  toda la app, y si cierras y vuelves a abrir la app, recuerda la
  eleccion (queda guardada en SharedPreferences).

## 7. Errores previsibles y como diagnosticarlos

**El build de Actions falla en el paso "Compilar APK de depuracion"**
Abre el log de ese paso (toca sobre el) y busca la primera linea en
rojo. Los mas probables en este proyecto:

- `Dependency ... requires compileSdk 37 / Android Gradle Plugin X.Y`:
  esto pasaria si en el futuro `compose-bom` sube de version y exige
  una herramienta mas nueva que la fijada aqui (AGP 8.11 / compileSdk
  36). Solucion: en `app/build.gradle.kts` baja la version del BOM a
  una anterior a esa exigencia, o sube `compileSdk` y la version de
  `com.android.application` en el `build.gradle.kts` raiz segun lo que
  pida el mensaje de error.
- `Unresolved reference` en un archivo .kt: suele ser una importacion
  que falta. Dime el nombre exacto del simbolo que marca el error y lo
  corrijo.
- `SDK location not found`: no deberia pasar en GitHub Actions (el
  runner ya trae el Android SDK), pero si compilas en Termux si hace
  falta definir `ANDROID_HOME`.

**El APK no instala ("App no instalada")**
Normalmente es que ya existe una version anterior firmada distinta, o
que falta espacio. Desinstala la version anterior y reintenta.

**La app se cierra sola al abrir**
Conecta el telefono a si mismo via depuracion inalambrica (Ajustes >
Opciones de desarrollador > Depuracion inalambrica > Emparejar con
codigo) y desde Termux:

```bash
pkg install android-tools -y
adb pair 127.0.0.1:PUERTO_DE_EMPAREJAMIENTO
adb connect 127.0.0.1:PUERTO_DE_CONEXION
adb logcat *:E
```

Esto muestra el error real (stack trace) en vez de solo "la app se
detuvo". Pegamelo y lo diagnostico.

## 8. Alternativa de compilacion local (opcional, avanzada)

Si mas adelante quieres compilar sin depender de internet, en Termux:

```bash
pkg install openjdk-17 gradle -y
cd /storage/emulated/0/T9WAZA9Arcade
gradle assembleDebug --no-daemon
```

En un Redmi 15C de 4 GB esto puede ser lento o quedarse sin memoria; en
las variantes de 6-8 GB es mas viable. Por eso GitHub Actions es el
metodo recomendado, no este.

## 9. Limitaciones conocidas de esta fase

- Este codigo esta escrito y revisado a mano, pero no ha sido compilado
  ni ejecutado por mi en un dispositivo real (no tengo acceso a tu
  telefono ni a un entorno Android con red en este chat). La primera
  verificacion real ocurre cuando GitHub Actions lo compile y tu lo
  instales. Si algo falla, pégame el error exacto del log.
- HyperOS es agresivo cerrando apps en segundo plano. No afecta a esta
  fase (no hay nada corriendo en segundo plano todavia), pero sera
  importante desde la Fase 2: habra que pedir al usuario que excluya la
  app de la optimizacion de bateria para que el servicio de grabacion
  no se detenga solo.
