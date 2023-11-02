pipeline {

    agent any


    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                git branch: "aminesnoussii", 
                    url: "https://github.com/Souhajaidi1/DevOps";
            }
        }
       
        stage("Build") {
            steps {
                sh "mvn clean install"
            }
        }

        stage('Setup MySQL Container') {
            steps {
                script {
                    // Pull the MySQL image from Docker Hub
                    sh 'sudo docker pull mysql:latest'
                    
                    // Run the MySQL container with the desired configuration
                    sh 'sudo docker run -d --name my-mysql-container --network devops -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_DATABASE=SkiStationDB mysql:latest'
                    
                    // Wait for the MySQL container to start (adjust the timeout as needed)
                    sh 'sudo docker exec my-mysql-container mysqladmin --silent --wait=30 -hlocalhost -uroot ping || exit 1'
                }
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
            sh 'sudo docker stop my-mysql-container'
            sh 'sudo docker rm my-mysql-container'
        }
    }
    
}
