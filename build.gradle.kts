buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpathAll(
            Dependency.androidToolsBuildGradle,
            Dependency.kotlinGradlePlugin
        )
    }
}

val kotlinLint: Configuration by configurations.creating

dependencies {
    kotlinLint(Dependency.kotlinLint.notation())
}

val reportsPath = "${rootProject.buildDir}/reports"
val analysisPath = "$reportsPath/analysis"
val analysisStylePath = "$analysisPath/style"
val analysisStyleHtmlPath = "$analysisStylePath/html/report.html"

task<JavaExec>("verifyCodeStyle") {
    classpath = kotlinLint
    main = "com.pinterest.ktlint.Main"
    args(
        "build.gradle.kts",
        "settings.gradle.kts",
        "buildSrc/src/main/kotlin/**/*.kt",
        "buildSrc/build.gradle.kts",
        "grobex-view/src/main/kotlin/**/*.kt",
        "grobex-view/build.gradle.kts",
        "grobex-sample/src/main/kotlin/**/*.kt",
        "grobex-sample/build.gradle.kts",
        "--reporter=html,output=$analysisStyleHtmlPath"
    )
}

task<DefaultTask>("verifyReadme") {
    doLast {
        val file = File(rootDir, "README.md")
        val text = file.requireFilledText()
        val projectCommon = MarkdownUtil.table(
            heads = listOf("Android project common", "version"),
            dividers = listOf("-", "-:"),
            rows = listOf(
                listOf("build gradle", "`${Version.Android.toolsBuildGradle}`"),
                listOf("compile sdk", "`${Version.Android.compileSdk}`"),
                listOf("build tools", "`${Version.Android.buildTools}`"),
                listOf("min sdk", "`${Version.Android.minSdk}`"),
                listOf("target sdk", "`${Version.Android.targetSdk}`")
            )
        )
        listOf(projectCommon).forEach {
            check(text.contains(it)) { "File by path ${file.absolutePath} must contains \"$it\"!" }
        }
        val bintray = setOf(
            "view"
        ).map {
            val project = "stanleyprojects/GreenRobotExtension/stan.grobex.$it"
            "[" +
                "![Download](https://api.bintray.com/packages/$project/images/download.svg)" +
            "](https://bintray.com/$project/_latestVersion)"
        }
        val versions = setOf(
            "grobex-sample" to Version.Name.sample,
            "grobex-view" to Version.Name.view
        ).map { (label, value) ->
            MarkdownUtil.image(
                text = label,
                url = badgeUrl(
                    label = label,
                    message = value.toString(),
                    color = "2962ff"
                )
            )
        }
        val lines = text.split(SystemUtil.newLine)
        (versions + bintray).forEach {
            check(lines.contains(it)) { "File by path ${file.absolutePath} must contains \"$it\" line!" }
        }
    }
}

task<DefaultTask>("verifyService") {
    doLast {
        val file = File(rootDir, "buildSrc/build.gradle.kts")
        val text = file.requireFilledText()
        listOf(Dependency.androidToolsBuildGradle.notation()).forEach {
            check(text.contains(it)) { "File by path ${file.absolutePath} must contains \"$it\"!" }
        }
    }
}

task<DefaultTask>("verifyLicense") {
    doLast {
        val file = File(rootDir, "LICENSE")
        val text = file.requireFilledText()
        // todo
    }
}

task<DefaultTask>("verifyAll") {
    dependsOn(setOf(
        "CodeStyle",
        "Readme",
        "Service",
        "License"
    ).map { "verify$it" })
}

task<Delete>("clean") {
    delete = setOf(rootProject.buildDir, File(rootDir, "buildSrc/build"))
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
