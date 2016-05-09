package purchase

import (
	"github.com/dugancathal/gobanking/money"
	"net/http"
	"io/ioutil"
	"encoding/json"
	"fmt"
	"bytes"
)

type CartClient interface {
	GetItemIDs() (ids []string, err error)
}

type ProductClient interface {
	GetProductPrice(productID string) (price money.Money, err error)
}

type BankClient interface {
	MakeWithdrawal(accountID string, amount money.Money) (err error)
}

type NetCartClient struct {
	client *http.Client
}

func NewNetCartClient() NetCartClient {
	return NetCartClient{client: http.DefaultClient}
}

func (self NetCartClient) GetItemIDs() ([]string, error) {
	var productIDs []string
	productResponse, err := http.Get("http://cart.dev/cart/items")
	productIDBody, err := ioutil.ReadAll(productResponse.Body)
	json.Unmarshal(productIDBody, &productIDs)
	return productIDs, err
}

type NetProductClient struct {
	client *http.Client
}

type ProductResponse struct {
	Price money.Money `json:"price"`
}

func NewNetProductClient() NetProductClient {
	return NetProductClient{client: http.DefaultClient}
}

func (self NetProductClient) GetProductPrice(productID string) (money.Money, error) {
	var product ProductResponse
	productResponse, err := http.Get(fmt.Sprintf("http://products.dev/products/%s", productID))
	productBody, err := ioutil.ReadAll(productResponse.Body)
	json.Unmarshal(productBody, &product)
	return product.Price, err
}

type NetBankClient struct {
	client *http.Client
}

func NewNetBankClient() NetBankClient {
	return NetBankClient{client: http.DefaultClient}
}

func (self NetBankClient) MakeWithdrawal(accountID string, amount money.Money) error {
	totalAsJSON, _ := json.Marshal(amount)
	_, err := http.Post(
		fmt.Sprintf("http://bank.dev/banks/%s/withdrawal", accountID),
		"application/json",
		bytes.NewReader(totalAsJSON),
	)
	if err != nil {
		fmt.Println(err.Error())
	}
	return nil
}