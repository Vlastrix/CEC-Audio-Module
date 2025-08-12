# 🎵 CEC Audio Module

CEC Audio Module es una aplicación Android tipo **soundboard**, desarrollada en **Kotlin** con **Android Studio**, que permite reproducir sonidos predefinidos de forma rápida y con un diseño moderno.  
Esta es la **versión 1.0**, enfocada en ofrecer una experiencia fluida con sonidos estáticos precargados.

---

## ✨ Características

- **Reproducción instantánea**: gracias a la precarga (`prewarm`) de sonidos con `MediaPlayerEngine`.
- **Soporte de múltiples sonidos simultáneos** (`allowOverlap`).
- **Diseño moderno**:
  - Items en tarjetas redondeadas con **borde celeste**.
  - **Ripple effect** al presionar.
  - Animación de escala y **haptic feedback** para una respuesta táctil profesional.
- **Soporte para Android 11+** con **SplashScreen API** (compatibilidad gracias a `androidx.core.splashscreen`).
- **Layout adaptativo** con `GridLayoutManager` y `ItemDecoration` para espaciado uniforme.
- **Icono e identidad visual personalizada**:
  - Logo propio optimizado para app icon.
  - Tema oscuro por defecto con colores personalizados (`cec_bg`, `cec_accent`).

---

## 🛠 Tecnologías y Librerías

- **Kotlin**
- **AndroidX**
  - `RecyclerView`
  - `CardView`
  - `ConstraintLayout`
  - `SplashScreen API` (`androidx.core:core-splashscreen`)
- **Material Components**
- **MediaPlayer** (para reproducir archivos MP3)
- **ViewBinding**

---

## 📂 Estructura del proyecto
app/
├─ java/com/example/cecaudiomodule/
│ ├─ ui/activities/MainActivity.kt # Pantalla principal + inicialización
│ ├─ ui/adapters/SoundAdapter.kt # Adaptador para los ítems del soundboard
│ ├─ utils/MediaPlayerEngine.kt # Lógica para reproducir sonidos
│ ├─ models/SoundItem.kt # Modelo de datos de sonido
│ └─ repositories/SoundRepository.kt # Lista estática de sonidos precargados
│
└─ res/
├─ layout/activity_main.xml # Layout principal
├─ layout/item_sound.xml # Layout de cada tarjeta de sonido
├─ drawable/item_border.xml # Borde celeste de los ítems
├─ mipmap/ # Iconos de la app
└─ values/themes.xml # Temas y estilos (incluyendo Splash)


---

## 🚀 Instalación y ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tuusuario/cec-audio-module.git

    Abrir el proyecto en Android Studio.

    Asegurarse de tener:

        compileSdk = 35

        targetSdk = 35

        minSdk = 30 (versión actual)

    Ejecutar en un emulador o dispositivo físico con Android 11+.

📋 Plan para próximas versiones

    Base de datos Room para almacenar sonidos personalizados.

    Agregar sonidos desde el almacenamiento usando SAF.

    Renombrar sonidos con long-press.

    Control de volumen individual por sonido.

    Categorías y ordenamiento con drag & drop.

    Respaldo y restauración de configuración de usuario.

📜 Licencia
Proyecto personal, todos los derechos reservados. El código puede ser usado como referencia para proyectos similares.
