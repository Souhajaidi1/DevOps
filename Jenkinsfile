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
                sh "mvn clean compile -DskipTests"
            }
        }
	
	
        stage("SonarQube") {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
            }
        }
	

        stage("Build Docker image") {
            steps {
                sh "docker build -t malakhachicha1998/devops:skistation ."
            }
        }


	stage('Push in dockerhub') {
            steps {
                sh "docker login -u malakhachicha1998 -p Malak02061998"
                sh "docker push malakhachicha1998/devops:skistation"
            }
        }

		        
	stage("Docker compose") {
            steps {
                sh "docker compose up -d"
            }
        }

	stage('Nexus') {
            steps {
                sh "mvn clean compile -DskipTests"
            }
        }
	

	stage('Junit Mockito') {
            steps {
                sh "mvn clean"
            }
        }
	

	stage('Grafana Prometheus') {
            steps {
                sh ".............."
            }
        }

}

    post {
        always {
            cleanWs()
        }
    }

}
