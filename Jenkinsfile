pipeline {
    agent any
    
    stages {
        // ===== STAGE 1: BUILD =====
        stage('Build') {
            steps {
                echo '=== 1. COMPILATION ==='
                bat 'mvn clean compile'
            }
        }
        
        // ===== STAGE 2: TEST =====
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
        
        
        
        // ===== STAGE 4: DEPLOY sur TOMCAT =====
        stage('Deploy to Tomcat') {
            steps {
                echo '=== 4. DEPLOIEMENT SUR TOMCAT ==='
                script {
                    // Récupérer le nom du fichier WAR
                    def warFile = findFiles(glob: 'target/*.war')[0].path
                    def warName = new File(warFile).getName()
                    
                    echo "Fichier WAR: ${warName}"
                    
                    // Vérifier que Tomcat est accessible
                    bat 'curl -s -o nul -w "%{http_code}" http://localhost:8080/manager/text/list'
                    
                    // Déployer via l'API Tomcat Manager
                    def deployUrl = "http://localhost:8080/manager/text/deploy?path=/monapp&update=true"
                    bat "curl -u jenkins:jenkins123 --upload-file ${warFile} \"${deployUrl}\""
                    
                    echo "✅ Déploiement terminé !"
                }
            }
            post {
                success {
                    echo '🎉 Application déployée avec succès !'
                    echo '🌐 http://localhost:8080/monapp'
                }
                failure {
                    echo '❌ Échec du déploiement Tomcat'
                }
            }
        }
    }
    
    // ===== POST PIPELINE =====
    post {
        always {
            echo '=== PIPELINE TERMINE ==='
            echo "Statut final: ${currentBuild.result}"
        }
        success {
            echo '✅  PIPELINE COMPLET REUSSI !'
            echo '🏁 Application disponible sur http://localhost:8080/monapp'
        }
        failure {
            echo '❌ ECHEC DU PIPELINE'
            echo '🔍 Vérifiez que Tomcat est en cours d\'exécution'
        }
    }
}