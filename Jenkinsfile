pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-21'
            args '-v $HOME/.m2:/root/.m2'
        }
    }

    stages {
        stage('Build & Unit Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Analyze Logs') {
            steps {
                sh 'apt-get update && apt-get install -y groovy' // install groovy temporarily
                sh 'groovy src/main/groovy/com/testops/XmakeLogAnalyzer.groovy'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
