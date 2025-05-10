pipeline {
    agent any

    stages {
        stage('Unit Tests') {
            steps {
                sh 'mvn -Dtest=HelloTest test'
            }
        }

        stage('Integration Tests') {
            steps {
                sh 'mvn -Dtest=IntegrationTest test'
            }
        }

        stage('UI Tests') {
            steps {
                sh 'mvn -Dtest=UiSimulationTest test'
            }
        }

        stage('Analyze Logs') {
            steps {
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
