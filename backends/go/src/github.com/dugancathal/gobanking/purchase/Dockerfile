FROM golang:1.6

EXPOSE 80

COPY . /go/src/github.com/dugancathal/gobanking/purchase

RUN go install github.com/dugancathal/gobanking/purchase

CMD purchase