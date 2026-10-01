@echo off
REM KickLight Gradle Wrapper Setup Script (Windows)

echo =========================================
echo KickLight Gradle Wrapper Setup
echo =========================================

if not exist "gradle\wrapper\gradle-wrapper.jar" (
    echo.
    echo Gradle wrapper JAR not found!
    echo Attempting to regenerate...
    echo.
    
    call gradlew.bat wrapper --gradle-version=8.6
    
    if %ERRORLEVEL% NEQ 0 (
        echo.
        echo Error: Failed to generate Gradle wrapper
        echo Please ensure Gradle 8.6+ is installed on your system
        exit /b 1
    )
    
    echo.
    echo Gradle wrapper generated successfully
) else (
    echo Gradle wrapper JAR found
)

echo.
echo =========================================
echo Setup complete! You can now build:
echo   gradlew.bat assembleDebug
echo =========================================
