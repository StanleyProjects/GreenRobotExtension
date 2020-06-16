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
    commonConfig()

    buildTypes {
        create(BuildType.snapshot)
    }

    val mainSourceSets = sourceSets["main"]!!
    val applicationId = "stan.grobex.view"
    libraryVariants.all {
        check(this.applicationId == applicationId) {
            "Expected app id \"$applicationId\" but actual \"${this.applicationId}\"!"
        }
        outputs.forEach { output ->
            check(output is com.android.build.gradle.internal.api.LibraryVariantOutputImpl)
            val name = output.name
            val postfix = when (name) {
                BuildType.release -> ""
                else -> "-$name"
            }
            val versionName = Version.name + "-" + Version.Code.view + postfix
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
