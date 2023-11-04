pipeline {

    agent any


    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git";
                git branch: "ski_station_project",
                    url: "https://github.com/Souhajaidi1/DevOps";
            }
        }

        stage("Build") {
            steps {
                sh "mvn clean install"
            }
        }

        stage('Setup MySQL Connection') {
    steps {
        script {
            // Use MySQL client to connect to the running MySQL container
            sh 'mysql -h 172.18.0.2 -u root -e "USE SkiStationDB;"'
          }
        }
       }


        stage("SRC Analysis Testing") {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=adminsonar"
            }
        }

        stage("Build Docker image") {
            steps {
                sh "docker build -t souhajaidi/SkiStationProject:latest ."
            }
        }

        stage("Deploy Artifact to private registry") {
            steps {
        sh "docker login -u souhajaidi -p souha123+ registry_url"
        sh "docker tag SkiStationProject:latest souhajaidi/SkiStationProject:latest"
        sh "docker push souhajaidi/SkiStationProject:latest
            }
        }

        stage("Deploy Dokcer Image to private registry") {
            steps {
                 sh "sed -i 's|image: nom_image:tag|image: nom_utilisateur/nom_image:tag|g' docker-compose.yml"
    
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }

}
