package main

import (
	"net/http"
	"github.com/dugancathal/gobanking/purchase/purchase"
"fmt"
	"os"
	"log"
)


func main() {
	port := os.Getenv("PORT")
	if port == "" {
		port = "80"
	}
	controller := purchase.NewController()
	http.HandleFunc("/checkout", controller.CheckoutHandler)
	http.HandleFunc("/receipts/", controller.GetReceiptHandler)
	log.Fatal(http.ListenAndServe(fmt.Sprintf(":%s", port), nil))
}