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
	

	stage('MySQL Connexion') {
                steps {
                        script {
                        // Use MySQL client to connect to the running MySQL container
                        
			sh 'docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:8 -v /var/lib/mysql'
                        }
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

	
	stage('Nexus') {
            steps {
                sh ".............."
            }
        }

	
	        
	stage("Unit Testing") {
            steps {
                sh ".................."
            }
        }

}

    post {
        always {
            cleanWs()
        }
    }

}
