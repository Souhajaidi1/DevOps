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
                sh 'mvn clean install'
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
    }
}
