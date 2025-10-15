plugins {
    id("pacemaker-library")
}

pacemaker {
    android()
    macos()
    jvm()
}

kotlin {
    sourceSets.commonMain.dependencies {
        api(project(":bluetooth-core"))
        implementation(Dependencies.okio)
    }

    sourceSets.androidMain.dependencies {
        implementation("androidx.annotation:annotation:1.7.0")
    }
}
