pipeline {
    agent any
 
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'Oussemahmaied', url: 'https://github.com/Souhajaidi1/DevOps'
            }
        }
        stage ("Build"){
          steps{
               sh"mvn-version"
               bat"mvn clean package -DskipTests"
  
             }
  
        }
        stage('MySQL Connexion') {
             steps {
        script {
        // Use MySQL client to connect to the running MySQL container

                sh 'docker run -d --name my-sql-container --network devops -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_DATABASE=SkiStationDB mysql:latest'
               }
          }
}

        stage('Test') {
            steps {
                // Étape pour exécuter des tests automatisés
            }
        }

        stage('Deploy') {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
            steps {
                // Étape pour le déploiement du projet (dans un environnement de test ou de production)
            }
        }
}
}
