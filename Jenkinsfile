pipeline {
    agent any
 
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'ski_station_project', url: 'https://github.com/Souhajaidi1/DevOps'
            }
        }
        stage ("Build"){
          steps{
               sh 'mvn clean install'
               }
             }
         stage('Setup MySQL Connection'){
           steps{
             script{
                 sh 'mysql -h 172.18.0.2 -u root -e "USE SkiStationDB;"'
              }
            }
           }

         stage("Sonar"){
          steps {
                sh "mvn sonar:sonar"
            }
        }

        stage("SRC Analysis Testing") {
            steps {
                sh "mvn sonar:sonar"
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
