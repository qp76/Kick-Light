# AndroidX
-keep class androidx.** { *; }
-dontwarn androidx.**

# Jetpack Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Kotlin
-keep class kotlin.** { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}

# Kotlinx Serialization
-keep,includedescriptorclasses class com.kicklight.**$$serializer { *; }
-keepclassmembers class com.kicklight.** {
    *** Companion;
}
-keepclasseswithmembers class com.kicklight.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Retrofit
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-keepattributes Signature
-keepattributes *Annotation*

# OkHttp
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-dontwarn dagger.hilt.**

# Media3
-keep class androidx.media3.** { *; }
-dontwarn androidx.media3.**

# Coil
-keep class coil.** { *; }
-dontwarn coil.**

# Room
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# Your app classes
-keep class com.kicklight.** { *; }
-keepclassmembers class com.kicklight.** {
    public <methods>;
}
