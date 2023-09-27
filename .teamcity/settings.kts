import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.buildSteps.nuGetPack
import jetbrains.buildServer.configs.kotlin.projectFeatures.activeStorage
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.s3Storage
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.05"

project {

    buildType(Build)

    features {
        awsConnection {
            id = "AmazonWebServicesAws"
            name = "Amazon Web Services (AWS)"
            regionName = "eu-west-1"
            credentialsType = static {
                accessKeyId = "AKIA5JH2VERVM3SVCDKS"
                secretAccessKey = "credentialsJSON:8e1b8901-5cc8-4978-bda6-940fff80a308"
                stsEndpoint = "https://sts.eu-west-1.amazonaws.com"
            }
        }
        s3Storage {
            id = "PROJECT_EXT_20"
            awsEnvironment = default {
                awsRegionName = "eu-west-1"
            }
            connectionId = "AmazonWebServicesAws"
            bucketName = "ollven-test"
            forceVirtualHostAddressing = true
            verifyIntegrityAfterUpload = true
        }
        activeStorage {
            id = "PROJECT_EXT_21"
            activeStorageID = "PROJECT_EXT_20"
        }
    }
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        nuGetInstaller {
            id = "jb_nuget_installer"
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "CalcProject/CalcProject.sln"
            updatePackages = updateParams {
            }
        }
        nuGetPack {
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            toolPath = "%teamcity.tool.NuGet.CommandLine.6.7.0%"
            paths = "CalcProject/CalcProject.nuspec"
            outputDir = "nuget-pack-out"
            cleanOutputDir = false
            publishPackages = true
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})
