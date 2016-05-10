FROM golang:1.6

EXPOSE 80

COPY . /go/src/github.com/dugancathal/gobanking/bank

RUN go install github.com/dugancathal/gobanking/bank

CMD bank
