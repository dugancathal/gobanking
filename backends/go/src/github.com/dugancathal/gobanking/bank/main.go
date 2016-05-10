package main

import (
	"fmt"
	"net/http"
	"os"

	"github.com/dugancathal/gobanking/bank/bank"
	"log"
)

func main() {
	port := os.Getenv("PORT")
	if port == "" {
		port = "80"
	}
	http.HandleFunc("/banks", bank.CreateBankHandler)
	http.HandleFunc("/banks/", bank.RouteToDepositWithdrawOrFunds)
	log.Fatal(http.ListenAndServe(fmt.Sprintf(":%s", port), nil))
}
