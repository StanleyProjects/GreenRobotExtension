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
        "grobex-sample/src/main/java/**/*.kt",
        "grobex-sample/build.gradle.kts",
        "--reporter=html,output=$analysisStyleHtmlPath"
    )
}

task<DefaultTask>("verifyReadme") {
    doLast {
        val file = File(rootDir, "README.md")
        val text = file.requireFilledText()
        val projectCommon = Markdown.table(
            heads = listOf("Android project common", "version"),
            dividers = listOf("-", "-:"),
            rows = listOf(
                listOf("compile sdk", "`${Version.Android.compileSdk}`"),
                listOf("build tools", "`${Version.Android.buildTools}`"),
                listOf("min sdk", "`${Version.Android.minSdk}`"),
                listOf("target sdk", "`${Version.Android.targetSdk}`")
            )
        )
        listOf(projectCommon).forEach {
            if(!text.contains(it)) error("File by path ${file.absolutePath} must contains \"$it\"!")
        }
        val lines = text.split(SystemUtil.newLine)
        val versionBadge = Markdown.image(
            text = "version",
            url = badgeUrl(
                label = "version",
                message = Version.Application.name +"-"+ Version.Application.code,
                color = "2962ff"
            )
        )
        listOf(versionBadge).forEach {
            if(!lines.contains(it)) error("File by path ${file.absolutePath} must contains \"$it\" line!")
        }
    }
}

task<JavaExec>("ktlint") {
    classpath = kotlinLint
    main = "com.pinterest.ktlint.Main"
}

task<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}

task<DefaultTask>("applicationVersionName") {
    doLast {
        println(Version.Application.name)
    }
}
task<DefaultTask>("applicationVersionCode") {
    doLast {
        println(Version.Application.code)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
