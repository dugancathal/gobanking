FROM golang:1.6

EXPOSE 80

COPY . /go/src/github.com/dugancathal/gobanking/products

RUN go install github.com/dugancathal/gobanking/products

CMD products
