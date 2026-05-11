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
            post {
                always {
                    // Le dossier target/surefire-reports sera créé après mvn test
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo '=== 3. CREATION DU WAR ==='
                bat 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.war'
                }
            }
        }
    }
    
    post {
        always {
            echo '=== PIPELINE TERMINE ==='
        }
        success {
            echo '✅ PIPELINE COMPLET REUSSI !'
        }
        failure {
            echo '❌ ECHEC DU PIPELINE'
        }
    }
}