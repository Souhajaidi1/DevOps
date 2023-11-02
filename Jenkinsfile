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
                sh "mvn -version"
                bat "mvn clean install verify"
            }
        }
       
        stage("SRC Analysis Testing") {
            steps {
                bat "mvn sonar:sonar"
            }
        }
        
        stage("Build Docker image") {
            steps {
                //sh "..............."
            }
        }

        stage("Deploy Artifact to private registry") {
            steps {
                //sh "..............."
            }
        }

        stage("Deploy Dokcer Image to private registry") {
            steps {
                //sh "..............."
            }
        }
    }
   
    post {
        always {
            cleanWs()
        }
    }
    
}
