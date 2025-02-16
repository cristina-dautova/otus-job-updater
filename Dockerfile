FROM python:3.12.1

USER root

RUN mkdir -p /root/jobs_builder

WORKDIR /root/jobs_builder

COPY . /root/jobs_builder/

RUN apt update -y \
        && apt install vim -y

RUN pip3 install jenkins-job-builder==6.4.2

ENTRYPOINT [ "/bin/sh", "entrypoint.sh" ]
