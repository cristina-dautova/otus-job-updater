timeout(time: 5, unit: 'MINUTES') {
    node {
        
        parameters {
            string(name: 'YAML_CONFIG', defaultValue: '', description: 'YAML configuration content')
        }

        def config = [:]
        
        stage('Read YAML') {
            script {      
                config = readYaml text: params.YAML_CONFIG
                echo "Jenkins URL: ${config.JENKINS_URL}"
                echo "Username: ${config.JENKINS_USERNAME}"        
                echo "Password: ${config.JENKINS_PASSWORD}"
            }
        }


        stage('checkout') {
            checkout scm
        }

        stage('Update job') {
            sh "docker run -t localhost:5005/jenkins_updater config.JENKINS_URL config.JENKINS_USERNAME config.JENKINS_PASSWORD"
        }
    }
}
