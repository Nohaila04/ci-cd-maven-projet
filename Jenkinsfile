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
        echo '=== 4. DEPLOIEMENT REEL SUR TOMCAT ==='
        
        // Lister les fichiers disponibles
        bat 'dir target\\*.war'
        
        script {
            def warFiles = findFiles(glob: 'target/*.war')
            if (warFiles.length > 0) {
                def warPath = warFiles[0].path
                def warName = new File(warPath).getName()
                echo "Déploiement de: ${warName}"
                
                // ⭐ DÉPLOIEMENT RÉEL VERS TOMCAT ⭐
                // Copie simple du WAR vers webapps
                bat "copy \"${warPath}\" C:\\tomcat\\webapps\\app.war"
                
                echo "✅ Application déployée sur Tomcat!"
                echo "🌐 Accédez à: http://localhost:8080/app"
            } else {
                error "Aucun fichier WAR trouvé!"
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
            echo '📦 WAR disponible dans les artifacts'
        }
        failure {
            echo 'Statut final: FAILURE'
            echo '❌ ECHEC DU PIPELINE'
        }
    }
}