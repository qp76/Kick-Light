#!/bin/bash

# KickLight Gradle Wrapper Setup Script
# This script ensures the Gradle wrapper JAR is properly set up

echo "========================================="
echo "KickLight Gradle Wrapper Setup"
echo "========================================="

# Check if gradle wrapper jar exists
if [ ! -f "gradle/wrapper/gradle-wrapper.jar" ]; then
    echo "⚠️  gradle-wrapper.jar not found!"
    echo "Regenerating Gradle wrapper..."
    
    # Make gradlew executable
    chmod +x gradlew
    
    # Generate wrapper (requires Gradle to be installed)
    ./gradlew wrapper --gradle-version=8.6
    
    if [ $? -eq 0 ]; then
        echo "✅ Gradle wrapper generated successfully"
    else
        echo "❌ Failed to generate Gradle wrapper"
        echo "Please ensure Gradle 8.6+ is installed on your system"
        exit 1
    fi
else
    echo "✅ Gradle wrapper JAR found"
fi

# Make sure gradlew scripts are executable
chmod +x gradlew

echo "========================================="
echo "Setup complete! You can now build:"
echo "  ./gradlew assembleDebug"
echo "========================================="
