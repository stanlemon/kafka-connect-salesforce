#!/usr/bin/env groovy

def versionNumber = "0.1.${env.BUILD_NUMBER}"
def mvnHome = tool 'M3'

def changeVersion (version) {
    if (env.BRANCH_NAME == 'master') {
        sh "${mvnHome}/bin/mvn -B versions:set -DgenerateBackupPoms=false -DnewVersion=${version}"
    }
}

node {
    checkout scm
    stage 'build'
    changeVersion(versionNumber)
    sh "${mvnHome}/bin/mvn -B -P maven-central clean verify package"

    junit '**/target/surefire-reports/TEST-*.xml'

    if (env.BRANCH_NAME == 'master') {
        stage 'publishing'
        sh "${mvnHome}/bin/mvn -B -P github,maven-central deploy"
    }
}