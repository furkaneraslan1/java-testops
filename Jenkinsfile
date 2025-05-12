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

        stage('Generate QA Summary') {
            steps {
                sh 'groovy scripts/test_summary.groovy'
            }
        }

        stage('Generate HTML Report') {
            steps {
                sh 'groovy scripts/summary_to_html.groovy'
            }
        }

        stage('Rotate Service Account Passwords') {
            steps {
                sh 'groovy scripts/password_rotator.groovy'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'reports/test_summary.html', fingerprint: true
        }
    }
}
