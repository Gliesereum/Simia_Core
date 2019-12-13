FROM adoptopenjdk/openjdk11:alpine-jre

ADD ./expert-0.1.4.jar /gls/expert/

WORKDIR /gls/expert

CMD sleep 5m && java -jar -server -XX:+UseG1GC -Xms500M -Xmx1536M -XX:MaxMetaspaceSize=1536M -XX:+UseStringDeduplication -XX:+OptimizeStringConcat \
./expert-0.1.4.jar
