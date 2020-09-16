import java.util.Properties

plugins {
    applyAll(
        Plugin.androidApplication,
        Plugin.kotlinAndroid
    )
}

fun getOutputFileName(
    applicationId: String,
    versionName: String,
    fileExtension: String
): String {
    return applicationId +
        "-" + versionName +
        "." + fileExtension
}

val appName = "Green robot extension sample"
val androidProjectName = name

android {
    commonConfig {
        applicationId = Application.Id.sample
        versionName = Version.Name.sample
        versionCode = VersionUtil.codeByName(versionName)
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }

    signingConfigs {
        val signingProperties = Properties().also {
            File(projectDir, "signing.properties").inputStream().use(it::load)
        }
        getByName(BuildType.debug) {
            val storeFileName = "$name.jks"
            val storePasswordKey = "DEBUG_STORE_PASSWORD"
            val keyAliasKey = "DEBUG_KEY_ALIAS"
            val keyPasswordKey = "DEBUG_KEY_PASSWORD"
            try {
                storeFile = file(storeFileName)
                storePassword = signingProperties[storePasswordKey] as String
                keyAlias = signingProperties[keyAliasKey] as String
                keyPassword = signingProperties[keyPasswordKey] as String
            } catch (ignored: Throwable) {
                error(
                    """
                        You should:
                        - use $storeFileName file
                        - define $storePasswordKey, $keyAliasKey and $keyPasswordKey in signing.gradle.kts
                    """.trimIndent()
                )
            }
        }
    }

    buildTypes {
        getByName(BuildType.debug) {
            signingConfig = signingConfigs.getByName(name)
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".$name"
            versionNameSuffix = "-$name"
            manifestPlaceholders["appNamePrefix"] = appName
            manifestPlaceholders["appNameBuildTypeSuffix"] = " $name"
        }
    }

    applicationVariants.all {
        outputs.forEach { output ->
            check(output is com.android.build.gradle.internal.api.ApkVariantOutputImpl)
            output.versionCodeOverride = versionCode
            val outputFileName = getOutputFileName(
                applicationId = applicationId,
                versionName = versionName!!,
                fileExtension = "apk"
            )
            output.outputFileName = outputFileName
        }
    }
}

dependencies {
    implementationProject(
        ":grobex-view"
    )

    implementation(kotlin("stdlib"))
}
