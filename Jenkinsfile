pipeline {

    agent any


    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                git branch: "Anis", 
                    url: "https://github.com/Souhajaidi1/DevOps.git";
            }
        }
        stage("Run Tests") {
            steps {
                script {
                    echo "Running unit tests"
                    sh "mvn test"
                }
            }
        }	
        stage('Nexus') {
            steps {
                script {
                    echo "Deploying artifacts to Nexus"
                    sh "mvn deploy"
                }
            }
        }
        stage('Docker Compose') {
            steps {
                script {
                    echo "Testing docker-compose up"
                    sh "docker-compose up"
                }
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
                sh "docker build -t anischennaoui/devops:skistation ."
            }
        }

         stage('Push in dockerhub') {
            steps {
                sh "docker login -u anischennaoui -p ac53550812"
                sh "docker push anischennaoui/devops:skistation"
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
      
        stage('MySQL Configuration') {
            steps {
                script {
                    // Retrieve MySQL password from Jenkins credentials
                    def dbPassword = credentials('')

                    // Set up MySQL connection configuration
                    def mysqlConfig = [
                        url: 'jdbc:mysql://localhost:3306/SkiStationDB', // Replace with your MySQL host, port, and database name
                        user: 'root',
                        password: dbPassword, // Use the retrieved password
                        driver: 'com.mysql.cj.jdbc.Driver'
                    ]

                    // Print MySQL connection details (optional, for verification)
                    echo "MySQL Connection URL: ${mysqlConfig.url}"
                    echo "MySQL User: ${mysqlConfig.user}"

                    // Now you can use 'mysqlConfig' in your SQL queries or database operations
                    // For example:
                    // sh "mysql -h ${mysqlConfig.url} -u ${mysqlConfig.user} -p${mysqlConfig.password} -e 'YOUR_SQL_QUERY_HERE'"
                }
            }
        }          
               
              
    }
   
    post {
        always {
            cleanWs()
        }
    }
    
}
