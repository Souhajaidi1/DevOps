pipeline {
    agent any
 
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'ski-station', url: 'https://github.com/Souhajaidi1/DevOps'
            }
        }
        stage ("Build"){
          steps{
               sh 'mvn clean compile'
               }
             }
         stage('Setup MySQL Connection'){
           steps{
             script{
                 sh 'mysql -h 172.18.0.2 -u root -e "USE SkiStationDB;"
              }
            }
           }

         stage("Sonar"){
          steps {
                bat "mvn sonar:sonar"
            }
        }

        stage("SRC Analysis Testing") {
            steps {
                bat "mvn sonar:sonar"
            }
        }

        stage("Build Docker image") {
            steps {
                sh "..............."
            }
        }

        stage("Deploy Artifact to private registry") {
            steps {
                sh "..............."
            }
        }

        stage("Deploy Dokcer Image to private registry") {
            steps {
                sh "..............."
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
