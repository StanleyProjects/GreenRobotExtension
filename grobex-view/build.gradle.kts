plugins {
    applyAll(
        Plugin.androidLibrary,
        Plugin.kotlinAndroid
    )
}

android {
    commonConfig()
}

dependencies {
    implementation(Dependency.kotlinStdlib)
}
