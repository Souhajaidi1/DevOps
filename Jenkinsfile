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
                sh "mvn clean install -DskipTests"
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
                sh "mvn clean test"
            }
        }
	

	stage('Grafana Prometheus') {
            steps {
                // Commandes pour démarrer Grafana et Prometheus
        	sh "docker run -d --name=grafana -p 3000:3000 grafana/grafana"
        	sh "docker run -d --name=prometheus -p 9090:9090 prom/prometheus"

        	// Configurer Prometheus avec des cibles de scraping
        	sh "curl -X POST -H 'Content-Type: application/json' -d '{\"targets\":[\"<target_IP>:<target_port>\"]}' http://192.168.33.10:9090/api/v1/targets"

        	// Configurer Grafana pour se connecter à Prometheus
        	sh "curl -X POST -u admin:admin http://192.168.33.10:3000/api/datasources -H 'Content-Type: application/json' --data-binary '{\"name\":\"Prometheus\",\"type\":\"prometheus		\",\"url\":\"http://192.168.33.10:9090\",\"access\":\"proxy\",\"basicAuth\":false}'"
            }
        }

}

    post {
        always {
            cleanWs()
        }
    }

}
