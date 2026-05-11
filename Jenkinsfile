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
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        // ⭐ AJOUTEZ CE STAGE PACKAGE ⭐
        stage('Package') {
            steps {
                echo '=== 3. CREATION DU WAR ==='
                bat 'mvn package -DskipTests'
            }
            post {
                success {
                    echo '=== ARCHIVAGE DU WAR ==='
                    archiveArtifacts artifacts: 'target/*.war'
                }
            }
        }
        
        stage('Deploy to Tomcat') {
            steps {
                echo '=== 4. DEPLOIEMENT SUR TOMCAT ==='
                script {
                    // Lister les fichiers dans target pour déboguer
                    bat 'dir target\\*.war'
                    
                    // Récupérer le chemin du WAR
                    def warFile = findFiles(glob: 'target/*.war')[0].path
                    def warName = new File(warFile).getName()
                    
                    echo "Déploiement de: ${warName}"
                    
                    // Simuler le déploiement (pour l'instant)
                    echo "✅ Déploiement simulé réussi!"
                }
            }
        }
    }
    
    post {
        always {
            echo '=== PIPELINE TERMINE ==='
        }
        success {
            echo 'Statut final: SUCCESS'
            echo '✅ PIPELINE COMPLET REUSSI !'
        }
        failure {
            echo 'Statut final: FAILURE'
            echo '❌ ECHEC DU PIPELINE'
        }
    }
}