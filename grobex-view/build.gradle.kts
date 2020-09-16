plugins {
    applyAll(
        Plugin.androidLibrary,
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

android {
    commonConfig {
        versionName = Version.Name.view
        versionCode = VersionUtil.codeByName(versionName)
    }

    buildTypes {
        create(BuildType.snapshot)
    }

    val mainSourceSets = sourceSets["main"]!!
    val applicationId = Application.Id.view
    libraryVariants.all {
        check(this.applicationId == applicationId) {
            "Expected app id \"$applicationId\" but actual \"${this.applicationId}\"!"
        }
        outputs.forEach { output ->
            check(output is com.android.build.gradle.internal.api.LibraryVariantOutputImpl)
            val name = output.name
            val versionName = listOfNotNull(
                Version.Name.view,
                when (name) {
                    BuildType.release -> null
                    else -> name
                }
            ).joinToString(separator = "-")
            output.outputFileName = getOutputFileName(
                applicationId = applicationId,
                versionName = versionName,
                fileExtension = "aar"
            )
            //
            task<Jar>("assemble${name.capitalize()}Source") {
                archiveBaseName.set(applicationId)
                archiveVersion.set(versionName)
                archiveClassifier.set("sources")
                from(mainSourceSets.java.srcDirs)
            }
            task("assemble${name.capitalize()}Pom") {
                doLast {
                    val parent = File(buildDir, "libs")
                    if (!parent.exists()) parent.mkdirs()
                    val file = File(parent, getOutputFileName(
                        applicationId = applicationId,
                        versionName = versionName,
                        fileExtension = "pom"
                    ))
                    if (file.exists()) file.delete()
                    file.createNewFile()
                    checkFileExists(file)
                    val text = MavenUtil.pom(
                        modelVersion = "4.0.0",
                        groupId = "stanleyprojects.GreenRobotExtension", // todo
                        artifactId = applicationId,
                        version = versionName,
                        packaging = "aar"
                    )
                    file.writeText(text)
                }
            }
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
}
