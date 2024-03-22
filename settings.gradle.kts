pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://jitpack.io")
    }

    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
            jcenter()
            maven(url = "https://jitpack.io")
            maven(url = "https://maven.google.com")

            maven {
                url = uri("https://artifactory.apero.vn/artifactory/gradle-release/")
                credentials {
                    username = "xuyen-zohaib"
                    password = "zohaib@123"
                }
            }
            maven(url = "https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea")
            maven(url = "https://artifact.bytedance.com/repository/pangle")
        }
    }
}

rootProject.name = "NigerianBankCodes"
include(":app")
 