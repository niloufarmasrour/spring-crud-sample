FROM openjdk:17
CMD ["mkdir", "/root/config"]
CMD ["mkdir", "/root/logs"]
ENV TZ="Asia/Tehran"
RUN apt-get -y update
COPY target/classes/*.properties /root/config/
COPY target/classes/*.yml /root/config/
COPY target/sample.jar /root/
WORKDIR /root/
ENTRYPOINT ["java", "-jar", "/root/sample.jar", "--spring.config.location=file:///root/config/"]
