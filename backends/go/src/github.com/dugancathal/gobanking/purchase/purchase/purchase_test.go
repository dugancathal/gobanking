package purchase_test

import (
	"github.com/dugancathal/gobanking/purchase/purchase"

	. "github.com/onsi/ginkgo"
	. "github.com/onsi/gomega"
	"net/http/httptest"
	"net/http"
	"strings"
	"github.com/dugancathal/gobanking/money"
	"encoding/json"
	"fmt"
)

var _ = Describe("PurchaseController", func() {
	var controller purchase.PurchaseController
	var cartClient *fakeCartClient
	var bankClient *fakeBankClient
	var productClient *fakeProductClient
	BeforeEach(func() {
		cartClient = new(fakeCartClient)
		bankClient = new(fakeBankClient)
		productClient = new(fakeProductClient)
		controller = purchase.PurchaseController{
			CartClient: cartClient,
			BankClient: bankClient,
			ProductClient: productClient,
		}
	})

	Describe("POST /checkout", func() {
		It("retrieves the cart's items from the product service", func() {
			cartClient.idsToReturn = []string{"fancy-product-id"}
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("POST", "/checkout", strings.NewReader(`{"account_id": "my-account-ID"}`))
			controller.CheckoutHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusCreated))
			Expect(productClient.calledWithProductID).To(Equal("fancy-product-id"))
		})

		It("withdraws the total amount from the bank", func() {
			cartClient.idsToReturn = []string{"fancy-product-id"}
			productClient.priceToReturn = money.NewUSD(893)
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("POST", "/checkout", strings.NewReader(`{"account_id": "my-account-ID"}`))
			controller.CheckoutHandler(response, request)

			Expect(bankClient.withdrawalMadeWith).To(Equal(money.NewUSD(965)))
			Expect(bankClient.withdrawalMadeAgainst).To(Equal("my-account-ID"))
		})
	})

	Describe("GET /receipts/:id", func() {
		It("returns the receipt when it can be found", func() {
			purchaseID := checkoutCartCosting(controller, cartClient, productClient, 9.99)

			response := httptest.NewRecorder()
			request, _ := http.NewRequest("GET", fmt.Sprintf("/receipts/%s", purchaseID), strings.NewReader(""))
			controller.GetReceiptHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusOK))
			var receipt purchase.Receipt
			json.Unmarshal(response.Body.Bytes(), &receipt)
			Expect(receipt.Subtotal).To(Equal(money.NewUSD(999)))
			Expect(receipt.Tax).To(Equal(money.NewUSD(80)))
			Expect(receipt.Total).To(Equal(money.NewUSD(1079)))
		})

		It("returns a 404 when the receipt does not exist", func() {
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("GET", "/receipts/932943", strings.NewReader(""))
			controller.GetReceiptHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusNotFound))
			Expect(response.Body.String()).To(BeEmpty())
		})
	})
})

func checkoutCartCosting(controller purchase.PurchaseController, cartClient *fakeCartClient, productClient *fakeProductClient, amount float64) string {
	cartClient.idsToReturn = []string{"fancy-product-id"}
	productClient.priceToReturn = money.NewUSD(int64(amount * 100.0))
	checkoutResponse := httptest.NewRecorder()
	checkoutRequest, _ := http.NewRequest("POST", "/checkout", strings.NewReader(`{"account_id": "my-account-ID"}`))
	controller.CheckoutHandler(checkoutResponse, checkoutRequest)
	var checkoutResponseJSON map[string]string
	json.Unmarshal(checkoutResponse.Body.Bytes(), &checkoutResponseJSON)
	return checkoutResponseJSON["id"]
}

type fakeCartClient struct {
	idsToReturn []string
}

func (self *fakeCartClient) GetItemIDs() ([]string, error) {
	return self.idsToReturn, nil
}

type fakeBankClient struct {
	withdrawalMadeWith money.Money
	withdrawalMadeAgainst string
}

func (self *fakeBankClient) MakeWithdrawal(accountID string, amount money.Money) error {
	self.withdrawalMadeAgainst = accountID
	self.withdrawalMadeWith = amount
	return nil
}

type fakeProductClient struct {
	calledWithProductID string
	priceToReturn money.Money
}

func (self *fakeProductClient) GetProductPrice(productID string) (money.Money, error) {
	self.calledWithProductID = productID
	return self.priceToReturn, nil
}