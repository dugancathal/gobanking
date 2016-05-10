package purchase

import (
	"net/http"
	"encoding/json"
	"github.com/dugancathal/gobanking/money"
	"math"
	"io/ioutil"
)

type PurchaseController struct {
	CartClient CartClient
	ProductClient ProductClient
	BankClient BankClient
}

func NewController() PurchaseController {
	return PurchaseController{
		CartClient: NewNetCartClient(),
		BankClient: NewNetBankClient(),
		ProductClient: NewNetProductClient(),
	}
}

type checkoutRequest struct {
	AccountID string `json:"account_id"`
}

func (self PurchaseController) CheckoutHandler(w http.ResponseWriter, r *http.Request) {
	productIDs, _ := self.CartClient.GetItemIDs()

	subtotal := money.NewUSD(0)

	for _, productID := range productIDs {
		price, _ := self.ProductClient.GetProductPrice(productID)
		subtotal = money.Add(subtotal, price)
	}

	tax := int64(math.Ceil(float64(subtotal.Pennies) * 0.08))

	total := money.Add(subtotal, money.NewUSD(tax))

	var reqBody checkoutRequest
	body, _ := ioutil.ReadAll(r.Body)
	defer r.Body.Close()
	json.Unmarshal(body, &reqBody)

	self.BankClient.MakeWithdrawal(reqBody.AccountID, total)

	purchaseId := AddReceipt(subtotal, money.NewUSD(tax))

	response, _ := json.Marshal(map[string]string{"id": purchaseId})
	w.WriteHeader(http.StatusCreated)
	w.Write(response)
}

func (self PurchaseController) GetReceiptHandler(w http.ResponseWriter, req *http.Request) {
	receiptID := req.URL.Path[len("/receipts/"):]
	var receipt *Receipt
	for _, r := range ReceiptBook.Receipts {
		if receiptID == r.ID {
			receipt = &r
		}
	}

	if receipt != nil {
		response, _ := json.Marshal(receipt)
		w.Write(response)
	} else {
		w.WriteHeader(http.StatusNotFound)
	}
}