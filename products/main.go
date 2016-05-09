package main

import (
	"fmt"
	"net/http"
	"os"

	"github.com/dugancathal/gobanking/products/products"
	"log"
)

func main() {
	port := os.Getenv("PORT")
	if port == "" {
		port = "80"
	}
	http.HandleFunc("/products", products.IndexFunc)
	http.HandleFunc("/products/", products.GetFunc)
	log.Fatal(http.ListenAndServe(fmt.Sprintf(":%s", port), nil))
}
