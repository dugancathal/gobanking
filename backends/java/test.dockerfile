FROM java:8

COPY gradle /usr/src/javabanking/gradle
COPY gradlew /usr/src/javabanking/gradlew
COPY *.gradle /usr/src/javabanking/

COPY components/common /usr/src/javabanking/components/common

WORKDIR /usr/src/javabanking

RUN ./gradlew :components/common:compileJava

COPY . /usr/src/javabanking

ENV BANK_URL http://localhost:8080
ENV PRODUCT_URL http://localhost:8080
ENV CART_URL http://localhost:8080
ENV PURCHASE_URL http://localhost:8080

CMD ["./gradlew", "test"]