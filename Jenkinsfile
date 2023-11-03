pipeline {

    agent any


    stages {
        stage ('Git Checkout') {
            steps {
               echo "Getting Project from Git";
                git branch: "HachichaMalak",
                    url: "https://github.com/Souhajaidi1/DevOps";
            }
        }

        stage("Build") {
            steps {
                sh "mvn clean compile"
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
                sh "docker build -t skistation ."
            }
        }


	stage('push in dockerhub') {
            steps {
                sh "docker login -u malakhachicha1998 -p Malak02061998"
                sh "docker push HachichaMalak/skistation:1.0.0"
            }
        }


	
	stage('run docker compose') {
            steps {
                sh "docker compose up "
            }
        }

	
	        
	stage("Unit Testing") {
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
