
val subktRepo: String by settings

sourceControl {
    gitRepository(uri(subktRepo)) {
        producesModule("myaa:subkt")
    }
}

pluginManagement {
    val subktVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "myaa.subkt") {
                useModule("myaa:subkt:$subktVersion")
            }
        }
    }
}

buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }
}
