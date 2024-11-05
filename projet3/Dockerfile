FROM jenkins/jenkins:lts

USER root

# Mettre à jour et installer Docker
RUN apt-get update && \
    apt-get install -y docker.io && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Revenir à l'utilisateur Jenkins
USER jenkins
