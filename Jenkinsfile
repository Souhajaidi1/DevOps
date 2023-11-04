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
       
        stage("Build") {
            steps {
                sh "mvn clean compile"
                  }
        }
        stage('MySQL Connexion') {
                steps {
                        script {
                        // Use MySQL client to connect to the running MySQL container

                        sh 'docker run -d --name my-mysql-container --network devops -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_DATABASE=SkiStationDB mysql:latest'
                        }
                }
        }

        stage("Sonar") {
            steps {
                bat "mvn sonar:sonar"
            }
        }
        
        stage("SRC Analysis Testing") {
            steps {
                bat "mvn sonar:sonar"
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
