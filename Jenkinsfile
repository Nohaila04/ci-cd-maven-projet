pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo '=== 1. COMPILATION ==='
                bat 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo '=== 2. EXECUTION DES TESTS ==='
                bat 'mvn test'
            }
            // Supprimez la partie "post" pour l'instant
        }
    }
    
    post {
        always {
            echo '=== PIPELINE TERMINE ==='
        }
        success {
            echo '✅ BUILD + TEST REUSSIS !'
        }
        failure {
            echo '❌ ECHEC (Build ou Test)'
        }
    }
}