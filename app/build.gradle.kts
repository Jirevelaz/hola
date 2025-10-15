@file:SuppressLint("TestManifestGradleConfiguration")
@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import android.annotation.SuppressLint
import com.android.build.api.dsl.ApplicationExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    `pacemaker-application`
    org.jetbrains.compose
    org.jetbrains.kotlin.plugin.compose
    id("org.jetbrains.compose.hot-reload")
}

pacemaker {
    android()
    jvm()
}

extensions.configure(ApplicationExtension::class) {
    namespace = "io.sellmair.pacemaker"

    defaultConfig {
        versionName = "2025.1"
        versionCode = 17
    }
}

kotlin {
    compilerOptions {
        optIn.add("org.jetbrains.compose.resources.ExperimentalResourceApi")
    }

    sourceSets.commonMain.get().dependencies {
        implementation(project(":app-core"))

        implementation(deps.evas.compose)

        /* COMPOSE */
        implementation(compose.ui)
        implementation(compose.foundation)
        implementation(compose.runtime)
        implementation(compose.components.resources)
        implementation(compose.components.uiToolingPreview)

        implementation(compose.material3)
        implementation(compose.materialIconsExtended)

    }

    sourceSets.jvmMain.dependencies {
        implementation(compose.desktop.currentOs)
        implementation(deps.coroutines.swing)
    }

    sourceSets.androidMain.get().dependencies {
        /* androidx */
        implementation("androidx.activity:activity-compose:1.9.3")
        implementation(compose.preview)
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    sourceSets.invokeWhenCreated("androidDebug") {
        dependencies {
            implementation(compose.uiTooling)
        }
    }

    sourceSets.getByName("androidInstrumentedTest").dependencies {
        implementation("androidx.compose.ui:ui-test-junit4:1.7.6")
    }
}
