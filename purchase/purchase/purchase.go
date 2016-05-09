package purchase

import (
	"github.com/dugancathal/gobanking/money"
	"strconv"
)

type Receipt struct {
	ID       string `json:"id"`
	Subtotal money.Money `json:"subtotal"`
	Tax      money.Money `json:"tax"`
	Total    money.Money `json:"total"`
}

type counter struct {
	counter int
}
func (self counter) Inc() string {
	self.counter++
	return strconv.Itoa(self.counter)
}

type receiptBook struct {
	Receipts []Receipt
}

var ReceiptBook = receiptBook{Receipts: []Receipt{}}
var idCounter = counter{}

func AddReceipt(subTotal, tax money.Money) string {
	newId := idCounter.Inc()
	ReceiptBook.Receipts = append(ReceiptBook.Receipts, Receipt{
		ID: newId,
		Subtotal: subTotal,
		Tax: tax,
		Total: money.Add(subTotal, tax),
	})
	return newId
}