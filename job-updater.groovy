timeout(time: 5, unit: 'MINUTES') {
    node('main') {

        def config = readYaml text:$YAML_CONFIG
        def jenkinsUrl = config['JENKINS_URL']
        def username = config['JENKINS_USERNAME']
        def password = config['JENKINS_PASSWORD']

        stage('checkout') {
            checkout scm
        }

        stage('Update job') {
            sh "docker run -t localhost:5005/jenkins_updater $jenkinsUrl $username $password"
        }
    }
}
