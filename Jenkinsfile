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
                    def mysqlContainer

                    try {
                        // Run the MySQL container with the desired configuration
                        mysqlContainer = docker.image('mysql:latest').withRun('-e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_DATABASE=SkiStationDB --network devops')

                        // Wait for the MySQL container to start (adjust the timeout as needed)
                        sh 'docker exec my-mysql-container mysqladmin --silent --wait=30 -hlocalhost -uroot ping || exit 1'
                    } finally {
                        // Clean up the MySQL container
                        if (mysqlContainer) {
                            mysqlContainer.stop()
                            mysqlContainer.remove(force: true)
                        }
                    }
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
        }
    }
    
}
