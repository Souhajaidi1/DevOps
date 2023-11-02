pipeline {

    agent any


    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git";
                git branch: "HachichaMalak",
                    url: "https://github.com/Souhajaidi1/DevOps";
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

        stage("Sonar") {
            steps {
                bat "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
            }
        }

        stage("SRC Analysis Testing") {
            steps {
                bat "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar
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
