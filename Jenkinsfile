pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo '=== COMPILATION MAVEN ==='
                bat 'mvn clean compile'
            }
        }
    }
    
    post {
        always {
            echo '=== PIPELINE TERMINE ==='
        }
        success {
            echo '✅ BUILD REUSSI !'
        }
        failure {
            echo '❌ BUILD ECHOUE !'
        }
    }
}