#!/usr/bin/env groovy
@Library("jenkins-pipeline") import com.github.jcustenborder.jenkins.pipeline.MavenUtilities
def mavenUtils = new MavenUtilities(steps)

node {
    def versionNumber = "0.1.${env.BUILD_NUMBER}"
    def mvnHome = tool 'M3'

    checkout scm
    stage 'build'
    mavenUtils.changeVersion(mvnHome, versionNumber)
    sh "${mvnHome}/bin/mvn -B -P maven-central clean verify package"

    junit '**/target/surefire-reports/TEST-*.xml'

    if (env.BRANCH_NAME == 'master') {
        stage 'publishing'
        sh "${mvnHome}/bin/mvn -B -P github,maven-central deploy"
    }
}