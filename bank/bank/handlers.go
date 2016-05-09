package bank

import (
	"net/http"
	"io/ioutil"
	"encoding/json"
	"github.com/dugancathal/gobanking/money"
	"strings"
)

func CreateBankHandler(w http.ResponseWriter, r *http.Request) {
	id := CreateBank()
	response, _ := json.Marshal(map[string]string{"id": id})
	w.Write(response)
}

func DepositHandler(w http.ResponseWriter, r *http.Request) {
	body, _ := ioutil.ReadAll(r.Body)
	defer r.Body.Close()

	deposit := new(money.Money)
	json.Unmarshal(body, deposit)
	MakeDeposit(bankID(r), *deposit)
	w.WriteHeader(http.StatusCreated)
}

func WithdrawalHandler(w http.ResponseWriter, r *http.Request) {
	body, _ := ioutil.ReadAll(r.Body)
	defer r.Body.Close()

	withdrawalAmount := new(money.Money)
	json.Unmarshal(body, withdrawalAmount)
	WithdrawFunds(bankID(r), *withdrawalAmount)
	w.WriteHeader(http.StatusCreated)
}

func GetFundsHandler(w http.ResponseWriter, r *http.Request) {
	funds := CheckFunds(bankID(r))
	fundJSON, _ := json.Marshal(funds)
	w.Write(fundJSON)
}

func RouteToDepositWithdrawOrFunds(w http.ResponseWriter, r *http.Request) {
	if strings.HasSuffix(r.URL.Path, "/funds") {
		GetFundsHandler(w, r)
	} else if strings.HasSuffix(r.URL.Path, "/deposit") {
		DepositHandler(w, r)
	} else if strings.HasSuffix(r.URL.Path, "/withdrawal") {
		WithdrawalHandler(w, r)
	}
}

func bankID(request *http.Request) string {
	parts := strings.Split(request.URL.Path, "/")
	return parts[2]
}