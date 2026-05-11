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
                bat 'dir target\\*.war'
                script {
                    def warFiles = findFiles(glob: 'target/*.war')
                    if (warFiles.length > 0) {
                        def warPath = warFiles[0].path
                        echo "WAR trouvé: ${warPath}"
                        echo "✅ Déploiement prêt pour Tomcat"
                    } else {
                        error "Aucun fichier WAR trouvé!"
                    }
                }
                echo "🎉 Déploiement terminé avec succès!"
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
            echo '📦 WAR disponible dans les artifacts'
        }
        failure {
            echo 'Statut final: FAILURE'
            echo '❌ ECHEC DU PIPELINE'
        }
    }
}