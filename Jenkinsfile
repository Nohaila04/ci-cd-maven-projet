pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo '=== BUILD ==='
                bat 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo '=== TEST ==='
                bat 'mvn test'
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
                bat 'mvn package -DskipTests'
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
                bat 'echo "Deploiement simule - WAR pret"'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline termine'
        }
        success {
            echo '✅ SUCCES'
        }
        failure {
            echo '❌ ECHEC'
        }
    }
}