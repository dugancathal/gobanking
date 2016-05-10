package bank_test

import (
	"github.com/dugancathal/gobanking/bank/bank"

	. "github.com/onsi/ginkgo"
	. "github.com/onsi/gomega"
	"net/http/httptest"
	"net/http"
	"strings"
	"github.com/dugancathal/gobanking/money"
	"encoding/json"
	"fmt"
)

var _ = Describe("Bank", func() {
	var bankURLPrefix, bankID string
	BeforeEach(func() {
		bank.Clear()
		bankID = createBank()
		bankURLPrefix = fmt.Sprintf("/banks/%s", bankID)
	})

	Describe("GET /funds", func() {
		It("returns 0.00 to begin with", func() {
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("GET", bankURLPrefix + "/funds", strings.NewReader(""))
			bank.GetFundsHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusOK))
			Expect(currentFunds(bankID)).To(Equal(0.00))
		})

		It("return the new value when the bank has been updated", func() {
			bank.MakeDeposit(bankID, money.NewUSD(999))

			response := httptest.NewRecorder()
			request, _ := http.NewRequest("GET", bankURLPrefix + "/funds", strings.NewReader(""))
			bank.GetFundsHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusOK))
			Expect(currentFunds(bankID)).To(Equal(9.99))
		})
	})

	Describe("POST /deposit", func() {
		It("increases the balance of the bank by some amount", func() {
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("POST", bankURLPrefix + "/deposit", strings.NewReader(`{"money": 1.00, "currency": "USD"}`))
			bank.DepositHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusCreated))
			Expect(currentFunds(bankID)).To(Equal(1.00))
		})

		It("does not pollute other banks", func() {
			otherBankID := createBank()

			response := httptest.NewRecorder()
			request, _ := http.NewRequest("POST", bankURLPrefix + "/deposit", strings.NewReader(`{"money": 2.00, "currency": "USD"}`))
			bank.DepositHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusCreated))
			Expect(currentFunds(bankID)).To(Equal(2.00))

			response = httptest.NewRecorder()
			request, _ = http.NewRequest("GET", fmt.Sprintf("/banks/%s/funds", otherBankID), strings.NewReader(``))
			bank.GetFundsHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusOK))
			Expect(currentFunds(otherBankID)).To(Equal(0.00))
		})
	})

	Describe("POST /withdrawal", func() {
		It("descreases the balance of the bank by some number of pennies", func() {
			bank.MakeDeposit(bankID, money.NewUSD(300))
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("POST", bankURLPrefix + "/withdrawal", strings.NewReader(`{"money": 1.50, "currency": "USD"}`))
			bank.WithdrawalHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusCreated))
			Expect(currentFunds(bankID)).To(Equal(1.50))
		})

		It("does not pollute other banks", func() {
			otherBankID := createBank()
			bank.MakeDeposit(bankID, money.NewUSD(450))

			response := httptest.NewRecorder()
			request, _ := http.NewRequest("POST", bankURLPrefix + "/withdrawal", strings.NewReader(`{"money": 2.00, "currency": "USD"}`))
			bank.WithdrawalHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusCreated))
			Expect(currentFunds(bankID)).To(Equal(2.50))

			response = httptest.NewRecorder()
			request, _ = http.NewRequest("GET", fmt.Sprintf("/banks/%s/funds", otherBankID), strings.NewReader(``))
			bank.GetFundsHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusOK))
			Expect(currentFunds(otherBankID)).To(Equal(0.00))
		})
	})
})

func createBank() string {
	response := httptest.NewRecorder()
	request, _ := http.NewRequest("POST", "/banks", strings.NewReader(""))
	bank.CreateBankHandler(response, request)
	var createBankResponse map[string]string
	json.Unmarshal(response.Body.Bytes(), &createBankResponse)
	return createBankResponse["id"]
}

func currentFunds(id string) float64 {
	return float64(bank.CheckFunds(id).Pennies) / 100.
}