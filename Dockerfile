FROM buildpack-deps:sid-scm

LABEL maintainer="dimovski.bojan@gmail.com"

VOLUME /tmp

EXPOSE 8080

RUN apt-get update && apt-get install -y --no-install-recommends \
		bzip2 \
		unzip \
		xz-utils \
	&& rm -rf /var/lib/apt/lists/*

ENV LANG C.UTF-8

RUN { \
		echo '#!/bin/sh'; \
		echo 'set -e'; \
		echo; \
		echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
	} > /usr/local/bin/docker-java-home \
	&& chmod +x /usr/local/bin/docker-java-home

RUN ln -svT "/usr/lib/jvm/java-10-openjdk-$(dpkg --print-architecture)" /docker-java-home
ENV JAVA_HOME /docker-java-home

ENV JAVA_VERSION 10.0.1+10
ENV JAVA_DEBIAN_VERSION 10.0.1+10-4

RUN set -ex; \
	\

	if [ ! -d /usr/share/man/man1 ]; then \
		mkdir -p /usr/share/man/man1; \
	fi; \
	\

	ln -svT /docker-java-home/bin/java /usr/local/bin/java; \
	\
	apt-get update; \
	apt-get install -y --no-install-recommends \
		openjdk-10-jdk="$JAVA_DEBIAN_VERSION" \
	; \
	rm -rf /var/lib/apt/lists/*; \
	\
	rm -v /usr/local/bin/java; \
	\

	[ "$(readlink -f "$JAVA_HOME")" = "$(docker-java-home)" ]; \
	\

	update-alternatives --get-selections | awk -v home="$(readlink -f "$JAVA_HOME")" 'index($3, home) == 1 { $2 = "manual"; print | "update-alternatives --set-selections" }'; \

	update-alternatives --query java | grep -q 'Status: manual'

CMD ["jshell"]

ARG JAR_FILE=target/api-demo-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} viaplay-api.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/viaplay-api.jar"]
