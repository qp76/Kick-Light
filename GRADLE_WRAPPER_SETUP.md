# Gradle Wrapper Setup

## Important: Gradle Wrapper JAR

The Gradle Wrapper includes a `gradle-wrapper.jar` file in `gradle/wrapper/` directory. This file is **required** for building the project.

### If You Downloaded This Project

The `gradle-wrapper.jar` should already be included in the ZIP file at:
```
gradle/wrapper/gradle-wrapper.jar
```

If it's missing, you can regenerate it:

#### On Mac/Linux:
```bash
./gradlew wrapper
```

#### On Windows:
```cmd
gradlew.bat wrapper
```

This will download and set up the proper Gradle wrapper JAR.

### What is Gradle Wrapper?

The Gradle Wrapper ensures everyone uses the same version of Gradle (8.6 in this project) regardless of their system, making builds consistent and reproducible.

### File Structure

```
gradle/
├── wrapper/
│   ├── gradle-wrapper.jar          ← REQUIRED: DO NOT DELETE
│   ├── gradle-wrapper.properties   ← Settings for wrapper
gradlew                             ← Unix/Mac script
gradlew.bat                         ← Windows script
```

### Codemagic Build

If building on Codemagic, it will automatically:
1. Use the included `gradle-wrapper.jar`
2. Download Gradle 8.6 if needed
3. Build your APK

No additional setup is needed!

### Local Development

If building locally:

```bash
# Make gradlew executable (Mac/Linux only)
chmod +x gradlew

# Build debug APK
./gradlew assembleDebug

# Or on Windows
gradlew.bat assembleDebug
```

### Gradle Version

- **Gradle**: 8.6 (latest stable)
- **Java**: 17+
- **Kotlin**: 1.9.22
- **Android Plugin**: 8.2.0
- **Compose Compiler**: 1.5.10

All versions are pinned in `gradle/libs.versions.toml` for consistency.

---

If you encounter any gradle-related errors, ensure the `gradle-wrapper.jar` file exists in the project before building.
