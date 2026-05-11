pipeline {
    agent any
    
    
    
    stages {
        stage('Build') {
            steps {
                echo '=== BUILD ==='
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo '=== TEST ==='
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo '=== PACKAGE ==='
                sh 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.war'
                }
            }
        }
        
        stage('Deploy') {
            steps {
                echo '=== DEPLOY ==='
                sh 'echo "Déploiement simulé - Le WAR est prêt"'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline terminé'
        }
        success {
            echo '✅ SUCCÈS'
        }
        failure {
            echo '❌ ÉCHEC'
        }
    }
}