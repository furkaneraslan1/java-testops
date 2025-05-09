pipeline {
    agent any

    stages {
        stage('Build & Unit Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Analyze Logs') {
            steps {
                // Run your Groovy log analyzer
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