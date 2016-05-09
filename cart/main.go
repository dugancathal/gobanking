package main

import (
	"fmt"
	"net/http"
	"os"

	"github.com/dugancathal/gobanking/cart/cart"
	"log"
)

func main() {
	port := os.Getenv("PORT")
	if port == "" {
		port = "80"
	}
	http.HandleFunc("/cart/items", cart.CartHandler)
	log.Fatal(http.ListenAndServe(fmt.Sprintf(":%s", port), nil))
}
