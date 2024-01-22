package patches.projects

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project
import jetbrains.buildServer.configs.kotlin.projectFeatures.AwsConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.S3Storage
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.s3Storage
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the root project
accordingly, and delete the patch script.
*/
changeProject(DslContext.projectId) {
    features {
        val feature1 = find<AwsConnection> {
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
        }
        feature1.apply {
            name = "Amazon Web Services (AWS)Edited"
            param("awsSessionDuration", "")
            param("awsAllowedInSubProjects", "")
        }
        val feature2 = find<S3Storage> {
            s3Storage {
                id = "PROJECT_EXT_20"
                awsEnvironment = default {
                    awsRegionName = "eu-west-1"
                }
                connectionId = "AmazonWebServicesAws"
                bucketName = "ollven-test"
                forceVirtualHostAddressing = true
            }
        }
        feature2.apply {
        }
    }
}
