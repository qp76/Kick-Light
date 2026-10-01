package com.kicklight

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Main application class for KickLight
 * Initializes Hilt dependency injection
 */
@HiltAndroidApp
class KickLightApplication : Application()
