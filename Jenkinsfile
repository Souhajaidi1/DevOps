pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                echo "Getting Project from Git"
                git branch: 'Oussemahmaied', url: 'https://github.com/Souhajaidi1/DevOps'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
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
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
            }
        }

        stage("Build Docker image") {
            steps {
                sh "docker build -t ousshmaied/devops:skistation ."
            }
        }
      

        stage("push in dockerhub") {
            steps {
                 sh "docker login -u ousshmaied -p basket1234"
                sh "docker push ousshmaied/devops:skistation"
            }
        }

           stage("Docker compose") {
            steps {   sh "docker login -u ousshmaied -p basket1234"
                sh "docker compose up -d"
            }
        }
}
    post {
        always {
            cleanWs()
        }
    
    }
}

